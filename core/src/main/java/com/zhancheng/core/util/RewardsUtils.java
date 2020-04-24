package com.zhancheng.core.util;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhancheng.core.config.exception.MyException;
import com.zhancheng.core.constant.CodeMsg;
import com.zhancheng.core.dao.*;
import com.zhancheng.core.entity.*;
import com.zhancheng.core.vo.OrderProductVO;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author BianShuHeng
 * @decription
 * @project RewardsUtils
 * @date 2019/11/25 14:28
 */
@Component
public class RewardsUtils {

    @Resource
    private OrderProductMapper orderProductMapper;

    @Resource
    private BuyHistoryMapper buyHistoryMapper;

    @Resource
    private ConfigInfoMapper configInfoMapper;

    @Resource
    private WalletMapper walletMapper;

    @Transactional(rollbackFor = Exception.class)
    public Boolean allotReward(Order order) {

        // 订单id
        Integer orderId = order.getId();

        //用户id
        Integer userId = order.getUserId();

        List<OrderProductVO> orderProductVOList = orderProductMapper.queryList(orderId);

        // 获取奖励配置信息
        ConfigInfo shareAward = configInfoMapper.selectOne(new QueryWrapper<ConfigInfo>().eq("key", "share_award").select("id", "value"));
        ConfigInfo freeNum = configInfoMapper.selectOne(new QueryWrapper<ConfigInfo>().eq("key", "free_num").select("id", "value"));
        ConfigInfo freeAward = configInfoMapper.selectOne(new QueryWrapper<ConfigInfo>().eq("key", "free_award").select("id", "value"));

        // 奖励配置的数量
        double shareAwardValue = Double.parseDouble(shareAward.getValue());
        int freeNumValue = Integer.parseInt(freeNum.getValue());
        double freeAwardValue = Double.parseDouble(freeAward.getValue());

        if (freeNumValue == 0) {
            throw new MyException(CodeMsg.CONFIG_INFO_ERROR);
        }

        // 分享奖励
        BigDecimal shareAmount = new BigDecimal(0);
        // 免单奖励
        BigDecimal freeAmount = new BigDecimal(0);
        // 总奖励金额
        BigDecimal totalAmount = new BigDecimal(0);

        Integer count = orderProductMapper.selectCount(new QueryWrapper<OrderProduct>().eq("order_id", orderId));
        for (OrderProductVO orderProductVo : orderProductVOList) {

            BigDecimal productPrice = orderProductVo.getProductPrice();
            Integer productNum = orderProductVo.getProductNum();
            Integer pid = orderProductVo.getPid();

            // 免单数量
            int num;
            // 余数
            int remainder;
            BuyHistory buyHistory = buyHistoryMapper.selectOne(new QueryWrapper<BuyHistory>()
                    .eq("user_id", userId).eq("product_id", pid));

            if (ObjectUtil.isNotNull(buyHistory)) {

                num = (productNum + buyHistory.getAwardNum()) / freeNumValue;
                remainder = (productNum + buyHistory.getAwardNum()) % freeNumValue;

                // 修改购买总数和剩余奖励数量
                buyHistory.setBuyNum(buyHistory.getBuyNum() + productNum).setAwardNum(remainder);
                buyHistory.updateById();
            } else {
                num = productNum / freeNumValue;
                remainder = productNum % freeNumValue;

                // 添加商品购买记录
                BuyHistory buyHistoryInfo = new BuyHistory();
                buyHistoryInfo.setUserId(userId).setProductId(pid).setBuyNum(productNum).setAwardNum(remainder);
                buyHistoryInfo.insert();
            }

            // 分享奖励数量
            int shareNum = productNum - num;

            // 单个商品的分享奖励 商品价格 * 分享商品的数量 * 分享奖励比例
            BigDecimal shareReward = productPrice.multiply(new BigDecimal(shareNum)).multiply(new BigDecimal(shareAwardValue));

            // 单个商品的免单奖励 商品价格 * 免单商品的数量 * 免单奖励比例
            BigDecimal freeReward = productPrice.multiply(new BigDecimal(num)).multiply(new BigDecimal(freeAwardValue));

            // 分享奖励
            shareAmount = shareAmount.add(shareReward);
            // 免单奖励
            freeAmount = freeAmount.add(freeReward);

            // 总奖励金额
            totalAmount = totalAmount.add(shareAmount).add(freeAmount);

        }

        addTransfer(userId, orderId, totalAmount);

        Award award = new Award();

      //  award.setUserId(userId).setOrderId(orderId).setShareId(order.getShareId()).setProductNum(count)
           //     .setOrderAmount(order.getPayMoney()).setShareAmount(shareAmount).setFreeAmount(freeAmount)
           //     .setTotalAmount(totalAmount).setAwardState(3);

        return award.insert();
    }

    private void addTransfer(Integer userId, Integer orderId, BigDecimal amount) {

        Wallet wallet = walletMapper.selectOne(new QueryWrapper<Wallet>().eq("user_id", userId));

       // BigDecimal incomeBalance = wallet.getIncomeBalance().add(amount);
        //BigDecimal commissionBalance = wallet.getCommissionBalance().add(amount);

        //wallet.setIncomeBalance(incomeBalance).setCommissionBalance(commissionBalance);
        wallet.updateById();

        Transfer transfer = new Transfer();

        transfer.setUserId(userId).setOrderId(orderId).setTradeType(1).setType(1).setAmount(amount)
                .setRemark("被邀请人购买商品分配奖励");

        Transfer transferInfo = new Transfer();

        BeanUtil.copyProperties(transfer, transferInfo);
        transferInfo.setType(2);

        transfer.insert();
        transferInfo.insert();
    }
}
