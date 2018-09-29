package com.pikaqiu.service.impl;

import com.pikaqiu.ProductApplicationTests;
import com.pikaqiu.dataobject.ProductInfo;
import com.pikaqiu.dto.CartDTO;
import com.pikaqiu.service.ProductInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2018/6/17.
 */
@Component
public class ProductInfoServiceImplTest extends ProductApplicationTests {

    @Autowired
    private ProductInfoService productInfoService;

    @Test
    public void findUpAll() throws Exception {
        List<ProductInfo> upAll = productInfoService.findUpAll();
        System.out.println(upAll.size());

    }

    @Test
    public void findList() throws Exception {

        List<String> list = Arrays.asList("157875227953464068");

        List<ProductInfo> upAll = productInfoService.findList(list);

        System.out.println(upAll.size());

    }

    @Test
    public void decreaseStock() throws Exception {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setProductId("164103465734242707");
        cartDTO.setProductQuantity(2);
        List<CartDTO> list = new ArrayList<>();
        list.add(cartDTO);
        productInfoService.decreaseStock(list);

    }
}