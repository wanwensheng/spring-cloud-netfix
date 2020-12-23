package com.wan.controller;

import com.wan.springcloud.clients.OrderServiceFeignClient;
import com.wan.springcloud.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2227324689
 * http://www.gupaoedu.com
 **/
@RestController
public class HystrixFeignController {

    @Autowired
    OrderServiceFeignClient orderServiceFeignClient;

    @GetMapping("/hystrix/feign/order")
    public String queryOrder(){
        return orderServiceFeignClient.orders();
    }

    @PostMapping("/hystrix/feign/order")
    public String insertOrder(){
        OrderDto orderDto=new OrderDto();
        orderDto.setOrderId("GP0001");
        return orderServiceFeignClient.insert(orderDto)>0?"SUCCESS":"FAILED";
    }

    /*@GetMapping("/hystrix/test")
    public String test(){

    }*/


}
