package com.f1soft.campaign.web.service.campaign.impl;

import com.f1soft.campaign.common.constant.MsgConstant;
import com.f1soft.campaign.common.dto.PageResponse;
import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.common.exception.InvalidDataException;
import com.f1soft.campaign.common.util.ResponseMsg;
import com.f1soft.campaign.entities.model.Campaign;
import com.f1soft.campaign.entities.model.OfferTransaction;
import com.f1soft.campaign.repository.CampaignRepository;
import com.f1soft.campaign.repository.OfferTransactionRepository;
import com.f1soft.campaign.repository.Util.FieldQueryParameter;
import com.f1soft.campaign.repository.Util.SearchQueryParameter;
import com.f1soft.campaign.web.campaign.helper.CampaignHelper;
import com.f1soft.campaign.web.dto.OfferTransactionListResponse;
import com.f1soft.campaign.web.dto.OfferTransactionResponse;
import com.f1soft.campaign.web.mapper.OfferTransactionMapper;
import com.f1soft.campaign.web.service.campaign.TransactionDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Prajwol Hada
 */
@Slf4j
@Component
public class TransactionDataServiceImpl implements TransactionDataService {

    @Autowired
    private OfferTransactionRepository offerTransactionRepository;
    @Autowired
    private CampaignRepository campaignRepository;

    @Override
    public ServerResponse getAllTransactionList() {
        List<OfferTransaction> offerTransactions = offerTransactionRepository.getAllOfferList();

        OfferTransactionListResponse offerTransactionListResponse = OfferTransactionMapper.convertToOfferTransaction(offerTransactions);

        return ResponseMsg.successResponse(MsgConstant.Data.SUCCESS, offerTransactionListResponse);
    }


    @Override
    public ServerResponse searchTransaction(SearchQueryParameter searchQueryParameter) {
        log.debug("Getting offer transaction for search query: {}", searchQueryParameter);

        List<FieldQueryParameter> fieldQueryParameterList = CampaignHelper.getQueryParameterListForTransactionFilter(searchQueryParameter);

        Page<OfferTransaction> offerTransactions = offerTransactionRepository
                .findAll(offerTransactionRepository.searchQuery(fieldQueryParameterList),
                        PageRequest.of(searchQueryParameter.getPage(), searchQueryParameter.getSize(), Sort.Direction.ASC, "id"));

        OfferTransactionListResponse offerTransactionListResponse = OfferTransactionMapper.convertToOfferTransaction(offerTransactions.getContent());
        PageResponse pageResponse = new PageResponse(offerTransactionListResponse.getTransactionList(), offerTransactions.getTotalElements());

        return ResponseMsg.successResponse(MsgConstant.Data.SUCCESS, pageResponse);
    }

    @Override
    public ServerResponse transactionDetail(Long id) {

        Optional<OfferTransaction> optionalOfferTransaction = offerTransactionRepository.findById(id);

        if (!optionalOfferTransaction.isPresent()) {
            throw new InvalidDataException(ResponseMsg.failureResponse(MsgConstant.INVALID_DATA));
        }

        OfferTransactionResponse transactionDetailResponse = OfferTransactionMapper.convertToOfferTransactionResponse(optionalOfferTransaction.get());
        return ResponseMsg.successResponse(MsgConstant.Data.SUCCESS, transactionDetailResponse);
    }

    @Override
    public ServerResponse searchManualTransaction(SearchQueryParameter searchQueryParameter) {

        List<FieldQueryParameter> fieldQueryParameterList = CampaignHelper.getQueryParamsForManualTransactionFilter(searchQueryParameter);

        Page<OfferTransaction> offerTransactions = offerTransactionRepository
                .findAll(offerTransactionRepository.searchQuery(fieldQueryParameterList),
                        PageRequest.of(searchQueryParameter.getPage(), searchQueryParameter.getSize(), Sort.Direction.ASC, "id"));

        OfferTransactionListResponse offerTransactionListResponse = OfferTransactionMapper.convertToOfferTransaction(offerTransactions.getContent());
        PageResponse pageResponse = new PageResponse(offerTransactionListResponse.getTransactionList(), offerTransactions.getTotalElements());

        return ResponseMsg.successResponse(MsgConstant.Data.SUCCESS, pageResponse);

    }

    @Override
    public ServerResponse transactionDetailByCampaignId(Long id) {
        Optional<Campaign> campaign = campaignRepository.findById(id);
        if (!campaign.isPresent()) {
            throw new InvalidDataException(ResponseMsg.failureResponse(MsgConstant.INVALID_DATA));
        }
        List<OfferTransaction> offerTransactions = offerTransactionRepository.getOfferTransactionByCampaignId(campaign.get().getId()).
                stream().limit(5).collect(Collectors.toList());

        OfferTransactionListResponse offerTransactionResponse = OfferTransactionMapper.convertToOfferTransaction(offerTransactions);
        return ResponseMsg.successResponse(MsgConstant.Data.SUCCESS, offerTransactionResponse);
    }

}
