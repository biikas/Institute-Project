package com.f1soft.campaign.web.service.campaign.impl;

import com.f1soft.campaign.common.cbs.dto.CustomerProfileDTO;
import com.f1soft.campaign.common.config.application.SystemConfig;
import com.f1soft.campaign.common.config.constant.AppConfigConstant;
import com.f1soft.campaign.common.constant.MsgConstant;
import com.f1soft.campaign.common.dto.PageResponse;
import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.common.enums.CampaignModeEnum;
import com.f1soft.campaign.common.enums.UserTypesEnum;
import com.f1soft.campaign.common.exception.InvalidDataException;
import com.f1soft.campaign.common.manager.QueryManager;
import com.f1soft.campaign.common.util.ResponseMsg;
import com.f1soft.campaign.entities.model.*;
import com.f1soft.campaign.repository.*;
import com.f1soft.campaign.repository.Util.FieldQueryParameter;
import com.f1soft.campaign.repository.Util.SearchQueryParameter;
import com.f1soft.campaign.web.campaign.dto.CampaignDetailDTO;
import com.f1soft.campaign.web.campaign.dto.response.CampaignDetailListResponse;
import com.f1soft.campaign.web.campaign.dto.response.CampaignDetailResponse;
import com.f1soft.campaign.web.campaign.helper.CampaignHelper;
import com.f1soft.campaign.web.campaign.mapper.CampaignResponseMapper;
import com.f1soft.campaign.web.dto.*;
import com.f1soft.campaign.web.dto.response.CampaignModeResponse;
import com.f1soft.campaign.web.dto.response.ProfileResponse;
import com.f1soft.campaign.web.mapper.CampaignMapper;
import com.f1soft.campaign.web.service.campaign.CampaignService;
import com.f1soft.campaign.web.users.dto.response.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CampaignServiceImpl implements CampaignService {

    @Autowired
    private CampaignRepository campaignRepository;
    @Autowired
    private BookingLogRepository bookingLogRepository;
    @Autowired
    private OfferModeRepository offerModeRepository;
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private QueryManager queryManager;
    @Autowired
    private SystemConfig systemConfig;
    @Autowired
    private CampaignEligibleProfileRepository campaignEligibleProfileRepository;
    @Autowired
    private CampaignEligibleUserRepository campaignEligibleUserRepository;
    @Autowired
    private EventAttributeRepository eventAttributeRepository;
    @Autowired
    private CampaignTotalRedeemRepository campaignTotalRedeemRepository;
    @Autowired
    private CampaignExcelFileRepository campaignExcelFileRepository;

    @Override
    public ServerResponse getAllCampaign() {
        List<Campaign> campaigns = campaignRepository.getAllCampaignList();

        CampaignListResponse campaignListResponse = CampaignMapper.convertToCampaign(campaigns);

        return ResponseMsg.successResponse(MsgConstant.Data.SUCCESS, campaignListResponse);
    }

    @Override
    public ServerResponse campaignDetail(Long id) {

        Optional<Campaign> campaign = campaignRepository.findById(id);
        if (!campaign.isPresent()) {
            throw new InvalidDataException(ResponseMsg.failureResponse(MsgConstant.INVALID_DATA));
        }

        Optional<EventAttribute> eventAttribute = eventAttributeRepository.getByCampaignId(id);
        Optional<CampaignTotalRedeem> campaignTotalRedeemOptional = campaignTotalRedeemRepository.getCampaignTotalRedeemByCampaignId(id);
        List<CampaignEligibleProfile> campaignEligibleProfileList = campaignEligibleProfileRepository.getByCampaign(id);
        String imagePath = systemConfig.appConfig(AppConfigConstant.CAMPAIGN_IMAGE_PATH);

        CampaignDetailResponse campaignDetailResponse = CampaignResponseMapper.campaignResponseMapper(campaign.get(), imagePath, campaignEligibleProfileList, eventAttribute);

        if (campaign.get().getAllowedUsers().equalsIgnoreCase(UserTypesEnum.BULK.name())) {
            CampaignExcelFiles campaignExcelFiles = campaignExcelFileRepository.getByCampaignId(campaign.get().getId());
            campaignDetailResponse.setExcelFileName(campaignExcelFiles.getFileName());
        }
        if (campaignTotalRedeemOptional.isPresent()) {
            campaignDetailResponse.setTotalOfferAmount(campaignTotalRedeemOptional.get().getOfferAmount());
        }
        return ResponseMsg.successResponse(MsgConstant.Data.SUCCESS, campaignDetailResponse);
    }

    @Override
    public ServerResponse searchCampaign(SearchQueryParameter searchQueryParameter) {

        List<FieldQueryParameter> fieldQueryParameterList = CampaignHelper.getQueryParameterListForFilter(searchQueryParameter);

        Page<Campaign> campaigns = campaignRepository
                .findAll(campaignRepository.searchQuery(fieldQueryParameterList),
                        PageRequest.of(searchQueryParameter.getPage(), searchQueryParameter.getSize(), Sort.Direction.DESC, "id"));

        List<CampaignResponse> campaignList = campaigns.getContent().stream()
                .map(campaign -> {
                    Optional<BookingLog> bookingLog = bookingLogRepository.findByCampaign(campaign.getId());
                    int totalConsumed = 0;
                    if (bookingLog.isPresent()) {
                        totalConsumed = bookingLog.get().getTotalUsed();
                    }
                    return CampaignMapper.convertToCampaignResponse(campaign, totalConsumed);
                }).collect(Collectors.toList());

        CampaignListResponse campaignListResponse = new CampaignListResponse();
        campaignListResponse.setCampaignList(campaignList);
        PageResponse pageResponse = new PageResponse(campaignListResponse.getCampaignList(), campaigns.getTotalElements());

        return ResponseMsg.successResponse(MsgConstant.Data.SUCCESS, pageResponse);
    }

    @Override
    public ServerResponse getAllOfferMode() {

        List<OfferMode> offerModeList = offerModeRepository.findAll();
        List<OfferModeResponse> offerModeResponses = offerModeList.stream()
                .filter(f -> f.getActive() == 'Y')
                .map(offerMode -> {
                    OfferModeResponse offerModeResponse = new OfferModeResponse();
                    offerModeResponse.setId(offerMode.getId());
                    offerModeResponse.setName(offerMode.getName());
                    offerModeResponse.setCode(offerMode.getCode());
                    if (!offerMode.getOfferPackages().isEmpty()) {
                        offerModeResponse.setPackageOptions(getPackages(offerMode.getOfferPackages()));
                    }
                    if (!offerMode.getOfferCommissionType().isEmpty()) {
                        offerModeResponse.setOfferCommissionTypes(getCommissionTypes(offerMode.getOfferCommissionType()));
                    }
                    return offerModeResponse;
                }).collect(Collectors.toList());

        OfferModeListResponse offerModeListResponse = new OfferModeListResponse(offerModeResponses);

        return ResponseMsg.successResponse(MsgConstant.Data.SUCCESS, offerModeListResponse);
    }

    private List<OfferCommissionTypeResponse> getCommissionTypes(List<OfferCommissionType> commissionTypes) {
        return commissionTypes.stream()
                .filter(f -> f.getActive() == 'Y')
                .map(commissionType -> {
                    OfferCommissionTypeResponse offerCommissionTypeResponse = new OfferCommissionTypeResponse();
                    offerCommissionTypeResponse.setName(commissionType.getCommissionType().getName());
                    offerCommissionTypeResponse.setCode(commissionType.getCommissionType().getCode());
                    offerCommissionTypeResponse.setId(commissionType.getId());
                    return offerCommissionTypeResponse;
                }).collect(Collectors.toList());
    }

    @Override
    public ServerResponse getAllServices() {

        List<com.f1soft.campaign.entities.model.Service> serviceList = serviceRepository.findAll();
        List<CampaignServiceResponse> campaignServiceResponses = serviceList.stream()
                .map(service -> {
                    CampaignServiceResponse campaignServiceResponse = new CampaignServiceResponse();
                    campaignServiceResponse.setId(service.getId());
                    campaignServiceResponse.setActive(service.getActive());
                    campaignServiceResponse.setCode(service.getCode());
                    campaignServiceResponse.setName(service.getName());
                    campaignServiceResponse.setModule(service.getModule());

                    return campaignServiceResponse;
                }).collect(Collectors.toList());

        CampaignServiceListResponse campaignServiceListResponse = new CampaignServiceListResponse(campaignServiceResponses);

        return ResponseMsg.successResponse(MsgConstant.Data.SUCCESS, campaignServiceListResponse);
    }

    @Override
    public ServerResponse getAllCustomerProfiles() {

        List<CustomerProfileDTO> customerProfileDTOList = queryManager.customerProfile();
        ProfileResponse profileResponse = new ProfileResponse(customerProfileDTOList);

        return ResponseMsg.successResponse(MsgConstant.Data.SUCCESS, profileResponse);
    }

    private List<PackageResponse> getPackages(List<OfferPackage> offerPackages) {
        return offerPackages.stream()
                .filter(f -> f.getActive() == 'Y')
                .map(offerPackage -> {
                    PackageResponse packageResponse = new PackageResponse();
                    packageResponse.setValue(offerPackage.getCode());
                    packageResponse.setLabel(offerPackage.getName());

                    return packageResponse;
                })
                .collect(Collectors.toList());
    }

    @Override
    public ServerResponse getCampaignModes() {
        CampaignModeResponse campaignModeResponse = new CampaignModeResponse();
        List<CampaignMode> campaignModes = new ArrayList<>();

        CampaignMode automaticCampaignMode = new CampaignMode();
        automaticCampaignMode.setMode(CampaignModeEnum.AUTO.name());

        CampaignMode manualCampaignMode = new CampaignMode();
        manualCampaignMode.setMode(CampaignModeEnum.MANUAL.name());

        campaignModes.add(automaticCampaignMode);
        campaignModes.add(manualCampaignMode);

        campaignModeResponse.setCampaignModes(campaignModes);

        return ResponseMsg.successResponse(MsgConstant.Data.SUCCESS, campaignModeResponse);
    }

    @Override
    public ServerResponse campaignRedeemDetail() {
        List<Campaign> campaignList = campaignRepository.getAllCampaignList();

        List<Campaign> activeCampaigns = campaignList.stream().filter(f -> f.getActive() == 'Y').collect(Collectors.toList());
        List<Campaign> inactiveCampaigns = campaignList.stream().filter(f -> f.getActive() == 'N').collect(Collectors.toList());

        List<Campaign> campaigns = new ArrayList<>();
        campaigns.addAll(activeCampaigns);
        campaigns.addAll(inactiveCampaigns);

        List<CampaignDetailDTO> campaignDetailDTOs = new ArrayList<>();
        for (Campaign campaign : campaigns) {
            Optional<BookingLog> bookingLog = bookingLogRepository.findByCampaign(campaign.getId());
            int totalConsumed = 0;
            if (bookingLog.isPresent()) {
                totalConsumed = bookingLog.get().getTotalUsed();
            }

            CampaignDetailDTO campaignDetailDTO = CampaignMapper.convertToCampaignDetailResponse(campaign, totalConsumed);

            campaignDetailDTOs.add(campaignDetailDTO);
        }

        CampaignDetailListResponse campaignDetailListResponse = new CampaignDetailListResponse();
        campaignDetailListResponse.setCampaignDetails(campaignDetailDTOs);

        return ResponseMsg.successResponse(MsgConstant.Data.SUCCESS, campaignDetailListResponse);
    }

    @Override
    public ServerResponse campaignCreatorProfile(Long id) {
        Optional<Campaign> campaign = campaignRepository.findById(id);
        if (!campaign.isPresent()) {
            throw new InvalidDataException(ResponseMsg.failureResponse(MsgConstant.INVALID_DATA));
        }

        UserResponse userResponse = CampaignMapper.convertToCampaignCreatorProfileResponse(campaign.get().getCreatedBy());
        return ResponseMsg.successResponse(MsgConstant.Data.SUCCESS, userResponse);
    }

}
