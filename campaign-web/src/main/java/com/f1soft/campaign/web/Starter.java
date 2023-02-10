package com.f1soft.campaign.web;


import com.f1soft.campaign.web.task.RedeemJob;
import com.f1soft.campaign.web.task.customRedeem.CustomRedeemJob;
import com.f1soft.campaign.web.task.fileprocess.FileProcessJob;
import com.f1soft.campaign.web.task.nthTransactionRedeem.NTransactionRedeemJob;
import com.f1soft.campaign.web.task.registeredCustomer.EligibleRegisteredCustomerJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Starter {

    @Autowired
    private RedeemJob redeemJob;
    @Autowired
    private EligibleRegisteredCustomerJob eligibleRegisteredCustomerJob;
    @Autowired
    private NTransactionRedeemJob nTransactionRedeemJob;
    @Autowired
    private CustomRedeemJob customRedeemJob;
    @Autowired
    private FileProcessJob fileProcessJob;

    @Scheduled(initialDelay = 1000 * 30, fixedDelay = Long.MAX_VALUE)
    public void run() {
        log.info("Starting new job starter");
        redeemJob.job();
        eligibleRegisteredCustomerJob.job();
        nTransactionRedeemJob.job();
        customRedeemJob.job();
        fileProcessJob.job();
    }
}
