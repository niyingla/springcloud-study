package com.pikaqiu.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/6/17.
 */
@Data
public class ResultVO<T> implements Serializable {

    private Integer code;

    private String msg;

    private T data;
}
