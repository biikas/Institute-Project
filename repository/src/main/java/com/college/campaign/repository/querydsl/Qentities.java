package com.college.campaign.repository.querydsl;


import com.college.campaign.entities.model.QApplicationUser;
import com.college.campaign.entities.model.QCampaign;
import com.college.campaign.entities.model.QGiftCard;
import com.college.campaign.entities.model.QOfferTransaction;

/*
 * @Author Rashim Dhaubanjar
 */
public class Qentities {

    protected QApplicationUser applicationUser = QApplicationUser.applicationUser;
    protected QCampaign campaign = QCampaign.campaign;
    protected QOfferTransaction offerTransaction = QOfferTransaction.offerTransaction;
    protected QGiftCard giftCard = QGiftCard.giftCard;

}
