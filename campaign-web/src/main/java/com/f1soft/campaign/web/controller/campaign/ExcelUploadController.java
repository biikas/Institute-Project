package com.f1soft.campaign.web.controller.campaign;

import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.common.util.ResponseBuilder;
import com.f1soft.campaign.web.excel.ExcelUploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author Nitesh Poudel
 */
@Slf4j
@RestController
@RequestMapping("campaign/excel")
public class ExcelUploadController {

    @Autowired
    private ExcelUploadService excelUploadService;

    @PostMapping(value = "upload/{type}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> uploadExcelFile(@PathVariable("type") String type, @RequestParam("file") MultipartFile file) {
        ServerResponse serverResponse = excelUploadService.uploadExcel(type, file);
        return ResponseBuilder.response(serverResponse);
    }
}
