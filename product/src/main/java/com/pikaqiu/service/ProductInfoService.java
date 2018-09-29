package com.pikaqiu.service;

import com.pikaqiu.dataobject.ProductInfo;
import com.pikaqiu.dto.CartDTO;

import java.util.List;

/**
 * Created by Administrator on 2018/6/17.
 */
public interface ProductInfoService {
    List<ProductInfo> findUpAll();

    List<ProductInfo> findList(List<String> productIdtList);

    void decreaseStock(List<CartDTO> list);
}
