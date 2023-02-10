package com.f1soft.campaign.transaction.thread;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author Rashim Dhaubanjar
 */

@Slf4j
@Configuration
public class ThreadInitializer {


    @Bean(name = "taskExecutor")
    public AsyncTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        executor.setCorePoolSize(30);
        executor.setMaxPoolSize(30);
        executor.setQueueCapacity(30);
        executor.setThreadNamePrefix("fundTransferCbs_executor_thread");
        executor.setRejectedExecutionHandler(new RejectedTaskHandler());

        executor.initialize();
        return executor;
    }

    @Bean(name = "fundTransferCbs")
    public AsyncTaskExecutor getfundTransferCbsAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(5);
        executor.setQueueCapacity(5);
        executor.setThreadNamePrefix("fundTransferCbs_executor_thread");
        executor.setRejectedExecutionHandler(new RejectedTaskHandler());

        executor.initialize();
        return executor;
    }
}
