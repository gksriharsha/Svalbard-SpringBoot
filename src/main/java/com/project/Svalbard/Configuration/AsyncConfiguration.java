package com.project.Svalbard.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;


@Configuration
@EnableAsync
public class AsyncConfiguration {

    @Bean(name = "taskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor(); //TODO methods should be made async
        executor.setCorePoolSize(4);
        executor.setQueueCapacity(150);
        executor.setMaxPoolSize(6);
        executor.setThreadNamePrefix("Multi - ");
        executor.initialize();
        return executor;
    }
}
