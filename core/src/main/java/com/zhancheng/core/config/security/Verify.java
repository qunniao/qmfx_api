package com.zhancheng.core.config.security;


import com.zhancheng.core.constant.UserIdentity;

import java.lang.annotation.*;

/**
 * @author Demon
 */
//@Target表示注解能用于哪些元素上面
@Target({ElementType.TYPE,ElementType.METHOD})
//@Retention用于什么时候执行
@Retention(RetentionPolicy.RUNTIME)
@Documented
//@Mapping
public @interface Verify {

    /**
     * 标识权限
     *
     * @return
     */
    String role() default UserIdentity.NOT;
}