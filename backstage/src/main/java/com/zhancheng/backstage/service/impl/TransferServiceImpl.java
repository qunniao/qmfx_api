package com.zhancheng.backstage.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.backstage.service.TransferService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.dao.OrderProductMapper;
import com.zhancheng.core.dao.TransferMapper;
import com.zhancheng.core.entity.OrderProduct;
import com.zhancheng.core.entity.Transfer;
import com.zhancheng.core.vo.CommissionListVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 钱包收支流水
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:29
 */

@Service
public class TransferServiceImpl extends ServiceImpl<TransferMapper, Transfer> implements TransferService {

    @Resource
    private OrderProductMapper orderProductMapper;

    @Override
    public IPage<CommissionListVO> queryCommission(PageDto<Transfer> pageDto, Integer userId) {

        IPage<CommissionListVO> commissionListPage = baseMapper.queryCommission(pageDto.getPage(), userId);

        List<CommissionListVO> recordList = commissionListPage.getRecords();

        if (ObjectUtil.isNotEmpty(recordList)) {
            for (CommissionListVO commissionListVo : recordList) {
                Integer orderId = commissionListVo.getOrderId();

                if (ObjectUtil.isNotNull(orderId)) {

                    Integer count = orderProductMapper.selectCount(new QueryWrapper<OrderProduct>()
                            .eq("order_id", orderId));
                    commissionListVo.setProductNum(count);
                }
            }
        }
        return commissionListPage;
    }
}