package com.zhancheng.core.config.award;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AwardConfig {
    /**
     * 统一奖励金额
     */
    @Value("${award.money}")
    private double money;

    /**
     * 统一奖励比例
     */
    @Value("${award.percent}")
    private double percent;

    /**
     * 统一奖励类型,1按金额奖励，2按比例奖励
     */
    @Value("${award.type}")
    private int type;


    public double getMoney() {
        return money;
    }

    public double getPercent() {
        return percent;
    }

    public int getType() {
        return type;
    }
}
