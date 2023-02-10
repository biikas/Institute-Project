package com.f1soft.campaign.repository.querydsl;


import com.f1soft.campaign.entities.model.QApplicationUser;
import com.f1soft.campaign.entities.model.QCampaign;
import com.f1soft.campaign.entities.model.QGiftCard;
import com.f1soft.campaign.entities.model.QOfferTransaction;

/*
 * @Author Rashim Dhaubanjar
 */
public class Qentities {

    protected QApplicationUser applicationUser = QApplicationUser.applicationUser;
    protected QCampaign campaign = QCampaign.campaign;
    protected QOfferTransaction offerTransaction = QOfferTransaction.offerTransaction;
    protected QGiftCard giftCard = QGiftCard.giftCard;

}
