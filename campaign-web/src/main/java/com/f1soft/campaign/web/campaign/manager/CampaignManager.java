package com.f1soft.campaign.web.campaign.manager;

import com.f1soft.campaign.common.config.application.SystemConfig;
import com.f1soft.campaign.common.constant.AppConfigConstant;
import com.f1soft.campaign.common.constant.MsgConstant;
import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.common.enums.CampaignStatusEnum;
import com.f1soft.campaign.common.enums.EventTypeEnum;
import com.f1soft.campaign.common.enums.UserTypesEnum;
import com.f1soft.campaign.common.exception.InvalidDataException;
import com.f1soft.campaign.common.exception.ResourceAlreadyExistException;
import com.f1soft.campaign.common.util.MessageComposer;
import com.f1soft.campaign.common.util.RandomGenerator;
import com.f1soft.campaign.common.util.ResponseMsg;
import com.f1soft.campaign.entities.model.*;
import com.f1soft.campaign.repository.*;
import com.f1soft.campaign.web.campaign.dto.CampaignServiceDTO;
import com.f1soft.campaign.web.campaign.dto.OfferCreateDTO;
import com.f1soft.campaign.web.campaign.dto.OfferModifyDTO;
import com.f1soft.campaign.web.campaign.dto.request.campaign.CreateCampaignRequest;
import com.f1soft.campaign.web.campaign.dto.request.campaign.ModifyCampaignRequest;
import com.f1soft.campaign.web.campaign.dto.request.campaign.UpdateCampaignStatusRequest;
import com.f1soft.campaign.web.campaign.dto.response.ModifyServiceDTO;
import com.f1soft.campaign.web.campaign.helper.CampaignHelper;
import com.f1soft.campaign.web.campaign.mapper.CampaignMapper;
import com.f1soft.campaign.web.constant.Constant;
import com.f1soft.campaign.web.constant.MsgParameter;
import com.f1soft.campaign.web.dto.OfferAmountDTO;
import com.f1soft.campaign.web.dto.ProfileDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Prajwol hada
 */
@Slf4j
@Component
public class CampaignManager {

    @Autowired
    private CampaignRepository campaignRepository;

    @Autowired
    private ChannelRepository channelRepository;

    @Autowired
    private CampaignAllowedChannelRepository campaignAllowedChannelRepository;

    @Autowired
    private CampaignEligibleServiceRepository campaignEligibleServiceRepository;

    @Autowired
    private OfferModeRepository offerModeRepository;

    @Autowired
    private CampaignOfferRepository campaignOfferRepository;

    @Autowired
    private CampaignEligibleProfileRepository campaignEligibleProfileRepository;

    @Autowired
    private CampaignEligibleUserRepository campaignEligibleUserRepository;

    @Autowired
    private EventTypeRepository eventTypeRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private EventAttributeRepository eventAttributeRepository;

    @Autowired
    private CustomCbsQueryRepository customCbsQueryRepository;

    @Autowired
    private SystemConfig systemConfig;

    @Autowired
    private CampaignTotalRedeemRepository campaignTotalRedeemRepository;

    @Autowired
    private CampaignExcelFileRepository campaignExcelFileRepository;

    public boolean checkIfPromoCodeExist(String promoCode) {
        Optional<Campaign> campaignOptional = campaignRepository.getCampaignByPromoCode(promoCode);
        if (campaignOptional.isPresent()) {
            throw new ResourceAlreadyExistException(ResponseMsg.failureResponse(MsgConstant.PROMO_CODE_ALREADY_EXIST));
        }
        return true;
    }

    @Transactional
    public ServerResponse createCampaign(CreateCampaignRequest createCampaignRequest, ApplicationUser applicationUser) {

        ServerResponse serverResponse = this.validateCampaignOfferForCreate(createCampaignRequest.getOffers());

        if (!serverResponse.isSuccess()) {
            return serverResponse;
        }

        Optional<EventType> eventType = eventTypeRepository.findById(createCampaignRequest.getEventTypeId());
        if (!eventType.isPresent()) {
            throw new InvalidDataException(ResponseMsg.failureResponse(MsgConstant.INVALID_DATA));
        }

        Campaign campaign;

        if (createCampaignRequest.getPromoCode() == null && createCampaignRequest.getEventType().equalsIgnoreCase(EventTypeEnum.REGISTRATION.name())) {

                String prefix = systemConfig.appConfig(AppConfigConstant.DYNAMIC_PROMO_CODE_PREFIX);
                String promoCode = RandomGenerator.generateRandomCapsAlphaNumeric(prefix, 20);

                campaign = CampaignMapper.mapToCampaign(createCampaignRequest, applicationUser, eventType.get(), promoCode);
        } else {
            campaign = CampaignMapper.mapToCampaign(createCampaignRequest, applicationUser, eventType.get());
        }

        campaign = campaignRepository.save(campaign);

        CampaignTotalRedeem campaignTotalRedeem = CampaignMapper.mapToCampaignTotalRedeem(campaign, createCampaignRequest.getTotalOfferAmount());
        campaignTotalRedeemRepository.save(campaignTotalRedeem);

        if (createCampaignRequest.getIsWeb() == 'Y') {
            Channel webChannel = channelRepository.getChannelByCode(Constant.Channel.WEB).get();
            CampaignAllowedChannel campaignAllowedChannel = new CampaignAllowedChannel();
            campaignAllowedChannel.setActive('Y');
            campaignAllowedChannel.setChannel(webChannel);
            campaignAllowedChannel.setCampaign(campaign);

            campaignAllowedChannelRepository.save(campaignAllowedChannel);
        }

        if (createCampaignRequest.getIsMobile() == 'Y') {
            Channel mobileChannel = channelRepository.getChannelByCode(Constant.Channel.MOBILE).get();
            CampaignAllowedChannel campaignAllowedChannel = new CampaignAllowedChannel();
            campaignAllowedChannel.setActive('Y');
            campaignAllowedChannel.setChannel(mobileChannel);
            campaignAllowedChannel.setCampaign(campaign);

            campaignAllowedChannelRepository.save(campaignAllowedChannel);
        }

        if (createCampaignRequest.getServices() != null) {
            for (CampaignServiceDTO serviceDTO : createCampaignRequest.getServices()) {
                Optional<Service> serviceOptional = serviceRepository.getServiceByCode(serviceDTO.getCode());
                CampaignEligibleService campaignEligibleService = CampaignMapper.mapToCampaignEligibleService(serviceDTO, serviceOptional, campaign);
                campaignEligibleServiceRepository.save(campaignEligibleService);
            }
        }

        if (createCampaignRequest.getNtransaction().getMinimumAmount() != null) {
            EventAttribute eventAttribute = new EventAttribute();
            eventAttribute.setCampaign(campaign);
            eventAttribute.setMinimumAmount(createCampaignRequest.getNtransaction().getMinimumAmount());
            eventAttribute.setCount(createCampaignRequest.getNtransaction().getNoOfTransaction());
            eventAttributeRepository.save(eventAttribute);
        } else if (createCampaignRequest.getCustomCbsQueryId() != null) {
            Optional<CustomCBSQuery> customCBSQuery = customCbsQueryRepository.findCbsQueryById(createCampaignRequest.getCustomCbsQueryId());
            if (!customCBSQuery.isPresent()) {
                throw new InvalidDataException(ResponseMsg.failureResponse(MsgConstant.INVALID_DATA));
            }
            EventAttribute eventAttribute = new EventAttribute();
            eventAttribute.setCampaign(campaign);
            eventAttribute.setCustomCbsQuery(customCBSQuery.get());
            eventAttributeRepository.save(eventAttribute);
        }

        for (OfferCreateDTO offerCreateDTO : createCampaignRequest.getOffers()) {
            OfferMode offerMode = offerModeRepository.getByCode(offerCreateDTO.getMode()).get();
            CampaignOffer campaignOffer = CampaignMapper.mapToCampaignOffer(offerCreateDTO, campaign, offerMode);
            campaignOfferRepository.save(campaignOffer);
        }

        if (createCampaignRequest.getAllowedUsers().equalsIgnoreCase(UserTypesEnum.PROFILE.name())) {
            for (ProfileDTO profileDTO : createCampaignRequest.getProfiles()) {
                CampaignEligibleProfile campaignEligibleProfile = CampaignMapper.mapToCampaignEligibleProfile(profileDTO, campaign);
                campaignEligibleProfileRepository.save(campaignEligibleProfile);
            }
        }

        if(createCampaignRequest.getAllowedUsers().equalsIgnoreCase(UserTypesEnum.BULK.name())) {
            CampaignExcelFiles campaignExcelFile = CampaignMapper.mapToCampaignExcelFile(createCampaignRequest.getExcelFileName(), campaign);
            campaignExcelFileRepository.save(campaignExcelFile);
        }
        return ResponseMsg.successResponse(MsgConstant.Data.CAMPAIGN_CREATE_SUCCESS, MessageComposer.getParameters(MsgParameter.CAMPAIGN_NAME, campaign.getTitle()));
    }

    @Transactional
    public ServerResponse modifyCampaign(ModifyCampaignRequest modifyCampaignRequest, Campaign campaign, ApplicationUser applicationUser) {

        ServerResponse serverResponse = this.validateCampaignOfferForModification(modifyCampaignRequest.getOffers());

        if (!serverResponse.isSuccess()) {
            return serverResponse;
        }

        Optional<EventType> eventType = eventTypeRepository.findById(modifyCampaignRequest.getEventTypeId());
        if (!eventType.isPresent()) {
            throw new InvalidDataException(ResponseMsg.failureResponse(MsgConstant.INVALID_DATA));
        }
        Campaign modifiedCampaign = CampaignHelper.campaignForModification(campaign, modifyCampaignRequest, applicationUser, eventType.get());

        campaignRepository.save(modifiedCampaign);

        Optional<CampaignTotalRedeem> campaignTotalRedeemOptional = campaignTotalRedeemRepository.getCampaignTotalRedeemByCampaignId(campaign.getId());

        CampaignTotalRedeem campaignTotalRedeem ;

        if(campaignTotalRedeemOptional.isPresent()) {
             campaignTotalRedeem = CampaignMapper.mapToModifyCampaignTotalRedeem(campaignTotalRedeemOptional.get(), campaign, modifyCampaignRequest.getTotalOfferAmount());
        }
        else{
             campaignTotalRedeem = CampaignMapper.mapToCampaignTotalRedeem(campaign, modifyCampaignRequest.getTotalOfferAmount());
        }
        campaignTotalRedeemRepository.save(campaignTotalRedeem);

        Map<String, CampaignAllowedChannel> channelMap = campaign.getCampaignAllowedChannels().stream().collect(Collectors.toMap(c -> c.getChannel().getCode(), c -> c));

        updateAllowedChannel(modifiedCampaign, channelMap, modifyCampaignRequest.getIsMobile(), modifyCampaignRequest.getIsWeb());

        List<CampaignEligibleService> campaignEligibleServiceList = campaign.getCampaignEligibleServices().stream().filter(service -> service.getActive() == 'Y').collect(Collectors.toList());


        Map<String, ModifyServiceDTO> serviceDTOMap = new HashMap<>();
        if (modifyCampaignRequest.getServices() != null) {
            for (ModifyServiceDTO serviceDTO : modifyCampaignRequest.getServices()) {

                Optional<CampaignEligibleService> eligibleService = campaignEligibleServiceList.stream().filter(service -> service.getServiceCode().equalsIgnoreCase(serviceDTO.getCode())).findAny();

                if (!eligibleService.isPresent()) {
                    Optional<Service> serviceOptional = serviceRepository.getServiceByCode(serviceDTO.getCode());

                    CampaignEligibleService campaignEligibleService = CampaignMapper.mapToCampaignEligibleService(serviceDTO, serviceOptional, campaign);
                    campaignEligibleServiceRepository.save(campaignEligibleService);
                } else {
                    serviceDTOMap.put(serviceDTO.getCode(), serviceDTO);
                }
            }
        }

        for (CampaignEligibleService campaignEligibleService : campaignEligibleServiceList) {

            if (!serviceDTOMap.containsKey(campaignEligibleService.getServiceCode())) {
                campaignEligibleService.setActive('N');
                campaignEligibleServiceRepository.save(campaignEligibleService);
            } else if (serviceDTOMap.containsKey(campaignEligibleService.getServiceCode())
                    && campaignEligibleService.getMode().equalsIgnoreCase(Constant.ServiceMode.MANUAL)) {
                ModifyServiceDTO serviceDTO = serviceDTOMap.get(campaignEligibleService.getServiceCode());
                if (!serviceDTO.getName().equals(campaignEligibleService.getServiceName())) {
                    campaignEligibleService.setServiceName(serviceDTO.getName());
                    campaignEligibleServiceRepository.save(campaignEligibleService);
                }
            }
        }

        //Modify CampaignOffer
        List<CampaignOffer> campaignOfferList = campaign.getCampaignOffer().stream().filter(campaignOffer -> campaignOffer.getActive() == 'Y').collect(Collectors.toList());

        Map<Long, OfferModifyDTO> offerModifyDTOMap = new HashMap<>();

        for (OfferModifyDTO offerModifyDTO : modifyCampaignRequest.getOffers()) {
            if (offerModifyDTO.getId() == null) {
                OfferMode offerMode = offerModeRepository.getByCode(offerModifyDTO.getMode()).get();
                CampaignOffer campaignOffer = new CampaignOffer();
                campaignOffer.setActive('Y');
                if (offerModifyDTO.getCommissionType() != null) {
                    campaignOffer.setCommissionType(offerModifyDTO.getCommissionType());
                }
                campaignOffer.setMinAmount(offerModifyDTO.getMinAmount());
                campaignOffer.setMaxAmount(offerModifyDTO.getMaxAmount());
                campaignOffer.setValue(offerModifyDTO.getValue());
                campaignOffer.setCampaign(campaign);
                campaignOffer.setOfferMode(offerMode);

                campaignOfferRepository.save(campaignOffer);
            } else {
                offerModifyDTOMap.put(offerModifyDTO.getId(), offerModifyDTO);
            }
        }

        for (CampaignOffer campaignOffer : campaignOfferList) {

            if (offerModifyDTOMap.containsKey(campaignOffer.getId())) {
                CampaignOffer modifiedCampaignOffer = CampaignHelper.campaignOfferForModification(campaignOffer, offerModifyDTOMap.get(campaignOffer.getId()));
                campaignOfferRepository.save(modifiedCampaignOffer);
            } else {
                campaignOffer.setActive('N');
                campaignOfferRepository.save(campaignOffer);
            }
        }

        //Modify CampaignEligibleProfile
        modifyCampaignEligibleProfile(modifyCampaignRequest, modifiedCampaign);

        return ResponseMsg.successResponse(MsgConstant.Data.CAMPAIGN_MODIFY_SUCCESS, MessageComposer.getParameters(MsgParameter.CAMPAIGN_NAME, campaign.getTitle()));
    }

    private void updateAllowedChannel(Campaign modifiedCampaign, Map<String, CampaignAllowedChannel> channelMap, char isMobile, char isWeb) {

        CampaignAllowedChannel campaignAllowedMobileChannel = channelMap.get(Constant.Channel.MOBILE);

        if (campaignAllowedMobileChannel == null) {
            campaignAllowedMobileChannel = new CampaignAllowedChannel();
            Channel channel = channelRepository.getChannelByCode(Constant.Channel.MOBILE).get();
            campaignAllowedMobileChannel.setActive('Y');
            campaignAllowedMobileChannel.setCampaign(modifiedCampaign);
            campaignAllowedMobileChannel.setChannel(channel);
        }
        campaignAllowedMobileChannel.setActive(isMobile == 'Y' ? 'Y' : 'N');
        campaignAllowedChannelRepository.save(campaignAllowedMobileChannel);

        CampaignAllowedChannel campaignAllowedWebChannel = channelMap.get(Constant.Channel.WEB);
        if (campaignAllowedWebChannel == null) {
            campaignAllowedWebChannel = new CampaignAllowedChannel();
            Channel channel = channelRepository.getChannelByCode(Constant.Channel.WEB).get();
            campaignAllowedWebChannel.setActive('Y');
            campaignAllowedWebChannel.setCampaign(modifiedCampaign);
            campaignAllowedWebChannel.setChannel(channel);
        }
        campaignAllowedWebChannel.setActive(isWeb == 'Y' ? 'Y' : 'N');
        campaignAllowedChannelRepository.save(campaignAllowedWebChannel);
    }

    private void modifyCampaignEligibleProfile(ModifyCampaignRequest modifyCampaignRequest, Campaign campaign) {

        List<CampaignEligibleProfile> campaignEligibleProfileList = campaignEligibleProfileRepository.getByCampaign(campaign.getId());

        if (modifyCampaignRequest.getAllowedUsers().equalsIgnoreCase(UserTypesEnum.ALL.name()) && campaignEligibleProfileList.size() > 0) {
            campaignEligibleProfileList.stream().map(campaignEligibleProfile -> {
                campaignEligibleProfile.setActive('N');
                return campaignEligibleProfile;
            }).forEach(campaignEligibleProfile ->
                campaignEligibleProfileRepository.save(campaignEligibleProfile));

        } else if (modifyCampaignRequest.getAllowedUsers().equalsIgnoreCase(UserTypesEnum.PROFILE.name())) {

            Map<Long, ProfileDTO> profileDTOMap = new HashMap<>();
            for (ProfileDTO profileDTO : modifyCampaignRequest.getProfiles()) {

                Optional<CampaignEligibleProfile> eligibleProfile = campaignEligibleProfileList.stream().filter(profile -> profile.getProfileId() == profileDTO.getId()).findAny();

                if (!eligibleProfile.isPresent()) {
                    CampaignEligibleProfile campaignEligibleProfile = CampaignMapper.mapToCampaignEligibleProfile(profileDTO, campaign);
                    campaignEligibleProfileRepository.save(campaignEligibleProfile);
                } else {
                    profileDTOMap.put(profileDTO.getId(), profileDTO);
                }
            }

            for (CampaignEligibleProfile campaignEligibleProfile : campaignEligibleProfileList) {
                if (!profileDTOMap.containsKey(campaignEligibleProfile.getProfileId())) {
                    campaignEligibleProfile.setActive('N');
                    campaignEligibleProfileRepository.save(campaignEligibleProfile);
                }
            }
        }
    }

    public ServerResponse updateCampaignStatus(Campaign campaign, ApplicationUser applicationUser, UpdateCampaignStatusRequest updateCampaignStatusRequest) {

        campaign.setStatus(updateCampaignStatusRequest.getStatus());
        campaign.setModifiedBy(applicationUser);
        campaign.setModifiedDate(new Date());

        if (updateCampaignStatusRequest.getRemarks() != null && updateCampaignStatusRequest.getRemarks().length() > 0) {
            campaign.setRemarks(updateCampaignStatusRequest.getRemarks());
        }

        campaignRepository.save(campaign);

        return ResponseMsg.successResponse(MsgConstant.Data.STATUS_CHANGE_SUCCESS);
    }

    private ServerResponse validateCampaignOfferForCreate(List<OfferCreateDTO> offerCreateDTOList) {
        List<OfferAmountDTO> sortedOfferList = offerCreateDTOList.stream().map(offerCreateDTO -> new OfferAmountDTO(offerCreateDTO.getMinAmount(), offerCreateDTO.getMaxAmount())).sorted(Comparator.comparingDouble(OfferAmountDTO::getMinAmount)).collect(Collectors.toList());

        return validateCampaignOffer(sortedOfferList);
    }

    public ServerResponse validateCampaignOfferForModification(List<OfferModifyDTO> offerCreateDTOList) {
        List<OfferAmountDTO> sortedOfferList = offerCreateDTOList.stream().map(offerCreateDTO -> new OfferAmountDTO(offerCreateDTO.getMinAmount(), offerCreateDTO.getMaxAmount())).sorted(Comparator.comparingDouble(OfferAmountDTO::getMinAmount)).collect(Collectors.toList());

        return validateCampaignOffer(sortedOfferList);
    }

    private ServerResponse validateCampaignOffer(List<OfferAmountDTO> offerAmountDTOList) {
        ServerResponse serverResponse = new ServerResponse();
        for (int i = 0; i < offerAmountDTOList.size(); i++) {
            if (offerAmountDTOList.get(i).getMinAmount() >= offerAmountDTOList.get(i).getMaxAmount()) {
                throw new InvalidDataException(ResponseMsg.failureResponse(MsgConstant.INVALID_OFFER_AMOUNT));
            } else if (i != 0 && offerAmountDTOList.get(i).getMinAmount() < offerAmountDTOList.get(i - 1).getMaxAmount()) {
                    throw new InvalidDataException(ResponseMsg.failureResponse(MsgConstant.INVALID_OFFER_AMOUNT));
            }
        }
        serverResponse.setSuccess(true);
        return serverResponse;
    }

    public ServerResponse deleteCampaign(Campaign campaign) {
        campaign.setActive('N');
        campaign.setStatus(CampaignStatusEnum.DELETED.name());
        campaignRepository.save(campaign);
        return ResponseMsg.successResponse(MsgConstant.Data.CAMPAIGN_DELETE_SUCCESS);
    }
}
