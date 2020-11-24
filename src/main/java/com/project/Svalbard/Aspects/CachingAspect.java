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
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Aspect
@Component
public class CachingAspect {

    private ConcurrentHashMap<HashMap<String,List<Long>>,HashMap<String,Double>> cacheMap = new ConcurrentHashMap<>();

    @Around("@annotation(com.project.Svalbard.Annotations.CacheValue)")
    public Map<String,Double> storeCache(ProceedingJoinPoint joinPoint) throws Throwable {

        // Get all the method params
        Object[] parameters = joinPoint.getArgs();
        // This is to check if the metric is being measured for the same classification objects

        List<Long> idlist = ((List<Classification>) parameters[0])
                .stream().map(Classification::getId).collect(Collectors.toList());

        HashMap<String,List<Long>> keyMap = new HashMap<>();

        keyMap.put((String) parameters[2], idlist); // {"Correlation",[classification ids]}

        HashMap<String,Double> parameterMap = new HashMap<>();

        if(!cacheMap.isEmpty())
        {
            if(cacheMap.containsKey(keyMap))
            {
                System.out.println("Cache hit");
                if(parameters[1].equals("all"))
                {
                    if(!keyMap.isEmpty())
                    {
                        parameters[3] = cacheMap.get(keyMap);
                        System.out.println("Present keys "+cacheMap.get(keyMap).keySet());
                    }
                    HashMap<String,Double> valueset = (HashMap<String, Double>) joinPoint.proceed(parameters);
                    valueset.forEach((k,v)->{
                        if(!cacheMap.get(keyMap).containsKey(k))
                        {
                            cacheMap.get(keyMap).put(k, v);
                        }
                    });
                    return valueset;
                }
                else
                {
                    if(cacheMap.get(keyMap).containsKey(parameters[1]))
                    {
                        return new HashMap<String,Double>(){{
                            put((String)parameters[1],cacheMap.get(keyMap).get(parameters[1]));
                        }};
                    }
                }
            }
        }
        HashMap<String,Double> valueset = (HashMap<String, Double>) joinPoint.proceed(parameters);
        System.out.println("Cache miss");
        if(!valueset.isEmpty())
        {
            cacheMap.put(keyMap, valueset);
        }
        return valueset;
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
