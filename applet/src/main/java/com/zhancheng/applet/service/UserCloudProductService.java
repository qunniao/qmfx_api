package com.zhancheng.applet.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.core.entity.User;
import com.zhancheng.core.entity.UserCloudProduct;
import com.zhancheng.core.vo.UserCloudProductVO;

import java.util.List;

/**
 * 用户
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:29
 */
public interface UserCloudProductService extends IService<UserCloudProduct> {


    List<UserCloudProductVO> list(Integer userId);
}

