package com.f1soft.campaign.web.excel;

import com.f1soft.campaign.common.dto.ServerResponse;
import org.springframework.web.multipart.MultipartFile;

public interface ExcelUploadService {

    ServerResponse uploadExcel(String type, MultipartFile file);
}
