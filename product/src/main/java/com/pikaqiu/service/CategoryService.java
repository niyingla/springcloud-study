package com.pikaqiu.service;

import com.pikaqiu.dataobject.ProductCategory;

import java.util.List;

/**
 * Created by Administrator on 2018/6/17.
 */
public interface CategoryService {
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypes);

}
