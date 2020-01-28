package com.pikaqiu.utils;

import com.pikaqiu.client.ProductClient;
import com.pikaqiu.dataobject.ProductInfo;
import com.pikaqiu.dto.CartDTO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Administrator on 2018/7/21.
 */
@Component
public class Fallback implements ProductClient {
    @Override
    public List<ProductInfo> listForOrder(List<String> productIdList) {
        System.out.println("feign fallback");
        return null;
    }

    @Override
    public void decreaseStock(List<CartDTO> cartDTOs) {

    }
}
