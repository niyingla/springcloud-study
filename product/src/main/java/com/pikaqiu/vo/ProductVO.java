package com.pikaqiu.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pikaqiu.dataobject.ProductInfo;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Administrator on 2018/6/17.
 */
@Data
public class ProductVO {
    /*指定返回前段的字段名称*/
    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    List<ProductInfoVO> productInfoVOList;
}
