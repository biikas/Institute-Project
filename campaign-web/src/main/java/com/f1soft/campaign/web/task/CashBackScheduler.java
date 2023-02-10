package com.f1soft.campaign.web.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Prajwol Hada
 */
@Slf4j
@Component
public class CashBackScheduler {

    @Autowired
    private RedeemJob cashBackJob;

//    @Scheduled(initialDelay = 1000, fixedDelay = 30000)
//    public void cashbackProcessor() {
//        cashBackJob.job();
//    }
}
