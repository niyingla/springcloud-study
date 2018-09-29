package com.pikaqiu.exception;

import com.pikaqiu.enums.ResultEnum;
import lombok.Data;

/**
 * Created by Administrator on 2018/6/17.
 */
@Data
public class ProductExcetion extends RuntimeException {
    private Integer code;

    private String msg;

    public ProductExcetion(String message, Integer code, String msg) {
        super(message);
        this.code = code;
        this.msg = msg;
    }

    public ProductExcetion(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMessage();
    }
}
