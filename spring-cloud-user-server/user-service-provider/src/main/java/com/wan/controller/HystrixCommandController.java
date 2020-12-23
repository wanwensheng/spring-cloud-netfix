package com.wan.controller;

import com.netflix.discovery.converters.Auto;
import com.wan.userserviceprovider.service.HystrixCommandService;
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
public class HystrixCommandController {

    @Autowired
    RestTemplate restTemplate;


    @GetMapping("/hystrix/command/{num}")
    public String hystrixCommand(@PathVariable("num")int num){
        HystrixCommandService hystrixCommandService=new HystrixCommandService(num,restTemplate);
        return  hystrixCommandService.execute();//执行
    }
}
