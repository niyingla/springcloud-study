package com.pikaqiu.controller;

import com.pikaqiu.client.ProductClient;
import com.pikaqiu.dataobject.ProductInfo;
import com.pikaqiu.dto.CartDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2018/6/17.
 */
@RestController
@Slf4j
public class ClientController {

    @Autowired
    private ProductClient productClient;

    @GetMapping("/getProductList")
    public String getProductList(){

        List<String> list = Arrays.asList("157875227953464068");

        List<ProductInfo> productInfos = productClient.listForOrder(list);

        log.info("response={}", productInfos);

        return "ok";
    }
    @GetMapping("/decreaseStock")
    public String decreaseStock(){
        List<CartDTO> list = new ArrayList<>();

        CartDTO cartDTO = new CartDTO();

        cartDTO.setProductId("164103465734242707");

        cartDTO.setProductQuantity(2);

        list.add(cartDTO);

        productClient.decreaseStock(list);

        return "getProductList ok";
    }
}
