package com.f1soft.campaign.web.service.campaign.impl;

import com.f1soft.campaign.common.constant.MsgConstant;
import com.f1soft.campaign.common.dto.PageResponse;
import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.common.exception.InvalidDataException;
import com.f1soft.campaign.common.helper.QueryFilterHelper;
import com.f1soft.campaign.common.util.ResponseMsg;
import com.f1soft.campaign.entities.model.GiftCard;
import com.f1soft.campaign.entities.model.GiftCardProvider;
import com.f1soft.campaign.repository.GiftCardDetailRepository;
import com.f1soft.campaign.repository.GiftCardProviderRepository;
import com.f1soft.campaign.repository.Util.FieldQueryParameter;
import com.f1soft.campaign.repository.Util.SearchQueryParameter;
import com.f1soft.campaign.web.campaign.helper.CampaignHelper;
import com.f1soft.campaign.web.config.provider.LoginProvider;
import com.f1soft.campaign.web.dto.response.GiftCardListResponse;
import com.f1soft.campaign.web.dto.response.GiftCardProviderDetail;
import com.f1soft.campaign.web.dto.response.GiftCardProviderListResponse;
import com.f1soft.campaign.web.dto.response.GiftCardResponse;
import com.f1soft.campaign.web.giftcard.dto.request.GiftCardCreateRequest;
import com.f1soft.campaign.web.giftcard.dto.request.GiftCardModifyRequest;
import com.f1soft.campaign.web.giftcard.dto.request.GiftCardProviderCreateRequest;
import com.f1soft.campaign.web.giftcard.dto.request.UpdateGiftCardStatusRequest;
import com.f1soft.campaign.web.giftcard.manager.GiftCardManager;
import com.f1soft.campaign.web.mapper.GiftCardMapper;
import com.f1soft.campaign.web.service.campaign.GiftCardService;
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
 * @author <krishna.pandey@f1soft.com>
 */
@Slf4j
@Component
public class GiftCardServiceImpl implements GiftCardService {

    @Autowired
    private GiftCardManager giftCardManager;
    @Autowired
    private GiftCardDetailRepository giftCardDetailRepository;
    @Autowired
    private GiftCardProviderRepository giftCardProviderRepository;
    @Autowired
    private QueryFilterHelper queryFilterHelper;

    @Override
    public ServerResponse createGiftCard(GiftCardCreateRequest giftCardCreateRequest) {
        giftCardManager.checkIfGiftCardExist(giftCardCreateRequest.getCode());
        return giftCardManager.createGiftCard(giftCardCreateRequest, LoginProvider.getApplicationUser());
    }

    @Override
    public ServerResponse createGiftCardProvider(GiftCardProviderCreateRequest giftCardCreateRequest) {
        giftCardManager.checkIfGiftCardProviderExist(giftCardCreateRequest.getCode());
        return giftCardManager.createGiftCardProvider(giftCardCreateRequest, LoginProvider.getApplicationUser());
    }

    @Override
    public ServerResponse giftCardDetail(Long id) {
        Optional<GiftCard> giftCardDetail = giftCardDetailRepository.findById(id);
        if (!giftCardDetail.isPresent()) {
            throw new InvalidDataException(ResponseMsg.failureResponse(MsgConstant.INVALID_DATA));
        }
        GiftCardResponse giftCardResponse = GiftCardMapper.convertToGiftCardResponse(giftCardDetail.get());
        return ResponseMsg.successResponse(MsgConstant.Data.SUCCESS, giftCardResponse);
    }

    @Override
    public ServerResponse modifyGiftCard(GiftCardModifyRequest giftCardModifyRequest) {
        Optional<GiftCard> giftCardDetailOptional = giftCardDetailRepository.findById(giftCardModifyRequest.getId());

        if (!giftCardDetailOptional.isPresent()) {
            throw new InvalidDataException(ResponseMsg.failureResponse(MsgConstant.INVALID_DATA));
        }
        if (!giftCardDetailOptional.get().getCode().equalsIgnoreCase(giftCardModifyRequest.getCode())) {
            giftCardManager.checkIfGiftCardExist(giftCardDetailOptional.get().getCode());
        }
        return giftCardManager.modifyCampaign(giftCardModifyRequest, giftCardDetailOptional.get(), LoginProvider.getApplicationUser());
    }

    @Override
    public ServerResponse updateGiftCardStatus(UpdateGiftCardStatusRequest updateGiftCardStatusRequest) {
        Optional<GiftCard> giftCardDetailOptional = giftCardDetailRepository.findById(updateGiftCardStatusRequest.getId());
        if (!giftCardDetailOptional.isPresent()) {
            throw new InvalidDataException(ResponseMsg.failureResponse(MsgConstant.INVALID_DATA));
        }
        return giftCardManager.updateCampaignStatus(giftCardDetailOptional.get(), LoginProvider.getApplicationUser(), updateGiftCardStatusRequest);
    }

    @Override
    public ServerResponse delete(Long campaignId) {
        Optional<GiftCard> giftCardDetailOptional = giftCardDetailRepository.findById(campaignId);
        if (!giftCardDetailOptional.isPresent()) {
            throw new InvalidDataException(ResponseMsg.failureResponse(MsgConstant.INVALID_DATA));
        }
        return giftCardManager.deleteGiftCard(giftCardDetailOptional.get());
    }

    @Override
    public ServerResponse searchGiftCard(SearchQueryParameter searchQueryParameter) {
        searchQueryParameter = queryFilterHelper.modifySearchQueryParam(searchQueryParameter);
        List<FieldQueryParameter> fieldQueryParameterList = CampaignHelper.getQueryParameterListForFilter(searchQueryParameter);

        Page<GiftCard> giftCardDetails = giftCardDetailRepository
                .findAll(giftCardDetailRepository.searchQuery(fieldQueryParameterList),
                        PageRequest.of(searchQueryParameter.getPage(), searchQueryParameter.getSize(), Sort.Direction.DESC, "id"));

        List<GiftCardResponse> campaignList = giftCardDetails.getContent().stream()
                .map(GiftCardMapper::convertToGiftCardResponse).collect(Collectors.toList());

        GiftCardListResponse giftCardListResponse = new GiftCardListResponse();
        giftCardListResponse.setGiftCardResponseList(campaignList);
        PageResponse pageResponse = new PageResponse(giftCardListResponse.getGiftCardResponseList(), giftCardDetails.getTotalElements());

        return ResponseMsg.successResponse(MsgConstant.Data.SUCCESS, pageResponse);
    }

    @Override
    public ServerResponse getAllProviders() {
        List<GiftCardProvider> giftCardProviders = giftCardProviderRepository.findAllActive();

        List<GiftCardProviderDetail> giftCardResponseList = giftCardProviders.stream().map(GiftCardMapper::convertToGiftCardProviderResponse)
                .collect(Collectors.toList());

        GiftCardProviderListResponse giftCardListResponse = new GiftCardProviderListResponse(giftCardResponseList);
        giftCardListResponse.setGiftCardProviderDetails(giftCardResponseList);
        return ResponseMsg.successResponse(MsgConstant.Data.SUCCESS, giftCardListResponse);
    }

    @Override
    public ServerResponse getAllGiftCard() {
        List<GiftCard> giftCards = giftCardDetailRepository.findAllActive();

        List<GiftCardResponse> giftCardResponseList = giftCards.stream().map(GiftCardMapper::convertToGiftCardResponse)
                .collect(Collectors.toList());

        GiftCardListResponse giftCardListResponse = new GiftCardListResponse(giftCardResponseList);
        giftCardListResponse.setGiftCardResponseList(giftCardResponseList);
        return ResponseMsg.successResponse(MsgConstant.Data.SUCCESS, giftCardListResponse);
    }
}
