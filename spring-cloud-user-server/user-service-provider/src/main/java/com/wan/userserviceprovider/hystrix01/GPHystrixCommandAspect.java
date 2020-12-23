package com.wan.userserviceprovider.hystrix01;

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
 * @Author: WanWenSheng
 * @Description:
 * @Dete: Created in 20:19 2020/12/22
 * @Modified By:
 */
@Component
@Aspect
public class GPHystrixCommandAspect {

    private   ExecutorService executorService = Executors.newFixedThreadPool(10);

    @Pointcut(value = "@annotation(GPHystrixCommand01)")
    public void Pointcut(){}

    @Around(value = "Pointcut()&&@annotation(gpHystrixCommand01)")
    public Object doPointCut(ProceedingJoinPoint joinPoint, GPHystrixCommand01 gpHystrixCommand01) throws InvocationTargetException, IllegalAccessException {
        int timeout = gpHystrixCommand01.timeout();
        //前置条件执行
        Future future = executorService.submit(() -> {
            try {
               return joinPoint.proceed();
            } catch (Throwable e) {
                e.printStackTrace();
            }
            return null;
        });
        Object result=null;

        try {
            result = future.get(timeout, TimeUnit.MILLISECONDS);
        } catch (InterruptedException |TimeoutException | ExecutionException e) {
            e.printStackTrace();
            return fullBack(joinPoint,gpHystrixCommand01);
        } 
        return result;
    }

    private Object fullBack(ProceedingJoinPoint joinPoint, GPHystrixCommand01 gpHystrixCommand01) throws InvocationTargetException, IllegalAccessException {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        //以上是获取被代理的方法的参数和Method
        //得到fallback方法
        Class<?>[] parameterTypes = method.getParameterTypes();

        try {
            Method invokeMethod = joinPoint.getTarget().getClass().getMethod(gpHystrixCommand01.fallBack(), parameterTypes);
            invokeMethod.setAccessible(true);
            Object invoke = invokeMethod.invoke(joinPoint.getTarget(), joinPoint.getArgs());
            return invoke;

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }


        return null;
    }


}
