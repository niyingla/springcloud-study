package com.pikaqiu.comfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

/**
 * 全局允许跨域设置
 * Created by Administrator on 2018/7/18.
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter(){

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        //配置类 和 注解属性比较相似
        CorsConfiguration configuration = new CorsConfiguration();

        //是否支持cookie跨域
        configuration.setAllowCredentials(true);
        //允许跨域的域名
        configuration.setAllowedOrigins(Arrays.asList("*"));  // http:www.a.com
        //允许的请求头
        configuration.setAllowedHeaders(Arrays.asList("*"));
        //允许的请求方法  get post
        configuration.setAllowedMethods(Arrays.asList("*"));
        //这个时间段里 相同的跨域请求不再检查
        configuration.setMaxAge(300L);
        //设置到基本路径源中               有效路径
        source.registerCorsConfiguration("/**", configuration);

        return new CorsFilter(source);
    }
}
