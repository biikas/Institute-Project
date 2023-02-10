package com.f1soft.campaign.web.service.campaign.impl;

import com.f1soft.campaign.common.constant.MsgConstant;
import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.common.util.ResponseMsg;
import com.f1soft.campaign.entities.model.BookingLog;
import com.f1soft.campaign.entities.model.Campaign;
import com.f1soft.campaign.repository.BookingLogRepository;
import com.f1soft.campaign.repository.CampaignRepository;
import com.f1soft.campaign.web.dto.BookingLogDTO;
import com.f1soft.campaign.web.dto.response.BookingLogResponse;
import com.f1soft.campaign.web.mapper.CampaignMapper;
import com.f1soft.campaign.web.service.campaign.BookingDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Prajwol Hada
 */
@Slf4j
@Component
public class BookingDataServiceImpl implements BookingDataService {

    @Autowired
    private CampaignRepository campaignRepository;

    @Autowired
    private BookingLogRepository bookingLogRepository;


    @Override
    public ServerResponse getAllBookingLog() {

        List<Campaign> campaignList = campaignRepository.getAllCampaignList();

        List<BookingLogDTO> bookingLogDTOList = campaignList.stream().map(campaign -> {
            Optional<BookingLog> bookingLogOptional = bookingLogRepository.findByCampaign(campaign.getId());
            BookingLogDTO bookingLogDTO;
            if (bookingLogOptional.isPresent()) {
                bookingLogDTO = CampaignMapper.convertToBookingLogDTO(bookingLogOptional.get());
            } else {
                bookingLogDTO = CampaignMapper.convertToBookingLogDTO(campaign);
            }
            return bookingLogDTO;
        }).collect(Collectors.toList());

        BookingLogResponse bookingLogResponse = new BookingLogResponse(bookingLogDTOList);

        return ResponseMsg.successResponse(MsgConstant.Data.SUCCESS, bookingLogResponse);
    }
}
