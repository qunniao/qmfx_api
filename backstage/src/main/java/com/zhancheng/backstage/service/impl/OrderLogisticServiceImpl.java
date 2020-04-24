package com.zhancheng.backstage.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.backstage.service.OrderLogisticService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.dao.OrderLogisticMapper;
import com.zhancheng.core.dao.OrderProductMapper;
import com.zhancheng.core.dto.OrderLogisticQueryDTO;
import com.zhancheng.core.entity.OrderLogistic;
import com.zhancheng.core.util.KdniaoTrackQueryAPI;
import com.zhancheng.core.vo.OrderListVO;
import com.zhancheng.core.vo.OrderLogisticVO;
import com.zhancheng.core.vo.OrderProductVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
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
        int nowTime = (int)(System.currentTimeMillis()/1000);
        int updateTime = (int)(item.getGmtModified().getTime()/1000);
        int t = nowTime-updateTime;
        int t2 = 1000*60*60*6;
        log.info("nowTime:"+nowTime+" updateTime:"+updateTime+" t:"+t+" t2:"+t2);
        int t3 = (int)(item.getGmtModified().getTime()-item.getGmtCreate().getTime());
        log.info("相减:"+t3);
        if(t3<10 || t>t2){
            log.info("获取物流信息");
            //调用第三方查询快递接口
            String result = "获取物流信息失败";
            KdniaoTrackQueryAPI kdniaoTrackQueryAPI = new KdniaoTrackQueryAPI();
            try {
                result = kdniaoTrackQueryAPI.getOrderTracesByJson(item.getExpressNumber(),item.getLogisticCode());
                item.setLogisticTrace(result);
                OrderLogistic orderLogistic = new OrderLogistic();
                BeanUtil.copyProperties(item, orderLogistic);
                orderLogistic.setLogisticTrace(result);
                orderLogistic.setGmtModified(new Date());
                orderLogistic.updateById();
            } catch (Exception e) {
                log.info("获取物流信息失败");
                e.printStackTrace();
            }

        }
        return item;
    }

    @Override
    public List<OrderLogisticVO> queryList(Integer orderId) {
        List<OrderLogisticVO> orderLogisticList = baseMapper.queryList(orderId);

        for(int i=0;i<orderLogisticList.size();i++){
            String[] strs = orderLogisticList.get(i).getOrderProductId().split(",");
            List<OrderProductVO> opList = new ArrayList<>();
            for(int j=0;j<strs.length;j++){
                if(strs[j].equals(""))continue;
                opList.add(orderProductMapper.queryInfo(new Integer(strs[j])));
            }
            orderLogisticList.get(i).setOrderProductList(opList);
        }

        return orderLogisticList;
    }

    @Override
    public Boolean insert(OrderLogisticQueryDTO item) {
        OrderLogistic orderLogistic = new OrderLogistic();
        BeanUtil.copyProperties(item, orderLogistic);
        orderLogistic.insert();
        return true;
    }

    @Override
    public Boolean update(OrderLogistic item) {
        item.updateById();
        return true;
    }

    @Override
    public Boolean delete(List<Integer> pids) {
        return baseMapper.deleteBatchIds(pids) > 0;
    }
}