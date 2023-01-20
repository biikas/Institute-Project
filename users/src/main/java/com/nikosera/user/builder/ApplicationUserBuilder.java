package com.nikosera.user.builder;

import com.nikosera.common.constant.MinioBucketMap;
import com.nikosera.common.exception.NoResultFoundException;
import com.nikosera.common.util.StaticContextAccessor;
import com.nikosera.entity.ApplicationUser;
import com.nikosera.entity.UserGroup;
import com.nikosera.image.server.operations.MinioObjectOperations;
import com.nikosera.user.request.AddUserRequest;
import com.nikosera.user.request.UpdateUserProfileRequest;
import com.nikosera.user.request.UpdateUserRequest;

import java.util.Date;

import static com.nikosera.notification.constant.StringConstants.NO;
import static com.nikosera.notification.constant.StringConstants.YES;

/**
 * @author Narayan Joshi
 * <narenzoshi@gmail.com>
 */

public class ApplicationUserBuilder {

    private static final MinioObjectOperations minioObjectOperations = StaticContextAccessor
            .getBean(MinioObjectOperations.class);

    public static ApplicationUser buildNewApplicationUser(AddUserRequest request, String encodedPassword) {
        ApplicationUser user = new ApplicationUser();
        user.setActive(YES);
        user.setName(request.getName());
        user.setUsername(request.getUsername());
        user.setPassword(encodedPassword);
        user.setEmailAddress(request.getEmail());
        user.setUserGroup(new UserGroup(request.getUserGroupId()));
        user.setContactNo(request.getContactNo());
        user.setIsMfaEnabled(NO);
        user.setHasEnforcedMfa(YES);
        return user;
    }

    public static ApplicationUser buildUpdateApplicationUser(UpdateUserRequest request, ApplicationUser user) {
        user.setActive(request.getActive() == null ? user.getActive() : request.getActive());
        user.setName(request.getName() == null ? user.getName() : request.getName());
        user.setEmailAddress(request.getEmailAddress() == null ? user.getEmailAddress() : request.getEmailAddress());
        user.setActive(request.getActive());
        user.setContactNo(request.getContactNo());
        if (user.getActive() == YES) {
            user.setWrongAttemptCount(0);
        }
        if (request.getUserGroupId() != null)
            user.setUserGroup(new UserGroup(request.getUserGroupId()));
        return user;
    }

    public static ApplicationUser buildChangePassword(String encodedNewPassword, ApplicationUser user) {
        user.setWrongAttemptCount(0);
        user.setPassword(encodedNewPassword);
        user.setLastPasswordChangedDate(new Date());
        return user;
    }

    public static ApplicationUser buildChangeImage(String imagePath, ApplicationUser user) {
        boolean fileExists = minioObjectOperations
                .doesFileExists(MinioBucketMap.BUCKET.get(MinioBucketMap.Selector.USER).getPath(), imagePath);
        if (!fileExists) {
            throw new NoResultFoundException("ImagePath Doesnt Exist");
        }
        user.setImageUrl(imagePath);
        return user;
    }

    public static void buildUpdateProfile(ApplicationUser applicationUser, UpdateUserProfileRequest request) {
        applicationUser.setName(request.getName());
        applicationUser.setContactNo(request.getContactNo());
    }
}
