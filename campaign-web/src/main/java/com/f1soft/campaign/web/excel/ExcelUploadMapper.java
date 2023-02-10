package com.f1soft.campaign.web.excel;

import com.f1soft.campaign.entities.model.Campaign;
import com.f1soft.campaign.web.fileutil.dto.ExcelFileUploadRequestDTO;

import java.io.InputStream;

/**
 * @Author Nitesh Poudel
 */
public class ExcelUploadMapper {

    private ExcelUploadMapper(){}

    public static ExcelFileUploadRequestDTO convertToExcelFileUploadRequestDTO(String file, InputStream inputStream, String fileUploadLocation, Campaign campaign) {
        ExcelFileUploadRequestDTO excelFileUploadRequestDTO = ExcelFileUploadRequestDTO.builder().
                fileName(file)
                .inputStream(inputStream)
                .tempLocation(fileUploadLocation)
                .campaign(campaign)
                .build();
        return excelFileUploadRequestDTO;
    }
}
