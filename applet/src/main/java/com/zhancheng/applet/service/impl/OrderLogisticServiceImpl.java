package com.zhancheng.applet.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.applet.service.OrderLogisticService;
import com.zhancheng.applet.service.OrderProductService;
import com.zhancheng.core.dao.OrderLogisticMapper;
import com.zhancheng.core.dao.OrderProductMapper;
import com.zhancheng.core.entity.OrderLogistic;
import com.zhancheng.core.entity.OrderProduct;
import com.zhancheng.core.util.KdniaoTrackQueryAPI;
import com.zhancheng.core.vo.OrderLogisticVO;
import com.zhancheng.core.vo.OrderProductVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单商品
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:29
 */

@Service
public class OrderLogisticServiceImpl extends ServiceImpl<OrderLogisticMapper, OrderLogistic> implements OrderLogisticService {
    private static Logger log = LoggerFactory.getLogger(OrderLogisticServiceImpl.class);
    @Resource
    private OrderLogisticMapper orderLogisticMapper;
    @Resource
    private OrderProductMapper orderProductMapper;

    @Override
    public OrderLogisticVO queryInfo(Integer id) {
        OrderLogisticVO item = orderLogisticMapper.queryInfo(id);
        String[] strs = item.getOrderProductId().split(",");
        //取相关订单产品信息
        List<OrderProductVO> opList = new ArrayList<>();
        for(int j=0;j<strs.length;j++){
            if(strs[j].equals(""))continue;
            opList.add(orderProductMapper.queryInfo(new Integer(strs[j])));
        }
        item.setOrderProductList(opList);
        //检查物流跟踪信息是否最新
        //如果修改时间超过6小时则重新查询第三方物流接口，没超过则直接从数据库缓存中取
        long nowTime = System.currentTimeMillis()/1000;
        long updateTime = item.getGmtModified().getTime()/1000;
        long t = nowTime-updateTime;
        long t2 = 1000*60*60*6;
        log.info("nowTime:"+nowTime+" updateTime:"+updateTime+" t:"+t+" t2:"+t2);
        if(item.getGmtModified()==item.getGmtCreate() || t>t2){
            //调用第三方查询快递接口
            String result = "获取物流信息失败";
            KdniaoTrackQueryAPI kdniaoTrackQueryAPI = new KdniaoTrackQueryAPI();
            try {
                result = kdniaoTrackQueryAPI.getOrderTracesByJson(item.getExpressNumber(),item.getLogisticCode());
                item.setLogisticTrace(result);
                OrderLogistic orderLogistic = new OrderLogistic();
                BeanUtil.copyProperties(item, orderLogistic);
                orderLogistic.setLogisticTrace(result);
                orderLogistic.updateById();
            } catch (Exception e) {
                log.info("获取物流信息失败");
                e.printStackTrace();
            }

        }
        return item;
    }


}