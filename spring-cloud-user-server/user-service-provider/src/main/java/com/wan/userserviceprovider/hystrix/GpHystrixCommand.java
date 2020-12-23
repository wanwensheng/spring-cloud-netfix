package com.wan.userserviceprovider.hystrix;

import java.lang.annotation.*;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2227324689
 * http://www.gupaoedu.com
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GpHystrixCommand {

    /**
     * 默认超时时间
     * @return
     */
    int timeout() default 1000;

    /**
     * 回退方法
     * @return
     */
    String fallback() default "";

}
