package com.pikaqiu.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018/7/8.
 */
@Data
@Component
//前缀
@ConfigurationProperties("girl")
//刷新配置
@RefreshScope
public class GirlConfig {
    @Override
    public String toString() {
        return "GirlConfig{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    private String name;
    private Integer age;
}
