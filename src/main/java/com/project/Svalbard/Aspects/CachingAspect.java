package com.project.Svalbard.Aspects;

import com.project.Svalbard.Annotations.CacheValue;
import com.project.Svalbard.Annotations.ClearCache;
import com.project.Svalbard.Model.db.Classification;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Aspect
@Component
public class CachingAspect {

    private ConcurrentHashMap<HashMap<String,List<Long>>,HashMap<String,CompletableFuture<Double>>> cacheMap = new ConcurrentHashMap<>();

    @Around("@annotation(com.project.Svalbard.Annotations.CacheValue)")
    public CompletableFuture<Double> storeCache(ProceedingJoinPoint joinPoint) throws Throwable {

        // Get all the method params
        Object[] parameters = joinPoint.getArgs();
        // This is to check if the metric is being measured for the same classification objects

        List<Long> idlist = ((List<Classification>) parameters[0])
                .stream().map(Classification::getId).collect(Collectors.toList());

        HashMap<String,List<Long>> keyMap = new HashMap<>();

        keyMap.put((String) parameters[2], idlist);

        HashMap<String,CompletableFuture<Double>> parameterMap = new HashMap<>();

        if(!cacheMap.isEmpty())
        {
            if(cacheMap.containsKey(keyMap) && cacheMap.get(keyMap).containsKey(parameters[1]))
            {
                System.out.println("Cache hit");
                return cacheMap.get(keyMap).get(parameters[1]);
            }
            parameterMap = cacheMap.get(keyMap);
        }
        CompletableFuture<Double> returnObject = (CompletableFuture<Double>) joinPoint.proceed(parameters);
        System.out.println("Cache Miss");
        parameterMap.put((String) parameters[1],returnObject );
        cacheMap.put(keyMap, parameterMap);
        return returnObject;
    }

    @After("@annotation(com.project.Svalbard.Annotations.ClearCache)")
    public void clearCache(JoinPoint call)
    {
        MethodSignature signature = (MethodSignature) call.getSignature();
        ClearCache annotation = signature.getMethod().getAnnotation(ClearCache.class);
        if(annotation.operation().equals("all") || annotation.operation().isEmpty())
        {
            cacheMap.clear();
            return ;
        }
        if(cacheMap.containsKey(annotation.operation()))
        {
            if(!annotation.metric().isEmpty() && cacheMap.get(annotation.operation()).containsKey(annotation.metric()))
            {
                cacheMap.get(annotation.operation()).remove(annotation.metric());
                return ;
            }
            cacheMap.remove(annotation.operation());
        }
    }
}
