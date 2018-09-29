package com.pikaqiu.enums;

import lombok.Getter;

/**
 * Created by Administrator on 2018/6/17.
 */
@Getter
public enum PayStatusEnum {

    NEW(0,"未支付"), FINISHED(1, "支付完成"), CANCEL(2, "取消支付");

    private Integer code;
    private String message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
