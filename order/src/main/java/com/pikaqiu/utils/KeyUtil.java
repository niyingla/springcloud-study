package com.pikaqiu.utils;

import java.util.Random;

/**
 * Created by Administrator on 2018/6/17.
 */
public class KeyUtil {
    /**
     * 生成唯一的主键
     * 格式: 时间+随机数
     */
    public static synchronized String genUniqueKey() {
        Random random = new Random();
        int randNum = random.nextInt(90000) + 100000;
        return System.currentTimeMillis() + "" + randNum;
    }
}
