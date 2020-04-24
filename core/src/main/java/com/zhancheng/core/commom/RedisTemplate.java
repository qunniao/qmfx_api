package com.zhancheng.core.commom;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.zhancheng.core.entity.Admin;
import com.zhancheng.core.entity.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author tangchao
 */

@Component
public class RedisTemplate extends StringRedisTemplate {

    private static String redisName = "distribution:";

    @Autowired
    private HttpServletRequest request;

    @Autowired
    public RedisTemplate(RedisConnectionFactory connectionFactory) {
        this.setConnectionFactory(connectionFactory);
        this.afterPropertiesSet();
    }

    /**
     * 过期时间(秒为单位)
     */
    private long expireTime = 30 * 24 * 60 * 60;


    /**
     * 通过key获取value，并重置过期时间
     *
     * @param key key
     * @return value
     */
    public String get(String key) {
        String value = super.opsForValue().get(key);
        if (!StringUtils.isBlank(value)) {
            super.opsForValue().set(key, value, expireTime, TimeUnit.SECONDS);
        }
        return value;
    }

    /**
     * 通过key获取value
     *
     * @param key key
     * @return value
     */
    public String getKey(String key) {

        return super.opsForValue().get(key);
    }

    /**
     * 存储数据
     *
     * @param key   键
     * @param value 值
     */
    public void set(String key, String value) {
        super.opsForValue().set(key, value, expireTime, TimeUnit.SECONDS);
    }


    /**
     * 存储手机验证码
     *
     * @param key   键
     * @param value 值
     */
    public void setSmsCode(String key, String value) {
        super.opsForValue().set(key, value, 300, TimeUnit.SECONDS);
    }

    /**
     * 存储用户信息
     *
     * @param user 用户信息
     * @return 用户token
     */
    public String setUser(User user) {

        Integer uid = user.getId();
//        判断用户是否已经存入redis,如果有则删除信息
        String oldToken = get(redisName + "user:id:" + uid);
        if (StrUtil.isNotBlank(oldToken)) {
            super.delete(redisName + "user:token:" + oldToken);
        }
        String token = IdUtil.simpleUUID();

        //存储id : token
        set(redisName + "user:id:" + uid, token);
        Map<String, Object> userMap = new HashMap<>(2);
        userMap.put("id", uid);
        String userString = JSONUtil.toJsonStr(userMap);
        //存储token : 用户信息
        set(redisName + "user:token:" + token, userString);
        return token;
    }

    public String setAdmin(Admin admin) {
        Integer uid = admin.getId();
//        判断用户是否已经存入redis,如果有则删除信息
        String oldToken = get(redisName + "user:id:" + uid);
        if (oldToken != null) {
            super.delete(redisName + "user:token:" + oldToken);
        }
        String token = IdUtil.simpleUUID();

        //存储id : token
        set(redisName + "admin:id:" + uid, token);
        Map<String, Object> userMap = new HashMap<>(2);
        userMap.put("id", uid);
        String userString = JSONUtil.toJsonStr(userMap);
        //存储token : 用户信息
        set(redisName + "admin:token:" + token, userString);
        return token;
    }

    /**
     * 传入token,返回用户信息
     *
     * @param token
     * @return 返回用户信息, 如果没有则返回null
     */
    public JSONObject getAdmin(String token) {
        String s = get(redisName + "admin:token:" + token);
        if (StringUtils.isBlank(s)) {
            return null;
        }
        return JSONUtil.parseObj(s);
    }

    /**
     * 传入token,返回用户信息
     *
     * @param token
     * @return 返回用户信息, 如果没有则返回null
     */
    public JSONObject getUser(String token) {
        String s = get(redisName + "user:token:" + token);
        if (StringUtils.isBlank(s)) {
            return null;
        }
        return JSONUtil.parseObj(s);
    }

    /**
     * 查询用户Id
     *
     * @return 返回用户Id
     */
    public Integer getUserUid() {
        String token = request.getHeader("token");

        String user = get(redisName + "user:token:" + token);
        if (StringUtils.isBlank(user)) {
            return null;
        }
        JSONObject jsonObject = JSONUtil.parseObj(user);

        return jsonObject.getInt("id");
    }

    /**
     * 查询用户Id
     *
     * @return 返回用户Id
     */
    public Integer getAdminUid() {
        // 从请求中获取token
        String token = request.getHeader("token");
        String s = get(redisName + "admin:token:" + token);
        if (StringUtils.isBlank(s)) {
            return null;
        }
        JSONObject jsonObject = JSONUtil.parseObj(s);

        return jsonObject.getInt("id");
    }
}
