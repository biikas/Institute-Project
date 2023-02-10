package com.f1soft.campaign.web.controller.campaign;

import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.common.log.SkipAPILogging;
import com.f1soft.campaign.common.util.ResponseBuilder;
import com.f1soft.campaign.repository.Util.SearchQueryParameter;
import com.f1soft.campaign.web.service.campaign.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author <krishna.pandey@f1soft.com>
 */
@Slf4j
@RestController
@RequestMapping("report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @SkipAPILogging
    @PostMapping(value = "registration/search", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCampaignList(@NotNull @Valid @RequestBody SearchQueryParameter searchQueryParameter) {
        ServerResponse serverResponse = reportService.searchReport(searchQueryParameter);
        return ResponseBuilder.response(serverResponse);
    }
}
