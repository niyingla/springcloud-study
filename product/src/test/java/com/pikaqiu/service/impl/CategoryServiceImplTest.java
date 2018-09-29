package com.pikaqiu.service.impl;

import com.pikaqiu.ProductApplicationTests;
import com.pikaqiu.dataobject.ProductCategory;
import com.pikaqiu.service.CategoryService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2018/6/17.
 */
@Component
public class CategoryServiceImplTest extends ProductApplicationTests{

    @Autowired
    private CategoryService categoryService;

    @Test
    public void findByCategoryTypeIn() throws Exception {

        List<Integer> integers = Arrays.asList(11, 22);

        List<ProductCategory> byCategoryTypeIn = categoryService.findByCategoryTypeIn(integers);

        System.out.println(byCategoryTypeIn);

    }

}