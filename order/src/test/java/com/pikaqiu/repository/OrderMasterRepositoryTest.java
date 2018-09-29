package com.pikaqiu.repository;

import com.pikaqiu.OrderApplicationTests;
import com.pikaqiu.dataobject.OrderMaster;
import com.pikaqiu.enums.OrderStatusEnum;
import com.pikaqiu.enums.PayStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Administrator on 2018/6/17.
 */
@Component
public class OrderMasterRepositoryTest extends OrderApplicationTests{

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Test
    public void testSave(){
        OrderMaster orderMaster = new OrderMaster();

        orderMaster.setOrderId("451242424");

        orderMaster.setBuyerOpenid("52341124");

        orderMaster.setBuyerName("pikaqiu");

        orderMaster.setBuyerPhone("524242452");

        orderMaster.setBuyerAddress("白石洲");

        orderMaster.setOrderAmount(BigDecimal.TEN);

        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());

        orderMaster.setPayStatus(PayStatusEnum.NEW.getCode());

        orderMaster.setCreateTime(new Date());

        orderMaster.setUpdateTime(new Date());

        OrderMaster save = orderMasterRepository.save(orderMaster);

        Assert.assertTrue(save != null);
    }

}