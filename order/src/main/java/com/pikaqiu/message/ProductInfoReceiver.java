package com.pikaqiu.message;

import com.alibaba.fastjson.JSON;
import com.pikaqiu.dataobject.ProductInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018/7/15.
 */
@Component
@Slf4j
public class ProductInfoReceiver {

    private static final String PRODUCT_STOCK_TEMPLATE = "PRODUCT_STOCK_TEMPLATE_%s";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RabbitListener(queuesToDeclare = @Queue("productInfo"))
    public void process(String message) {

        ProductInfo productInfo = JSON.parseObject(message, ProductInfo.class);

        //第二个会放入第一个【{}】 作为名称 第三个 会放入{} 作为内容
        log.info("从队列【{}】接受的消息:{}", "productInfo", productInfo);

        stringRedisTemplate.opsForValue().set(String.format(ProductInfoReceiver.PRODUCT_STOCK_TEMPLATE, productInfo.getProductId()), productInfo.getProductStock() + "");
    }

}
