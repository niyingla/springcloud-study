package com.pikaqiu.repository;

import com.pikaqiu.dataobject.ProductInfo;
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
 * Created by Administrator on 2018/6/16.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductInfoRepositoryTest {
    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Test
    public void findByProductStatus() throws Exception {
        List<ProductInfo> byProductStatus = productInfoRepository.findByProductStatus(0);
        System.out.println(byProductStatus.size() > 0);

    }

    @Test
    public void findByProductIdIn() throws Exception {
        List<String> list = Arrays.asList("157875227953464068");
        List<ProductInfo> byProductIdIn = productInfoRepository.findByProductIdIn(list);
        Assert.assertTrue(byProductIdIn.size() > 0);

    }


}