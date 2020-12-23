package com.wan.controller;

import com.netflix.discovery.converters.Auto;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2227324689
 * http://www.gupaoedu.com
 **/
@RestController
public class HystrixController {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(commandProperties = {
            @HystrixProperty(name="circuitBreaker.enabled",value ="true"),
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value = "5"),
            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value = "5000"),
            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value = "50")
    },fallbackMethod = "fallback",
    groupKey = "",threadPoolKey = "order-service")
    @GetMapping("/hystrix/order/{num}")
    public String queryOrder(@PathVariable("num")int num){
        if(num%2==0){
            return "正常访问";
        }
        //restTemplate默认有一个请求超时时间
        return  restTemplate.getForObject("http://localhost:8082/orders",String.class);
    }

    @HystrixCommand(fallbackMethod ="timeoutFallback",commandProperties = {
        @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "2000"),
    })
    @GetMapping("/hystrix/timeout")
    public String queryOrderTimeout(){
        return  restTemplate.getForObject("http://localhost:8082/orders",String.class);
    }

    public String fallback(int num){
        return "请求被降级";
    }

    public String timeoutFallback(){
        return "请求超时，请求被降级";
    }
}
