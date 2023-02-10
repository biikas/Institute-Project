package com.f1soft.campaign.web.task.fileprocess;

import com.f1soft.campaign.common.cbs.dto.CampaignJob;
import com.f1soft.campaign.common.util.ThreadUtil;
import com.f1soft.campaign.entities.model.CampaignExcelFiles;
import com.f1soft.campaign.repository.CampaignExcelFileRepository;
import com.f1soft.campaign.web.constant.FileUploadProcessConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author Nitesh Poudel
 */
@Slf4j
@Component
public class FileProcessTask {

    @Autowired
    private CampaignExcelFileRepository campaignExcelFileRepository;
    @Autowired
    private FileProcessExecutor fileProcessExecutor;


    public void call() {
        List<CampaignExcelFiles> campaignExcelFiles = campaignExcelFileRepository.findUnprocessedFile();

        if (campaignExcelFiles.size() > 0) {
            campaignExcelFiles.stream()
                    .forEach(file -> {
                        CampaignJob jobResponse = fileProcessExecutor.processFile(file);
                        if (jobResponse.isSuccess()) {
                            file.setProcessStatus(FileUploadProcessConstant.FILE_PROCESSED);
                        } else {
                            file.setProcessStatus(FileUploadProcessConstant.FILE_PROCESS_FAILED);
                        }
                        campaignExcelFileRepository.save(file);
                    });

        } else {
            ThreadUtil.sleepForSecond(60);
        }
    }
}
