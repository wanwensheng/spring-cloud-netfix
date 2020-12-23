package com.wan.userserviceprovider.hystrix01;

import java.lang.annotation.*;

/**
 * @Author: WanWenSheng
 * @Description:
 * @Dete: Created in 20:16 2020/12/22
 * @Modified By:
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GPHystrixCommand01 {

    int timeout() default 1000;


    String fallBack() default "";
}
