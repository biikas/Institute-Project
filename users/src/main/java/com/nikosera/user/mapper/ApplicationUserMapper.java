package com.nikosera.user.mapper;

import com.nikosera.common.constant.ApiConstant;
import com.nikosera.common.util.StaticContextAccessor;
import com.nikosera.entity.ApplicationUser;
import com.nikosera.image.server.util.ImageUrlAttacher;
import com.nikosera.user.response.ApplicationUserDetail;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Narayan Joshi
 * <narenzoshi@gmail.com>
 */
@Slf4j
public class ApplicationUserMapper {

    private static final ImageUrlAttacher imageUrlAttacher = StaticContextAccessor.getBean(ImageUrlAttacher.class);

    public static ApplicationUserDetail mapToUserDetails(ApplicationUser user, String type) {
        if (user == null) {
            return null;
        }
        ApplicationUserDetail applicationUserDetail = new ApplicationUserDetail();
        applicationUserDetail.setId(user.getId());
        applicationUserDetail.setName(user.getName());
        applicationUserDetail.setUsername(user.getUsername());
        applicationUserDetail.setEmailAddress(user.getEmailAddress());
        applicationUserDetail.setActive(user.getActive());

        applicationUserDetail.setLastLoginTime(user.getLastLoginTime());
        applicationUserDetail.setLastPasswordChangeDate(user.getLastPasswordChangedDate());
        applicationUserDetail.setContactNo(user.getContactNo());
        applicationUserDetail.setImageUrl(imageUrlAttacher.attachForUser(user.getImageUrl()));
        applicationUserDetail.setIsMfaEnabled(user.getIsMfaEnabled());


        if (user.getCreatedBy() != null)
            applicationUserDetail.setCreatedBy(new ApplicationUserDetail(user.getCreatedBy().getId(), user.getCreatedBy().getName()));
        if (user.getLastModifiedBy() != null)
            applicationUserDetail.setLastModifiedBy(new ApplicationUserDetail(user.getLastModifiedBy().getId(), user.getLastModifiedBy().getName()));
        applicationUserDetail.setCreatedOn(user.getCreatedDate());
        applicationUserDetail.setLastModifiedOn(user.getLastModifiedDate());
        if (type.equals(ApiConstant.GET_BY_ID)) {
            applicationUserDetail.setUserGroup(UserGroupMapper.createUserGroupResponse(user.getUserGroup(), ApiConstant.GET_BY_ID));
        }

        return applicationUserDetail;
    }
}
