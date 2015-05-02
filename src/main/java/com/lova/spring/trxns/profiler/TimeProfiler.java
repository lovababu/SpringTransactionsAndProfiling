package com.lova.spring.trxns.profiler;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * Created by Lovababu on 5/2/2015.
 */
@Aspect
@Component
@Slf4j
public class TimeProfiler implements Ordered{

    @Pointcut(value = "execution(* com.lova.spring.trxns.service.*.*(..))")
    private void measureTimePointCut(){}

    @Before(value = "measureTimePointCut()")
    public void beforeMethod(){
        log.info("Before Executing service method..");
    }

    // this method *is* the around advice
    @Around(value = "measureTimePointCut()")
    public Object timeProfiler(ProceedingJoinPoint call) throws Throwable {
        Object returnValue;
        StopWatch clock = new StopWatch(getClass().getName());
        try {
            clock.start(call.toShortString());
            returnValue = call.proceed();
        } finally {
            clock.stop();
            System.out.println("====================== TimeProfiler Start =======================\n");
            System.out.println(clock.prettyPrint());
            System.out.println("====================== TimeProfiler End =======================");
        }
        return returnValue;
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
