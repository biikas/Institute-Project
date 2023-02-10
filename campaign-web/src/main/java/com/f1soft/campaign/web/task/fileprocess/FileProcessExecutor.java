package com.f1soft.campaign.web.task.fileprocess;

import com.f1soft.campaign.common.cbs.dto.CampaignJob;
import com.f1soft.campaign.common.config.application.SystemConfig;
import com.f1soft.campaign.common.config.constant.AdminConfigConstant;
import com.f1soft.campaign.entities.model.CampaignExcelFiles;
import com.f1soft.campaign.web.excel.ExcelUploadManager;
import com.f1soft.campaign.web.excel.ExcelUploadMapper;
import com.f1soft.campaign.web.fileutil.ExcelFileHelper;
import com.f1soft.campaign.web.fileutil.dto.ExcelFileUploadRequestDTO;
import com.f1soft.campaign.web.manager.FileManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;

/**
 * @Author Nitesh Poudel
 */
@Slf4j
@Component
public class FileProcessExecutor {

    @Autowired
    private SystemConfig systemConfig;
    @Autowired
    private FileManager fileManager;
    @Autowired
    private ExcelFileHelper excelFileHelper;
    @Autowired
    private ExcelUploadManager excelUploadManager;

    public CampaignJob processFile(CampaignExcelFiles campaignExcelFiles) {
        String fileLocation = systemConfig.adminConfig(AdminConfigConstant.EXCEL_FILE_UPLOAD_LOCATION);

        InputStream inputStream = fileManager.getFileInputStream(fileLocation, campaignExcelFiles.getFileName());
        ExcelFileUploadRequestDTO excelFileUploadRequestDTO = ExcelUploadMapper.convertToExcelFileUploadRequestDTO(
                campaignExcelFiles.getFileName(), inputStream,
                fileLocation, campaignExcelFiles.getCampaign());

        return excelUploadManager.processData(excelFileUploadRequestDTO);
    }
}
