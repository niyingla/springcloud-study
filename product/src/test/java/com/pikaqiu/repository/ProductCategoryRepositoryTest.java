package com.pikaqiu.repository;

import com.pikaqiu.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2018/6/17.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductCategoryRepositoryTest {
    @Autowired
    ProductCategoryRepository ProductCategoryRepository;

    @Test
    public void findByCategoryTypeIn() throws Exception {
        List<ProductCategory> byCategoryTypeIn = ProductCategoryRepository.findByCategoryTypeIn(Arrays.asList(11, 12));
        Assert.assertTrue(byCategoryTypeIn.size() > 0);
    }

}