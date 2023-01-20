package com.nikosera.user.service;

import com.nikosera.common.dto.GenericResponse;
import com.nikosera.user.request.*;

/**
 * @author Narayan Joshi
 * <narenzoshi@gmail.com>
 */

public interface UserService {

    GenericResponse getAllUsers();

    GenericResponse getUserById(Long userId);

    GenericResponse getLoggedInUser();

    GenericResponse saveUser(AddUserRequest request);

    GenericResponse modifyUser(UpdateUserRequest request, Long userId);

    GenericResponse changePassword(ChangePasswordRequest request);

    GenericResponse changeImage(ChangeImageRequest request);

    GenericResponse changeMultiFactorAuth(ChangeOTPModeRequest request);

    GenericResponse getOtpQR();

    GenericResponse searchUser(UserSearchRequest request);

    GenericResponse updateUserProfile(UpdateUserProfileRequest request);
}
