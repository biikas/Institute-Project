package com.f1soft.campaign.web.excel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @Author Nitesh Poudel
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExcelUploadResponse {

    private List<String> fileNames;
}
