package com.f1soft.campaign.web.fileutil;

import com.f1soft.campaign.common.constant.MsgConstant;
import com.f1soft.campaign.common.exception.DataNotFoundException;
import com.f1soft.campaign.common.util.ResponseMsg;
import com.f1soft.campaign.web.fileutil.dto.ExcelFileResponseDTO;
import com.f1soft.campaign.web.fileutil.dto.ExcelFileUploadRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Slf4j
@Component
public class ExcelFileHelper {

    public ExcelFileResponseDTO read(ExcelFileUploadRequestDTO excelFileUploadRequestDTO) {

        log.info("Reading uploaded file");
        ExcelFileResponseDTO readFileResponseDTO = new ExcelFileResponseDTO();
        ExcelFileReader excelFileReader;
        final int excelRowSize = 500000;
        InputStream inputStream = excelFileUploadRequestDTO.getInputStream();

        String excelFileName = excelFileUploadRequestDTO.getTempLocation() + "\\" + excelFileUploadRequestDTO.getFileName();
        try {
            excelFileReader = new ExcelFileReader(excelFileName);
            List<String[]> excelFileRows = excelFileReader.readRows(excelRowSize);

            log.info("Excel files rows :" + excelFileRows.size());

            if (excelFileRows.isEmpty()) {
                throw new DataNotFoundException(ResponseMsg.failureResponse(MsgConstant.FileUpload.EMPTY_FILE));
            }
            readFileResponseDTO.setSuccess(true);
            readFileResponseDTO.setExcelDataList(excelFileRows);

            inputStream.close();
        } catch (Exception e) {
            throw new DataNotFoundException(ResponseMsg.failureResponse(MsgConstant.FileUpload.FILE_PROCESSING_EXCEPTION));
        }
        return readFileResponseDTO;
    }
}
