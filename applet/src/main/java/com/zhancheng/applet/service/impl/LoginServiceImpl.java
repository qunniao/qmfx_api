package com.zhancheng.applet.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhancheng.applet.service.LoginService;
import com.zhancheng.core.commom.RedisTemplate;
import com.zhancheng.core.config.exception.MyException;
import com.zhancheng.core.constant.CodeMsg;
import com.zhancheng.core.dao.UserMapper;
import com.zhancheng.core.dto.RegisterDTO;
import com.zhancheng.core.entity.InviteInfo;
import com.zhancheng.core.entity.User;
import com.zhancheng.core.entity.Visitor;
import com.zhancheng.core.entity.Wallet;
import com.zhancheng.core.util.WxUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author BianShuHeng
 * @decription
 * @project LoginServiceImpl
 * @date 2019/11/19 18:15
 */
@Service
public class LoginServiceImpl implements LoginService {
    private static Logger log = LoggerFactory.getLogger(LoginServiceImpl.class);
    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private HttpServletRequest request;

    @Resource
    private WxUtil wxUtil;

    @Resource
    private UserMapper userMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> wxLogin(RegisterDTO registerDto) throws Exception {
        log.info("wxLogin shareId:"+registerDto.getInviterId());
        Map<String, Object> map = new HashMap<>(2);

        Integer userId = null;

        JSONObject jsonObject = wxUtil.loginByWeiXin(registerDto.getCode(), registerDto.getEncryptedData(), registerDto.getIv());

        User userInfo = userMapper.selectOne(new QueryWrapper<User>()
                .eq("openid", jsonObject.getStr("openid"))
                .eq("is_deleted", 0));
        if (ObjectUtil.isNotNull(userInfo)) {
            userInfo.setGmtLogin(new Date());
            userInfo.updateById();
            String token = redisTemplate.setUser(userInfo);
            userId = userInfo.getId();
            map.put("token", token);
            map.put("user", userInfo);
        } else {
            User user = new User();

            // 邀请人id
            Integer inviterId = registerDto.getInviterId();

            user.setNickname(jsonObject.getStr("nickName"))
                    .setOpenid(jsonObject.getStr("openid"))
                    .setGender(jsonObject.getInt("gender"))
                    .setCover(jsonObject.getStr("avatarUrl"))
                    .setGmtLogin(new Date()).setInviters(inviterId);
            user.insert();

            Wallet wallet = new Wallet();
            wallet.setUserId(user.getId()).setPassword(SecureUtil.md5("123456"));
            wallet.insert();

            if (ObjectUtil.isNotNull(inviterId)) {

                User inviterUser = userMapper.selectById(inviterId);

                if (ObjectUtil.isNull(inviterUser)) {
                    throw new MyException(CodeMsg.USER_NOT_EXISTED);
                }

                //增加邀请人数
                Integer inviterNum =  inviterUser.getInviters() + 1;
                inviterUser.setInviters(inviterNum);
                inviterUser.updateById();
                user.setInviterId(inviterId);

                // 添加邀请人层级信息
                InviteInfo inviteInfo = new InviteInfo();
                inviteInfo.setUserId(user.getId()).setInviterId(inviterId).setOrderNo(inviterNum);
                inviteInfo.insert();
            }

            String token = redisTemplate.setUser(user);
            userId = user.getId();
            map.put("token", token);
            map.put("user", user);
        }

        // 添加访客记录
        String clientIp = ServletUtil.getClientIP(request, "HTTP_CLIENT_IP");
        Visitor visitor = new Visitor();
        visitor.setUserId(userId).setIp(clientIp).insert();

        return map;
    }
}
