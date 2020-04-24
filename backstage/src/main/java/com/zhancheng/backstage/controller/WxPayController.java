package com.zhancheng.backstage.controller;

import cn.hutool.core.util.ObjectUtil;
import com.zhancheng.backstage.service.WxPayService;
import com.zhancheng.core.commom.RedisTemplate;
import com.zhancheng.core.config.security.Verify;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.constant.UserIdentity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * @author BianShuHeng
 * @decription 支付接口
 * @project WxPayController
 * @date 2019/11/6 21:03
 */
@Api(tags = "支付接口")
@RestController
@RequestMapping("/pay")
public class WxPayController {

    @Resource
    private WxPayService wxPayService;

    @Resource
    private RedisTemplate redisTemplate;

    Logger logger = LoggerFactory.getLogger(WxPayController.class);

    @Verify(role = UserIdentity.ORDINARY)
    @ApiOperation(value = "微信支付")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单id"),
    })
    @PostMapping("/agentWxPay")
    public R<Map<String, String>> agentWxPay(Integer orderId) throws Exception {
        Integer uid = redisTemplate.getUserUid();
        return R.ok(wxPayService.agentWxPay(orderId, uid));
    }

    /**
     * 回调接口
     * @param notifyXml
     */
    @PostMapping("/wxPayUnifiedNotify")
    public void wxPayUnifiedNotify(@RequestBody String notifyXml, HttpServletResponse response) {

        String result = wxPayService.wxPayUnifiedNotify(notifyXml);

        logger.info(result);
        writeResp(response, result);
    }

    private void writeResp(HttpServletResponse response, String result) {
        PrintWriter printWriter  = null;
        try {
            printWriter = response.getWriter();
            printWriter.write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (ObjectUtil.isNotNull(printWriter)) {
                printWriter.flush();
                printWriter.close();
            }
        }
    }

    /**
     * 微信退款回调接口
     * @param notifyXml
     */
    @PostMapping("/wxPayRefundUnifiedNotify")
    public void wxPayRefundUnifiedNotify(@RequestBody String notifyXml, HttpServletResponse response) {

        String result = wxPayService.wxPayRefundUnifiedNotify(notifyXml);

        logger.info(result);
        writeResp(response, result);
    }

    /**
     * 微信支付统一下单API
     *
     * @param
     * @return
     * @throws Exception
     */
    @Verify(role = UserIdentity.ORDINARY)
    @ApiOperation(value = "商品支付", notes = "无")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderNumber", value = "订单编号", required = true)
    })
    @PostMapping("/wxPayOrder")
    public Map<String, String> wxPay(String orderNumber) throws Exception {
        Integer userId = redisTemplate.getUserUid();
        return wxPayService.wxPay(orderNumber, userId);
    }


}
