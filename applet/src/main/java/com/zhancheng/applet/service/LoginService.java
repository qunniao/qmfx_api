package com.zhancheng.applet.service;

import com.zhancheng.core.dto.RegisterDTO;

import java.util.Map;

/**
 * @author BianShuHeng
 * @decription
 * @project LoginService
 * @date 2019/10/26 11:51
 */
public interface LoginService {

    /**
     * @return
     * @throws Exception
     */
    Map<String, Object> wxLogin(RegisterDTO registerDto)throws Exception ;

}
