package com.zhancheng.applet.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.dto.TransferQueryDTO;
import com.zhancheng.core.entity.Transfer;

/**
 * @author BianShuHeng
 * @decription
 * @project TransferService
 * @date 2019/11/8 15:39
 */
public interface TransferService extends IService<Transfer> {

  /**
   * 分页查询订单表列表
   * @param pageDto 分页参数
   * @return IPage<OrderInfo>
   */
  IPage<Transfer> selectPage(PageDto pageDto, TransferQueryDTO transferQueryDto);

}
