package com.zhancheng.core.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author BianShuHeng
 * @decription
 * @project FreightListVo
 * @date 2019/10/24 15:27
 */
@Data
public class FreightListVo {

    private Integer id;
    private String title;
    private Integer valuationWay;
    private Integer defaultStock;
    private Integer defaultBuyins;
    private Integer defaultTake;
    private Integer defaultRetail;
    private String transportWay;
    private Integer freightNum;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtModified;

}
