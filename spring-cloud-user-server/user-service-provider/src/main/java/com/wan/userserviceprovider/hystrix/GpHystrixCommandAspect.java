package com.wan.userserviceprovider.hystrix;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.*;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2227324689
 * http://www.gupaoedu.com
 **/
@Component
@Aspect
public class GpHystrixCommandAspect {

    ExecutorService executorService= Executors.newFixedThreadPool(10);

    @Pointcut(value = "@annotation(GpHystrixCommand)")
    public void pointCut(){}

    @Around(value = "pointCut()&&@annotation(hystrixCommand)")
    public Object doPointCut(ProceedingJoinPoint joinPoint,GpHystrixCommand hystrixCommand) throws InterruptedException, ExecutionException, TimeoutException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        int timeout=hystrixCommand.timeout();
        //前置的判断逻辑
        Future future=executorService.submit(()->{
            try {
                return joinPoint.proceed(); //执行目标方法
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            return null;
        });
        Object result;
        try {
            result=future.get(timeout, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
            future.cancel(true);
            // ？
            if(StringUtils.isBlank(hystrixCommand.fallback())){
                throw e;
            }
            //调用fallback
            result=invokeFallback(joinPoint,hystrixCommand.fallback());
        }
        return result;
    }

    private Object invokeFallback(ProceedingJoinPoint joinPoint,String fallback) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        MethodSignature signature=(MethodSignature)joinPoint.getSignature();
        Method method=signature.getMethod();
        Class<?>[] parameterTypes=method.getParameterTypes();
        //以上是获取被代理的方法的参数和Method
        //得到fallback方法
        try {
            Method fallbackMethod=joinPoint.getTarget().getClass().getMethod(fallback,parameterTypes);
            fallbackMethod.setAccessible(true);
            //完成反射调用
            return fallbackMethod.invoke(joinPoint.getTarget(),joinPoint.getArgs());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

}
