package com.nikosera.user.response;

import com.nikosera.common.converter.CharacterStatusToBooleanConverter;
import com.nikosera.common.converter.LongToHashConverter;
import com.nikosera.common.dto.ModelBase;
import com.nikosera.user.response.dto.UserGroupDTO;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.util.Date;

/**
 * @author Narayan Joshi
 * <narenzoshi@gmail.com>
 */

@Data
public class ApplicationUserDetail extends ModelBase {

    @JsonSerialize(converter = LongToHashConverter.class)
    private Long id;

    private String name;

    private String username;

    private Character active;

    private String emailAddress;

    private String contactNo;

    private Character isSuperAdmin;

    private String lastLoginTime;

    private Date lastPasswordChangeDate;

    private ApplicationUserDetail createdBy;

    private Date createdOn;

    private ApplicationUserDetail lastModifiedBy;

    private Date lastModifiedOn;

    private UserGroupDTO userGroup;

    private String imageUrl;

    @JsonSerialize(converter = CharacterStatusToBooleanConverter.class)
    private Character isMfaEnabled;

    private String remarks;

    public ApplicationUserDetail() {
    }

    public ApplicationUserDetail(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
