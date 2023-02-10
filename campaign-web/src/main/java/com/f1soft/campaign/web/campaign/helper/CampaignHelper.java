package com.f1soft.campaign.web.campaign.helper;

import com.f1soft.campaign.common.constant.MsgConstant;
import com.f1soft.campaign.common.enums.CampaignModeEnum;
import com.f1soft.campaign.common.enums.OfferTransactionStatusEnum;
import com.f1soft.campaign.common.exception.InvalidDataException;
import com.f1soft.campaign.common.util.DateFormat;
import com.f1soft.campaign.common.util.DateFormatter;
import com.f1soft.campaign.common.util.ResponseMsg;
import com.f1soft.campaign.entities.model.ApplicationUser;
import com.f1soft.campaign.entities.model.Campaign;
import com.f1soft.campaign.entities.model.CampaignOffer;
import com.f1soft.campaign.entities.model.EventType;
import com.f1soft.campaign.repository.Util.FieldQueryParameter;
import com.f1soft.campaign.repository.Util.SearchQueryParameter;
import com.f1soft.campaign.web.campaign.dto.OfferModifyDTO;
import com.f1soft.campaign.web.campaign.dto.request.campaign.ModifyCampaignRequest;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Prajwol hada
 */
public class CampaignHelper {


    public static List<FieldQueryParameter> getQueryParameterListForFilter(SearchQueryParameter searchQueryParameter) {

        List<FieldQueryParameter> fieldQueryParameterList = searchQueryParameter.getSearch();
        if (fieldQueryParameterList == null) {
            fieldQueryParameterList = new ArrayList<>();
        }

        if (!StringUtils.isEmpty(searchQueryParameter.getFromDate())
                && !StringUtils.isEmpty(searchQueryParameter.getToDate())) {
            if (searchQueryParameter.getFromDate().equals(searchQueryParameter.getToDate())) {
                if (!searchQueryParameter.getFromDate().equals(DateFormatter.convertToString(new Date(), DateFormat.SQL_DATE_FORMAT))) {
                    throw new InvalidDataException(ResponseMsg.failureResponse(MsgConstant.Date.INVALID_DATE));
                }
            }
        }
        if (!StringUtils.isEmpty(searchQueryParameter.getFromDate())) {
            fieldQueryParameterList.add(new FieldQueryParameter("fromDate", DateFormatter.changeToStartDate(DateFormatter.convertToDate(searchQueryParameter.getFromDate()))));
        }
        if (!StringUtils.isEmpty(searchQueryParameter.getToDate())) {
            fieldQueryParameterList.add(new FieldQueryParameter("toDate", DateFormatter.changeToEndDate(DateFormatter.convertToDate(searchQueryParameter.getToDate()))));
        }
        return fieldQueryParameterList;
    }


    public static List<FieldQueryParameter> getQueryParameterListForTransactionFilter(SearchQueryParameter searchQueryParameter) {

        List<FieldQueryParameter> fieldQueryParameterList = searchQueryParameter.getSearch();
        if (fieldQueryParameterList == null) {
            fieldQueryParameterList = new ArrayList<>();
        }

        if (!StringUtils.isEmpty(searchQueryParameter.getFromDate())
                && !StringUtils.isEmpty(searchQueryParameter.getToDate())) {
            fieldQueryParameterList.add(new FieldQueryParameter("fromDate", DateFormatter.changeToStartDate(DateFormatter.convertToDate(searchQueryParameter.getFromDate()))));
            fieldQueryParameterList.add(new FieldQueryParameter("toDate", DateFormatter.changeToEndDate(DateFormatter.convertToDate(searchQueryParameter.getToDate()))));
        }
        return fieldQueryParameterList;
    }

    public static List<FieldQueryParameter> getQueryParamsForManualTransactionFilter(SearchQueryParameter searchQueryParameter) {
        List<FieldQueryParameter> fieldQueryParameterList = searchQueryParameter.getSearch();
        if (fieldQueryParameterList == null) {
            fieldQueryParameterList = new ArrayList<>();
        }

        fieldQueryParameterList.add(new FieldQueryParameter("campaignMode", CampaignModeEnum.MANUAL.name()));
        fieldQueryParameterList.add(new FieldQueryParameter("txnStatus", OfferTransactionStatusEnum.PENDING.name()));

        if (!StringUtils.isEmpty(searchQueryParameter.getFromDate())
                && !StringUtils.isEmpty(searchQueryParameter.getToDate())) {
            fieldQueryParameterList.add(new FieldQueryParameter("fromDate", DateFormatter.changeToStartDate(DateFormatter.convertToDate(searchQueryParameter.getFromDate()))));
            fieldQueryParameterList.add(new FieldQueryParameter("toDate", DateFormatter.changeToEndDate(DateFormatter.convertToDate(searchQueryParameter.getToDate()))));
        }
        return fieldQueryParameterList;
    }

    public static Campaign campaignForModification(Campaign campaign, ModifyCampaignRequest modifyCampaignRequest, ApplicationUser applicationUser, EventType eventType) {
        campaign.setTitle(modifyCampaignRequest.getTitle());
        campaign.setShortDescription(modifyCampaignRequest.getShortDescription());
        campaign.setDescription(modifyCampaignRequest.getDescription());
        campaign.setPolicy(modifyCampaignRequest.getPolicy());
        campaign.setPromoCode(modifyCampaignRequest.getPromoCode());
        campaign.setImage(modifyCampaignRequest.getImage());
        campaign.setTotalIssued(modifyCampaignRequest.getTotalIssued());
        campaign.setUsagePerCostumer(modifyCampaignRequest.getUsagePerCustomer());
        campaign.setBookingExpiryTime(modifyCampaignRequest.getExpiryTime());
        campaign.setEventType(modifyCampaignRequest.getEventType());
        campaign.setStartDate(DateFormatter.convertToDate(modifyCampaignRequest.getStartDate()));
        campaign.setEndDate(DateFormatter.convertToDate(modifyCampaignRequest.getEndDate()));
        campaign.setAllowedUsers(modifyCampaignRequest.getAllowedUsers());
        campaign.setModifiedBy(applicationUser);
        campaign.setModifiedDate(new Date());
        campaign.setOfferAccount(modifyCampaignRequest.getOfferAccount());
        campaign.setOfferLink(modifyCampaignRequest.getLink());
        campaign.setEventTypeId(eventType);
        campaign.setEventType(eventType.getCode());
        campaign.setCampaignMode(modifyCampaignRequest.getCampaignMode());
        return campaign;
    }

    public static CampaignOffer campaignOfferForModification(CampaignOffer campaignOffer, OfferModifyDTO offerModifyDTO) {
        if (offerModifyDTO.getCommissionType() != null) {
            if (!campaignOffer.getCommissionType().equalsIgnoreCase(offerModifyDTO.getCommissionType())) {
                campaignOffer.setCommissionType(offerModifyDTO.getCommissionType());
            }
        }
        if (campaignOffer.getMinAmount() != offerModifyDTO.getMinAmount()) {
            campaignOffer.setMinAmount(offerModifyDTO.getMinAmount());
        }
        if (campaignOffer.getMaxAmount() != offerModifyDTO.getMaxAmount()) {
            campaignOffer.setMaxAmount(offerModifyDTO.getMaxAmount());
        }
        if (campaignOffer.getValue() != offerModifyDTO.getValue()) {
            campaignOffer.setValue(offerModifyDTO.getValue());
        }
        return campaignOffer;
    }
}
