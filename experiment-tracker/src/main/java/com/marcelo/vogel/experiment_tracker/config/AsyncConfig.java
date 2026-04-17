package com.marcelo.vogel.experiment_tracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
public class AsyncConfig {

    @Bean(name = "experimentExecutor")
    public Executor experimentExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        executor.setCorePoolSize(2);   // always active threads
        executor.setMaxPoolSize(5);    // max threads under load
        executor.setQueueCapacity(10); // tasks waiting in queue

        executor.setThreadNamePrefix("experiment-");

        executor.initialize();
        return executor;
    }
}