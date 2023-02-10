package com.f1soft.campaign.web.image;


import com.f1soft.campaign.common.dto.ModelBase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author Rashim Dhaubanjar
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImageUploadResponse extends ModelBase {

    private List<String> fileNames;

}
