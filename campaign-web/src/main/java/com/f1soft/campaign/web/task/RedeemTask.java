package com.f1soft.campaign.web.task;

import com.f1soft.campaign.common.config.application.SystemConfig;
import com.f1soft.campaign.common.util.ThreadUtil;
import com.f1soft.campaign.entities.model.OfferTransaction;
import com.f1soft.campaign.repository.OfferTransactionRepository;
import com.f1soft.campaign.web.constant.RunnerConfigConstant;
import com.f1soft.campaign.web.refund.RedeemExecutor;
import com.f1soft.campaign.web.refund.dto.JobResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Prajwol Hada
 */
@Component
@Slf4j
public class RedeemTask {

    @Autowired
    private OfferTransactionRepository offerTransactionRepository;

    @Autowired
    private RedeemExecutor redeemExecutor;

    @Autowired
    private SystemConfig systemConfig;

    @Async
    public void call() {

        Integer recordToFetch = Integer.parseInt(systemConfig.runnerConfig(RunnerConfigConstant.REDEEM_RECORDS_TO_FETCH));
        while (true) {
            List<OfferTransaction> offerTransactionList = offerTransactionRepository.getOfferTransactionsToProcessForRedeem(recordToFetch);
            log.debug("Cashback list size : {}", offerTransactionList.size());

            if (offerTransactionList.size() > 0) {

                List<Long> offerTransactionIdList = offerTransactionList.stream()
                        .map(OfferTransaction::getId)
                        .collect(Collectors.toList());

                JobResponse jobResponse = redeemExecutor.process(offerTransactionIdList);
            } else {
                ThreadUtil.sleepForSecond(60);
            }
        }
    }
}
