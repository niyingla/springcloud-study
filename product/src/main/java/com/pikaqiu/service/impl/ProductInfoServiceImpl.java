package com.pikaqiu.service.impl;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.pikaqiu.dataobject.ProductInfo;
import com.pikaqiu.dto.CartDTO;
import com.pikaqiu.enums.ProductStatusEnum;
import com.pikaqiu.enums.ResultEnum;
import com.pikaqiu.exception.ProductExcetion;
import com.pikaqiu.repository.ProductInfoRepository;
import com.pikaqiu.service.ProductInfoService;
import com.rabbitmq.tools.json.JSONUtil;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.PropertyAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Administrator on 2018/6/17.
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService {
    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Autowired
    private AmqpTemplate amqpTemplate;


    @Override
    public List<ProductInfo> findUpAll() {

        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());

    }

    @Override
    public List<ProductInfo> findList(List<String> productIdtList) {

        return productInfoRepository.findByProductIdIn(productIdtList);
    }

   /* @Override
    @Transactional
    public void decreaseStock(List<CartDTO> list) {
        for (CartDTO cartDTO : list) {

            //找到对应的数据库记录
            Optional<ProductInfo> byId = productInfoRepository.findById(cartDTO.getProductId());

            if (!byId.isPresent()) {

                throw new ProductExcetion(ResultEnum.PRODUCT_NOT_EXIST);
            }
            ProductInfo productInfo = byId.get();

            //获得减去后库存
            int count = productInfo.getProductStock() - cartDTO.getProductQuantity();

            if (count < 0) {
                throw new ProductExcetion(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            //设置减去后库存
            productInfo.setProductStock(count);

            productInfoRepository.save(productInfo);

            //直接就发送mq消息
            //没有接受方 就没有创建队列  所以不能发送 也可以手动创建队列
            amqpTemplate.convertAndSend("productInfo", JSON.toJSONString(productInfo));

        }
    }*/


    @Override
    public void decreaseStock(List<CartDTO> list) {

        List<ProductInfo> productInfos = decreaseStockProcess(list);

        amqpTemplate.convertAndSend("productInfo", JSON.toJSONString(productInfos));


    }

    @Transactional
    public List<ProductInfo> decreaseStockProcess(List<CartDTO> list) {


        List<ProductInfo> ProductInfoList = new ArrayList<>();

        for (CartDTO cartDTO : list) {

            //找到对应的数据库记录
            Optional<ProductInfo> byId = productInfoRepository.findById(cartDTO.getProductId());

            if (!byId.isPresent()) {

                throw new ProductExcetion(ResultEnum.PRODUCT_NOT_EXIST);
            }
            ProductInfo productInfo = byId.get();

            //获得减去后库存
            int count = productInfo.getProductStock() - cartDTO.getProductQuantity();

            if (count < 0) {
                throw new ProductExcetion(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            //设置减去后库存
            productInfo.setProductStock(count);

            productInfoRepository.save(productInfo);

            ProductInfoList.add(productInfo);
        }
        return ProductInfoList;
    }
}
