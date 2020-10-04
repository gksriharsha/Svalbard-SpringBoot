package com.project.Svalbard.Aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LoggingAspect {
    private Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("@annotation(com.project.Svalbard.Annotations.LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object proceed = joinPoint.proceed();

        long executionTime = System.currentTimeMillis() - start;

        Object[] args = joinPoint.getArgs();

        logger.info(joinPoint.getSignature().toShortString() + " method has executed in " + executionTime + " ms");

        return proceed;
    }

    @Pointcut("execution(* com.project.Svalbard.Controller.AnalyticsController.performOperation(..))")
    public void AnalyticsTimer() {
    }

    @Around("AnalyticsTimer()")
    public Object logAnalyticsExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object proceed = joinPoint.proceed();

        long executionTime = System.currentTimeMillis() - start;

        Object[] args = joinPoint.getArgs();

        if (args[1].equals("all")) {
            logger.info(args[0] + " for all metrics are executed in " + executionTime + " ms");
        } else {
            logger.info(args[0] + " for the metric " + args[1] + " is executed in " + executionTime + " ms");
        }

        return proceed;
    }
}
