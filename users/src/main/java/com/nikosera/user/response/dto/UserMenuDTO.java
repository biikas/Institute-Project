package com.nikosera.user.response.dto;

import com.nikosera.common.converter.LongToHashConverter;
import com.nikosera.common.dto.ModelBase;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.util.List;

/**
 * @author Narayan Joshi
 * <narenzoshi@gmail.com>
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserMenuDTO extends ModelBase {
    @JsonSerialize(converter = LongToHashConverter.class)
    private Long id;
    private String action;
    private Character active;
    private String description;
    private String icon;
    private Character isMultilevel;
    private String name;
    private String navType;
    @JsonSerialize(converter = LongToHashConverter.class)
    private Long parentId;
    private String path;
    private String sortOrder;
    private List<UserMenuDTO> childPermission;

}
