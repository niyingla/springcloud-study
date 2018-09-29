package com.pikaqiu.utils;

import com.pikaqiu.vo.ResultVO;

/**
 * Created by Administrator on 2018/6/17.
 */
public class ResultVOUtil {
    /**
     * @param data
     * @return
     */
    public static ResultVO success(Object data) {

        ResultVO resultVO = new ResultVO<>();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(data);
        return resultVO;
    }
}
