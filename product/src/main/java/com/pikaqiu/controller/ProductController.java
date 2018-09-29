package com.pikaqiu.controller;

import com.pikaqiu.dataobject.ProductCategory;
import com.pikaqiu.dataobject.ProductInfo;
import com.pikaqiu.dto.CartDTO;
import com.pikaqiu.service.CategoryService;
import com.pikaqiu.service.ProductInfoService;
import com.pikaqiu.utils.ResultVOUtil;
import com.pikaqiu.vo.ProductInfoVO;
import com.pikaqiu.vo.ProductVO;
import com.pikaqiu.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 肖烨
 * Created by Administrator on 2018/6/16.
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ResultVO<ProductInfo> list() {
        //1 查询所有在架的商品
        List<ProductInfo> upAll = productInfoService.findUpAll();
        //2 拿到所有分类类型
        List<Integer> categoryType = upAll.stream().map(item -> item.getCategoryType()).collect(Collectors.toList());
        //3 根据类型id 拿到所有的的分类
        List<ProductCategory> categorys = categoryService.findByCategoryTypeIn(categoryType);
        //4 构造数据
        List<ProductVO> list = new ArrayList<>();


        for (int i = 0; i < categorys.size(); i++) {

            ProductCategory productCategory = categorys.get(i);

            ProductVO productVO = new ProductVO();

            productVO.setCategoryName(productCategory.getCategoryName());

            productVO.setCategoryType(productCategory.getCategoryType());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();

            for (ProductInfo productInfo : upAll) {

                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {

                    ProductInfoVO productInfoVO = new ProductInfoVO();

                    BeanUtils.copyProperties(productInfo, productInfoVO);

                    productInfoVOList.add(productInfoVO);

                }

            }
            productVO.setProductInfoVOList(productInfoVOList);

            list.add(productVO);

        }
        return ResultVOUtil.success(list);
    }

    /**
     * 获取商品列表
     *
     * @param productIdList
     * @return
     */
    @PostMapping("/listForOrder")
    public List<ProductInfo> listForOrder(@RequestBody List<String> productIdList) {

        return productInfoService.findList(productIdList);
    }

    @PostMapping("/decreaseStock")
    public void decreaseStock(@RequestBody List<CartDTO> cartDTOs) {
        productInfoService.decreaseStock(cartDTOs);
    }
}
