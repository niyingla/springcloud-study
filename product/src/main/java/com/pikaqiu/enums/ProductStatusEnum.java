package com.pikaqiu.enums;

import lombok.Getter;

/**
 * Created by Administrator on 2018/6/17.
 */
@Getter
public enum ProductStatusEnum {
    UP(0, "在架"),
    DOWN(1, "下架");

    private Integer code;
    private String massage;

    ProductStatusEnum(Integer code, String massage) {
        this.code = code;
        this.massage = massage;
    }
}
