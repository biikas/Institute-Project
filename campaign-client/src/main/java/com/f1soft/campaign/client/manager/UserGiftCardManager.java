package com.f1soft.campaign.client.manager;

import com.f1soft.campaign.client.constant.TemplateConstant;
import com.f1soft.campaign.client.dto.EmailMessage;
import com.f1soft.campaign.client.dto.UserInfoDTO;
import com.f1soft.campaign.client.dto.request.GiftCardFetchRequest;
import com.f1soft.campaign.client.service.EmailService;
import com.f1soft.campaign.common.dto.Message;
import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.entities.model.GiftCard;
import com.f1soft.campaign.entities.model.GiftCardEligibleUser;
import com.f1soft.campaign.repository.GiftCardEligibleUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Nitesh Poudel
 */
@Slf4j
@Component
public class UserGiftCardManager {

    @Autowired
    private GiftCardEligibleUserRepository giftCardEligibleUserRepository;
    @Autowired
    private EmailService emailService;

    public boolean checkGiftCardEligibility(GiftCard giftCard, GiftCardFetchRequest giftCardFetchRequest) {
        List<GiftCardEligibleUser> giftCardEligibleUserList = giftCardEligibleUserRepository.getByGiftCardId(giftCard.getId());
        if (!giftCardEligibleUserList.isEmpty() && giftCardFetchRequest.getUserName() != null) {
            return giftCardEligibleUserList.stream()
                    .filter(f -> f.getUsername().equals(giftCardFetchRequest.getUserName()))
                    .findAny().isPresent();
        }
        return false;
    }

    public ServerResponse sendGiftCardEmail(UserInfoDTO userInfo, GiftCard giftCard) {
        ServerResponse serverResponse = new ServerResponse();
        Message msg = new Message();
        msg.setMessage("Hello redeem your giftCard");

        EmailMessage emailMessage = getEmailMessage(msg, userInfo, giftCard);

        sendEmail(emailMessage);

        serverResponse.setMessage("email sent");
        serverResponse.setSuccess(true);
        serverResponse.setCode("0");
        return serverResponse;
    }

    private EmailMessage getEmailMessage(Message msg, UserInfoDTO userInfo, GiftCard giftCard) {

        // can be changed as per requirement.
        Map<String, String> parameterMap = new HashMap<>();
        parameterMap.put("message", msg.getMessage());
//        parameterMap.put("customerName", userInfo.getName());
        parameterMap.put("giftCardAmount", giftCard.getAmount().toString());
        parameterMap.put("giftCardProvider", giftCard.getGiftCardProvider().getName());

        List<String> receiversEmailAddress = new ArrayList<>();
        receiversEmailAddress.add(userInfo.getEmailAddress());

        EmailMessage emailMessage = new EmailMessage(TemplateConstant.GIFT_CARD_EMAIL_TEMPLATE,
                "Gift Card Redeemption",
                parameterMap,
                receiversEmailAddress
        );
        return emailMessage;
    }

    private void sendEmail(EmailMessage emailMessage) {
        if (emailMessage != null) {
            List<EmailMessage> emailMessages = new ArrayList<>();
            emailMessages.add(emailMessage);
            emailService.email(emailMessages);
        }
    }
}
