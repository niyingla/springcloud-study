package com.pikaqiu.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * Created by Administrator on 2018/7/18.
 */
@RestController
@DefaultProperties(defaultFallback = "defaultCuola")
public class HystrixController {

     //超时熔断
    @GetMapping("/listFor")
     //设置相关参数 比如超时时间  默认是1s
    //@HystrixCommand(fallbackMethod = "defaultCuola" ,commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1000")})
     public String getProductInfoList() {
         try {
             Thread.sleep(2000);
         } catch (InterruptedException e) {
             e.printStackTrace();
         }
         RestTemplate restTemplate = new RestTemplate();
         return restTemplate.postForObject("http://127.0.0.1:8762/product/listForOrder", Arrays.asList("157875227953464068"), String.class);

     }

    /**
     * 断路器熔断状态机  三种状态
     * open 打开状态 直接放回错误
     * closed关闭 一定次数 一定比例 转为打开
     * halfOpen 打开 circuitBreaker.sleepWindowInMilliseconds的时间 就会进入半熔断状态
     * 允许定量成功 如果调用都成功 就会关闭熔断 否则就会继续打开 重新及时
     * @param number
     * @return
     */
    @GetMapping("/listForOrder")
    //设置相关参数 比如超时时间  默认是1s
    @HystrixCommand(fallbackMethod = "heheda",
            commandProperties =
                    {@HystrixProperty(name = "circuitBreaker.enabled", value = "true"),//开启熔断 及时切断故障
                            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),//滚动时间窗口 最小错误请求次数  达到后熔断
                            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),//休眠时间窗
                            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),}) // 这里是10次 大于6次 错误百分比超过就会打开断路器
    public String getProductInfoList(@RequestParam("number") Integer number) {

        if (number % 2 == 0) {
            return "success";
        }
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject("http://127.0.0.1:8762/product/listForOrder", Arrays.asList("157875227953464068"), String.class);

    }


    public String heheda(Integer number) {
        return "太拥挤了，请稍候再试" + number;
    }

    public String defaultCuola() {
        return "太拥挤了，请稍候再试--默认";
    }

}
