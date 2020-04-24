package com.zhancheng.core.util;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.zhancheng.core.config.exception.MyException;
import com.zhancheng.core.constant.CodeMsg;
import com.zhancheng.core.dao.OrderProductMapper;
import com.zhancheng.core.dao.ProductMapper;
import com.zhancheng.core.dto.OrderProductDTO;
import com.zhancheng.core.entity.OrderProduct;
import com.zhancheng.core.entity.Product;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author BianShuHeng
 * @decription
 * @project OrderUtil
 * @date 2019/11/21 16:58
 */
@Component
public class OrderUtil {

    @Resource
    private ProductMapper productMapper;

    @Resource
    private OrderProductMapper orderProductMapper;

    @Transactional(rollbackFor = Exception.class)
    public BigDecimal addOrderProduct(List<OrderProductDTO> orderProductDTOList, Integer orderId, Integer userId) {

        if (ObjectUtil.isNull(orderProductDTOList)) {
            throw new MyException(CodeMsg.ORDER_PRODUCT_IS_NULL);
        }

        BigDecimal totalPrice = new BigDecimal("0");
        for (OrderProductDTO orderProductDto : orderProductDTOList) {

            Integer pid = orderProductDto.getProductId();
            Integer productNum = orderProductDto.getProductNum();
            BigDecimal price = orderProductDto.getProductPrice();

            Product product = productMapper.selectById(pid);

            if (ObjectUtil.isNull(product)){
                throw new MyException(CodeMsg.PRODUCT_NOT_EXISTED);
            }
            // 商品库存
            Integer totalStock = product.getTotalStock();

            if (productNum < 1 || productNum > totalStock){
                throw new MyException(CodeMsg.GOODS_NUM_ERROR);
            }

            //单价乘数量。获得单个商品总价
            BigDecimal multiply = price.multiply(new BigDecimal(productNum));
            //累积总价钱
            totalPrice = totalPrice.add(multiply);

            OrderProduct orderProduct = new OrderProduct();
            BeanUtil.copyProperties(orderProductDto, orderProduct);

            orderProduct.setUserId(userId).setOrderId(orderId);
            orderProductMapper.insert(orderProduct);

            product.setTotalStock(totalStock - productNum);
            product.updateById();
        }

       return totalPrice;
    }
}
