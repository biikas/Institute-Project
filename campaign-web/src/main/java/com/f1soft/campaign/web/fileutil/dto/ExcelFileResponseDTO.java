package com.f1soft.campaign.web.fileutil.dto;

import com.f1soft.campaign.common.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ExcelFileResponseDTO extends ModelBase {

    private boolean success;
    private String message;
    private List<String[]> excelDataList;
}
