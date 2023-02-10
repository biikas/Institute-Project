package com.f1soft.campaign.web.task.fileprocess;

import com.f1soft.campaign.common.config.application.SystemConfig;
import com.f1soft.campaign.web.constant.RunnerConfigConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author Nitesh Poudel
 */
@Slf4j
@Component
public class FileProcessJob {

    @Autowired
    private FileProcessTask fileProcessTask;
    @Autowired
    private SystemConfig systemConfig;

    public void job() {
        boolean runCampaignExcelFileProcessor = systemConfig.runnerConfig(RunnerConfigConstant.ENABLE_CAMPAIGN_FILE_PROCESSOR).equalsIgnoreCase("Y");
        if (runCampaignExcelFileProcessor) {
            fileProcessTask.call();
        }
    }
}
