package com.f1soft.campaign.web.excel;

import com.f1soft.campaign.common.cbs.dto.CampaignJob;
import com.f1soft.campaign.web.campaign.manager.CampaignEligibleUserManager;
import com.f1soft.campaign.web.dto.CampaignEligibleUserDTO;
import com.f1soft.campaign.web.fileutil.ExcelFileHelper;
import com.f1soft.campaign.web.fileutil.dto.ExcelFileResponseDTO;
import com.f1soft.campaign.web.fileutil.dto.ExcelFileUploadRequestDTO;
import com.f1soft.campaign.web.manager.FileManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Nitesh Poudel
 */
@Slf4j
@Component
public class ExcelUploadManager {

    protected List<String[]> excelDatas;

    @Autowired
    private ExcelFileHelper excelFileHelper;
    @Autowired
    private FileManager fileManager;
    @Autowired
    private CampaignEligibleUserManager campaignEligibleUserManager;

    public CampaignJob processData(ExcelFileUploadRequestDTO excelFileUploadRequestDTO) {

        ExcelFileResponseDTO excelFileResponseDTO = excelFileHelper.read(excelFileUploadRequestDTO);
        if (excelFileResponseDTO.isSuccess()) {
            setExcelDatas(excelFileResponseDTO.getExcelDataList());
            uploadData(excelFileUploadRequestDTO);
            return new CampaignJob(true, "Data uploaded");
        } else {
            return new CampaignJob(false, "Upload Failed");
        }

    }

    public List<String[]> getExcelDatas() {
        return excelDatas;
    }

    public void setExcelDatas(List<String[]> excelDatas) {
        excelDatas.remove(0);
        this.excelDatas = excelDatas;
    }

    private void uploadData(ExcelFileUploadRequestDTO excelFileUploadRequestDTO) {
        List<CampaignEligibleUserDTO> tempDataList = new ArrayList<>();
        for (String[] data : getExcelDatas()) {
            CampaignEligibleUserDTO campaignEligibleUserDTO = new CampaignEligibleUserDTO();

            if (excelFileUploadRequestDTO.getCampaign().getPromoCode() != null) {
                campaignEligibleUserDTO.setMobileNumber(data[0]);
                campaignEligibleUserDTO.setPromoCode(excelFileUploadRequestDTO.getCampaign().getPromoCode());
                campaignEligibleUserDTO.setCampaign(excelFileUploadRequestDTO.getCampaign());
                tempDataList.add(campaignEligibleUserDTO);
            } else if (excelFileUploadRequestDTO.getCampaign().getPromoCode() == null && data.length == 2) {
                campaignEligibleUserDTO.setMobileNumber(data[0]);
                campaignEligibleUserDTO.setPromoCode(data[1]);
                campaignEligibleUserDTO.setCampaign(excelFileUploadRequestDTO.getCampaign());
                tempDataList.add(campaignEligibleUserDTO);
            }
        }
        campaignEligibleUserManager.insertData(tempDataList);
    }
}
