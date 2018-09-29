package com.pikaqiu.enums;

import lombok.Data;
import lombok.Getter;

/**
 * Created by Administrator on 2018/6/17.
 */
@Getter
public enum ResultEnum {
    PRODUCT_NOT_EXIST(1, "商品不存在"),
    PRODUCT_STOCK_ERROR(2, "库存不足");
    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
