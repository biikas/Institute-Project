package com.f1soft.campaign.web.fileutil.dto;

import com.f1soft.campaign.common.dto.ModelBase;
import com.f1soft.campaign.entities.model.Campaign;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.InputStream;

@Getter
@Setter
@Builder
public class ExcelFileUploadRequestDTO extends ModelBase {

    private InputStream inputStream;
    private String fileName;
    private String tempLocation;
    private Campaign campaign;
}
