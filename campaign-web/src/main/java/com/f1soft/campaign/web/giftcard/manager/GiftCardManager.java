package com.f1soft.campaign.web.giftcard.manager;

import com.f1soft.campaign.client.service.EmailService;
import com.f1soft.campaign.common.constant.MsgConstant;
import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.common.exception.InvalidDataException;
import com.f1soft.campaign.common.exception.ResourceAlreadyExistException;
import com.f1soft.campaign.common.util.MessageComposer;
import com.f1soft.campaign.common.util.ResponseMsg;
import com.f1soft.campaign.entities.model.ApplicationUser;
import com.f1soft.campaign.entities.model.GiftCard;
import com.f1soft.campaign.entities.model.GiftCardProvider;
import com.f1soft.campaign.repository.GiftCardDetailRepository;
import com.f1soft.campaign.repository.GiftCardProviderRepository;
import com.f1soft.campaign.web.config.provider.LoginProvider;
import com.f1soft.campaign.web.constant.MsgParameter;
import com.f1soft.campaign.web.giftcard.dto.request.GiftCardCreateRequest;
import com.f1soft.campaign.web.giftcard.dto.request.GiftCardModifyRequest;
import com.f1soft.campaign.web.giftcard.dto.request.GiftCardProviderCreateRequest;
import com.f1soft.campaign.web.giftcard.dto.request.UpdateGiftCardStatusRequest;
import com.f1soft.campaign.web.mapper.GiftCardMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

/**
 * @author <krishna.pandey@f1soft.com>
 */
@Slf4j
@Component
public class GiftCardManager {

    @Autowired
    private GiftCardDetailRepository giftCardDetailRepository;
    @Autowired
    private GiftCardProviderRepository giftCardProviderRepository;
    @Autowired
    private EmailService emailService;

    public boolean checkIfGiftCardExist(String code) {
        Optional<GiftCard> giftCardDetail = giftCardDetailRepository.getGiftCardDetailByCode(code);
        if (giftCardDetail.isPresent()) {
            throw new ResourceAlreadyExistException(ResponseMsg.failureResponse(MsgConstant.GIFT_CARD_ALREADY_EXIST));
        }
        return true;
    }

    public boolean checkIfGiftCardProviderExist(String code) {
        Optional<GiftCardProvider> giftCardDetail = giftCardProviderRepository.getGiftCardDetailByCode(code);
        if (giftCardDetail.isPresent()) {
            throw new ResourceAlreadyExistException(ResponseMsg.failureResponse(MsgConstant.GIFT_CARD_PROVIDER_ALREADY_EXIST));
        }
        return true;
    }

    @Transactional
    public ServerResponse createGiftCard(GiftCardCreateRequest giftCardCreateRequest, ApplicationUser applicationUser) {
        Optional<GiftCardProvider> giftCardProvider = giftCardProviderRepository.findById(giftCardCreateRequest.getGiftCardProviderId());
        if (!giftCardProvider.isPresent()) {
            throw new InvalidDataException(ResponseMsg.failureResponse(MsgConstant.INVALID_DATA));
        }

        GiftCard giftCard = GiftCardMapper.mapToGiftCardDetail(giftCardCreateRequest, applicationUser, giftCardProvider.get());

        giftCard = giftCardDetailRepository.save(giftCard);

        return ResponseMsg.successResponse(MsgConstant.Data.GIFT_CARD_CREATE_SUCCESS, MessageComposer.getParameters(MsgParameter.GIFT_CARD_NAME, giftCard.getName()));
    }

    @Transactional
    public ServerResponse createGiftCardProvider(GiftCardProviderCreateRequest giftCardCreateRequest, ApplicationUser applicationUser) {

        GiftCardProvider giftCardDetail = GiftCardMapper.convertToGiftCardProvider(giftCardCreateRequest);

        giftCardDetail = giftCardProviderRepository.save(giftCardDetail);

        return ResponseMsg.successResponse(MsgConstant.Data.GIFT_CARD_PROVIDER_CREATE_SUCCESS, MessageComposer.getParameters(MsgParameter.GIFT_CARD_NAME, giftCardDetail.getName()));
    }

    @Transactional
    public ServerResponse modifyCampaign(GiftCardModifyRequest giftCardModifyRequest, GiftCard giftCard, ApplicationUser applicationUser) {

        Optional<GiftCardProvider> giftCardProvider = giftCardProviderRepository.findById(giftCardModifyRequest.getGiftCardProviderId());
        if (!giftCardProvider.isPresent()) {
            throw new InvalidDataException(ResponseMsg.failureResponse(MsgConstant.INVALID_DATA));
        }
        GiftCard modifiedGiftCard = GiftCardMapper.giftCardForModification(giftCardModifyRequest, giftCard, applicationUser, giftCardProvider.get());

        giftCardDetailRepository.save(modifiedGiftCard);

        return ResponseMsg.successResponse(MsgConstant.Data.GIFT_CARD_MODIFY_SUCCESS, MessageComposer.getParameters(MsgParameter.GIFT_CARD_NAME, giftCard.getName()));
    }

    public ServerResponse updateCampaignStatus(GiftCard giftCard, ApplicationUser applicationUser, UpdateGiftCardStatusRequest updateGiftCardStatusRequest) {

        giftCard.setActive(updateGiftCardStatusRequest.getActive());
        giftCard.setModifiedDate(new Date());
        giftCard.setModifiedBy(applicationUser);
        giftCardDetailRepository.save(giftCard);

        return ResponseMsg.successResponse(MsgConstant.Data.STATUS_CHANGE_SUCCESS);
    }

    public ServerResponse deleteGiftCard(GiftCard giftCard) {
        giftCard.setActive('N');
        giftCard.setModifiedDate(new Date());
        giftCard.setModifiedBy(LoginProvider.getApplicationUser());
        giftCardDetailRepository.save(giftCard);
        return ResponseMsg.successResponse(MsgConstant.Data.CAMPAIGN_DELETE_SUCCESS);
    }
}
