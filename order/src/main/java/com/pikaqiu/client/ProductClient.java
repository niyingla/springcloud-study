package com.pikaqiu.client;

import com.pikaqiu.dataobject.ProductInfo;
import com.pikaqiu.dto.CartDTO;
import com.pikaqiu.utils.Fallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Created by Administrator on 2018/6/17.
 */
@FeignClient(name = "product", fallback = Fallback.class)
public interface ProductClient {

    @PostMapping("/product/listForOrder")
    List<ProductInfo> listForOrder(@RequestBody List<String> productIdList);

    @PostMapping("/product/decreaseStock")
    void decreaseStock(@RequestBody List<CartDTO> cartDTOs);
}
