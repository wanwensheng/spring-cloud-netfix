package com.wan.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2227324689
 * http://www.gupaoedu.com
 **/
@RefreshScope //标记当前类的bean动态加载
@RestController
public class ConfigController {

    //Environement 去获得指定的配置.
    @Value("${hello}")
    private String txt;

    @GetMapping("/config")
    public String config(){
        return txt;
    }

}
