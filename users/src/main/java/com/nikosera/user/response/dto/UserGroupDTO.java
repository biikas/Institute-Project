package com.nikosera.user.response.dto;

import com.nikosera.common.converter.LongToHashConverter;
import com.nikosera.common.dto.ModelBase;
import com.nikosera.entity.UserGroup;
import com.nikosera.user.response.ApplicationUserDetail;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author Narayan Joshi
 * <narenzoshi@gmail.com>
 */

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserGroupDTO extends ModelBase {
    @JsonSerialize(converter = LongToHashConverter.class)
    private Long id;
    private String name;
    private String description;
    private String remarks;
    private Character active;
    private UserGroupDTO parentId;
    private ApplicationUserDetail createdBy;
    private ApplicationUserDetail modifiedBy;
    private Date createdOn;
    private Date modifiedOn;
    private List<UserMenuDTO> permissions;

    public UserGroupDTO() {
    }

    public UserGroupDTO(Long id, String name, UserGroup parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId == null ? null : new UserGroupDTO(parentId.getId(), parentId.getName(), parentId.getParent());
    }
}
