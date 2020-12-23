package com.wan.userserviceprovider.service;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import org.springframework.web.client.RestTemplate;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2227324689
 * http://www.gupaoedu.com
 **/
public class HystrixCommandService extends HystrixCommand<String>{
    int num;
    RestTemplate restTemplate;
    public HystrixCommandService(int num,RestTemplate restTemplate){
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("order-service")).
                andCommandPropertiesDefaults(HystrixCommandProperties.Setter().
                        withCircuitBreakerEnabled(true).
                        withCircuitBreakerRequestVolumeThreshold(5))); //TODO
        this.num=num;
        this.restTemplate=restTemplate;
    }
    @Override
    protected String run() throws Exception {
        if(num%2==0){
            return "正常访问";
        }
        //发起远程请求
        return restTemplate.getForObject("http://localhost:8082/orders",String.class);
    }

    //如果Hystrix触发了降级，那么将会执行fallback方法
    @Override
    protected String getFallback() {
        return "请求被降级";
    }
}
