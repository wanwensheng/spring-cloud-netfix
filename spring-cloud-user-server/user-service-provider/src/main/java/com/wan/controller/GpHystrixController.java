package com.wan.controller;

import com.wan.userserviceprovider.hystrix.GpHystrixCommand;
import com.wan.userserviceprovider.hystrix01.GPHystrixCommand01;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2227324689
 * http://www.gupaoedu.com
 **/
@RestController
public class GpHystrixController {

    @Autowired
    RestTemplate restTemplate;

    @GpHystrixCommand(fallback = "fallback",timeout = 1000)
    @GetMapping("/hystrix/test")
    public String test(){
        return restTemplate.getForObject("http://localhost:8082/orders",String.class);
    }

    public String fallback(){
        return "请求被降级";
    }


    @GPHystrixCommand01(fallBack = "fallback",timeout = 5000)
    @GetMapping("/hystrix/test01")
    public String test01(){
        return restTemplate.getForObject("http://localhost:8082/orders",String.class);
    }

}
