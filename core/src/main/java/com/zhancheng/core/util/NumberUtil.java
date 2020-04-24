package com.zhancheng.core.util;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;

/**
 * @author BianShuHeng
 * @decription
 * @project NumberUtil
 * @date 2019/11/21 16:28
 */
public class NumberUtil {

    /**
     * 生成订单号
     * @return
     */
    public static String generateOrderNumber(){
        Snowflake snowflake = IdUtil.getSnowflake(1, 1);
       return snowflake.nextIdStr();
    }
}
