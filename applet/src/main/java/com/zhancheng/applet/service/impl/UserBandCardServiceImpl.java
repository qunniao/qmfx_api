package com.zhancheng.applet.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.applet.service.UserAddressService;
import com.zhancheng.applet.service.UserBandCardService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.dao.UserAddressMapper;
import com.zhancheng.core.dao.UserBandCardMapper;
import com.zhancheng.core.dto.UserBandCardDTO;
import com.zhancheng.core.entity.Order;
import com.zhancheng.core.entity.UserAddress;
import com.zhancheng.core.entity.UserBandCard;
import com.zhancheng.core.util.BandNameList;
import com.zhancheng.core.util.HttpUtil;
import com.zhancheng.core.util.KdniaoTrackQueryAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户银行卡
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-25 16:51:16
 */

@Service
public class UserBandCardServiceImpl extends ServiceImpl<UserBandCardMapper, UserBandCard> implements UserBandCardService {
    private static Logger log = LoggerFactory.getLogger(UserBandCardServiceImpl.class);
    @Override
    public IPage<UserBandCard> selectPage(PageDto<UserBandCard> pageDto) {
        return baseMapper.selectPage(pageDto.getPage(),
                new QueryWrapper<UserBandCard>());
    }

    @Override
    public UserBandCard info(Integer id) {

        return baseMapper.selectById(id);
    }

    @Override
    public Boolean insert(UserBandCardDTO modal) {
        UserBandCard m = new UserBandCard();
        BeanUtil.copyProperties(modal, m);

        UserBandCard item = baseMapper.queryInfoByCardNumber(modal.getCardNumber(),modal.getUserId());
            if (ObjectUtil.isNotNull(item)){
                item.setRealName(modal.getRealName());
                item.updateById();
                return true;
            }
        //查询银行名称
        String url = "https://ccdcapi.alipay.com/validateAndCacheCardInfo.json?_input_charset=utf-8&cardNo="+modal.getCardNumber()+"&cardBinCheck=true";
        String result = HttpUtil.doGet(url);
        log.info("查询银行名称result:"+result);

        // 转为 Json 对象
        JSONObject json = new JSONObject(result);
        //获取到 bank 代码
        String bank = String.valueOf(json.get("bank"));
        log.info("bank:"+bank);
        BandNameList bandNameList = new BandNameList();
        String cardName = (String) bandNameList.getMap().get(bank);
        log.info("cardName:"+cardName);
        m.setCardName(cardName);
        m.setBandCode(bank);
        log.info("bank1:"+m.getBandCode()+"cardName:"+m.getCardName());
        return  baseMapper.insert(m) > 0;
    }

    @Override
    public Boolean update(UserBandCardDTO modal) {
        /*
        UserBandCard m = new UserBandCard();
        BeanUtil.copyProperties(modal, m);

         */
        UserBandCard m = baseMapper.selectById(modal.getId());
        m.setCardNumber(modal.getCardNumber());
        m.setRealName(modal.getRealName());
        return baseMapper.updateById(m) > 0;
    }

    @Override
    public Boolean delete(List<Integer> ids) {
        return baseMapper.deleteBatchIds(ids) > 0;
    }



}