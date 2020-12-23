package com.wan.profiledemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2227324689
 * http://www.gupaoedu.com
 **/
@Configuration
public class ProfileConfiguration {

    @Profile("dev")
    @Bean
    public ProfileService profileServiceDev(){
        return new ProfileService("dev");
    }

    @Profile("prd")
    @Bean
    public ProfileService profileServicePrd(){
        return new ProfileService("prd");
    }
}
