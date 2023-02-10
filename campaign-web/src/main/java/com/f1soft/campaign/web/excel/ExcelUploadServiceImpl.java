package com.f1soft.campaign.web.excel;

import com.f1soft.campaign.common.config.application.SystemConfig;
import com.f1soft.campaign.common.config.constant.AdminConfigConstant;
import com.f1soft.campaign.common.constant.MsgConstant;
import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.common.util.ResponseMsg;
import com.f1soft.campaign.repository.ExcelTypeRepository;
import com.f1soft.campaign.web.manager.FileManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Nitesh Poudel
 */
@Slf4j
@Service
public class ExcelUploadServiceImpl implements ExcelUploadService{

    @Autowired
    private ExcelTypeRepository excelTypeRepository;
    @Autowired
    private FileManager fileManager;
    @Autowired
    private SystemConfig systemConfig;

    public ServerResponse uploadExcel(String type, MultipartFile file) {
        String fileUploadLocation = systemConfig.adminConfig(AdminConfigConstant.EXCEL_FILE_UPLOAD_LOCATION);

        String savedFileName = fileManager.saveFile(file, fileUploadLocation);

        List<String> uploadedFileName = new ArrayList<>();
        uploadedFileName.add(savedFileName);

        ExcelUploadResponse excelUploadResponse = new ExcelUploadResponse(uploadedFileName);
        return ResponseMsg.successResponse(MsgConstant.FileUpload.FILE_UPLOAD_SUCCESS, excelUploadResponse);
    }
}
