package com.pikaqiu.vo;

import lombok.Data;

/**
 * Created by Administrator on 2018/6/17.
 */
@Data
public class ResultVO<T> {

    private Integer code;

    private String msg;

    private T data;
}
