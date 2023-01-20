package com.nikosera.user.service.impl;

import com.nikosera.cas.totp.code.verifier.CodeVerifier;
import com.nikosera.cas.totp.qr.QrBuilder;
import com.nikosera.common.aop.MethodLogging;
import com.nikosera.common.builder.MsgBuilder;
import com.nikosera.common.builder.ResponseBuilder;
import com.nikosera.common.constant.ApiConstant;
import com.nikosera.common.constant.MsgConstant;
import com.nikosera.common.dto.GenericResponse;
import com.nikosera.common.util.ApplicationUtil;
import com.nikosera.common.util.OptionalList;
import com.nikosera.entity.ApplicationUser;
import com.nikosera.entity.QApplicationUser;
import com.nikosera.entity.UserGroup;
import com.nikosera.repository.querydsl.GenericSearch;
import com.nikosera.repository.repository.core.ApplicationUserRepository;
import com.nikosera.repository.repository.core.UserGroupRepository;
import com.nikosera.user.builder.ApplicationUserBuilder;
import com.nikosera.user.constant.UserConstant;
import com.nikosera.user.invoker.NotificationInvoker;
import com.nikosera.user.mapper.ApplicationUserMapper;
import com.nikosera.user.request.*;
import com.nikosera.user.response.ApplicationUserDetail;
import com.nikosera.user.response.OTPResponse;
import com.nikosera.user.service.UserService;
import com.nikosera.user.util.PasswordUtil;
import com.nikosera.user.validator.ApplicationUserValidator;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Narayan Joshi
 * <narenzoshi@gmail.com>
 */
@Slf4j
@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    private final ApplicationUserRepository applicationUserRepository;
    private final ApplicationUserValidator userValidator;
    private final ApplicationUtil applicationUtil;
    private final BCryptPasswordEncoder passwordEncoder;
    private final CodeVerifier codeVerifier;
    private final QrBuilder qrBuilder;
    private final GenericSearch<UserSearchRequest> search;
    private final UserGroupRepository userGroupRepository;
    private final NotificationInvoker notificationInvoker;

    @MethodLogging
    @Override
    public GenericResponse getAllUsers() {
        ApplicationUser loggedInUser = applicationUtil.loggedInUser();
        List<ApplicationUserDetail> applicationUserDetails = new ArrayList<>();

        Long loggedInUserGroup = loggedInUser.getUserGroup().getId();

        List<Long> childrenGroups = userGroupRepository
                .findChildGroup(loggedInUserGroup)
                .stream()
                .map(UserGroup::getId)
                .collect(Collectors.toList());

        log.debug("Finding user selected groups: {}", childrenGroups);

        /**
         *  Condition is all application user belonging to childrenGroups
         */
        final BooleanExpression condition = QApplicationUser.applicationUser.userGroup.id.in(childrenGroups);

        List<ApplicationUser> applicationUserList = OptionalList
                .from(applicationUserRepository.findAll(condition, Sort.by(Sort.Direction.ASC, "name")))
                .orElseThrow(MsgBuilder.emptyDataList(MsgConstant.Model.USER));

        applicationUserDetails = applicationUserList
                .stream()
                .map(user -> ApplicationUserMapper.mapToUserDetails(user, ApiConstant.GET_ALL))
                .collect(Collectors.toList());

        return ResponseBuilder
                .buildSuccessMessage(applicationUserDetails, MsgBuilder.successList(MsgConstant.Model.USER));
    }

    @MethodLogging
    @Override
    public GenericResponse getUserById(Long userId) {
        log.debug("Get User By Id Service Invoked...");
        userValidator.validateUserById(userId, applicationUtil.loggedInUser());

        ApplicationUser applicationUser = getApplicationUserById(userId);

        ApplicationUserDetail applicationUserDetail = ApplicationUserMapper
                .mapToUserDetails(applicationUser, ApiConstant.GET_BY_ID);
        return ResponseBuilder
                .buildSuccessMessage(applicationUserDetail, MsgBuilder.successSingle(MsgConstant.Model.USER));
    }

    @MethodLogging
    @Override
    public GenericResponse getLoggedInUser() {
        log.debug("Get Logged In Service Invoked...");

        ApplicationUserDetail applicationUserDetail = ApplicationUserMapper
                .mapToUserDetails(
                        applicationUtil.loggedInUser(),
                        ApiConstant.GET_BY_ID
                );

        return ResponseBuilder
                .buildSuccessMessage(applicationUserDetail, MsgConstant.SUCCESS_LOGIN_USER_FETCH);
    }

    @MethodLogging
    @Override
    public GenericResponse saveUser(AddUserRequest request) {
        log.debug("Create User Service Invoked...");

        ApplicationUser loggedInUser = applicationUtil.loggedInUser();
        userValidator.validateNewUser(request, loggedInUser);

        if (request.getPasswordMode().equalsIgnoreCase(UserConstant.PasswordMode.AUTO)) {
            request.setPassword(PasswordUtil.getRandomPassword());
        }

        ApplicationUser user = ApplicationUserBuilder
                .buildNewApplicationUser(request, passwordEncoder.encode(request.getPassword()));

        saveApplicationUser(user);

        if (request.getPasswordMode().equalsIgnoreCase(UserConstant.PasswordMode.AUTO)) {
            notificationInvoker.createUserNotification(user, request.getPassword());
        }

        return ResponseBuilder
                .buildSuccessMessage(MsgBuilder.successSave(MsgConstant.Model.USER));
    }

    @MethodLogging
    @Override
    public GenericResponse modifyUser(UpdateUserRequest request, Long userId) {
        userValidator.validateUserById(userId, applicationUtil.loggedInUser());

        ApplicationUser loggedInUser = applicationUtil.loggedInUser();
        userValidator.validateUpdateUser(request, loggedInUser);

        ApplicationUser user = getApplicationUserById(userId);

        ApplicationUserBuilder.buildUpdateApplicationUser(request, user);

        saveApplicationUser(user);

        return ResponseBuilder
                .buildSuccessMessage(MsgBuilder.successSave(MsgConstant.Model.USER));
    }

    @MethodLogging
    @Override
    public GenericResponse changePassword(ChangePasswordRequest request) {
        log.debug("Change Password Service Invoked");

        log.debug("Change Password Request :: {}", request);

        ApplicationUser user = applicationUtil.loggedInUser();

        log.debug("DB password :: " + user.getPassword());
        log.debug("password :: " + passwordEncoder.encode(request.getOldPassword()));
        if (passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            if (request.getOldPassword().equals(request.getPassword())) {
                return ResponseBuilder.failureResponse(MsgConstant.SAME_PASSWORD_FAILURE);
            }
            user = ApplicationUserBuilder.buildChangePassword(passwordEncoder.encode(request.getPassword()), user);
            notificationInvoker.changePasswordNotification(user);

            saveApplicationUser(user);

            return ResponseBuilder
                    .buildSuccessMessage(MsgConstant.CHANGE_PASSWORD_SUCCESS);
        } else {
            return ResponseBuilder
                    .failureResponse(MsgConstant.CURRENT_PASSWORD_NOT_CORRECT_FAILURE);
        }
    }

    @MethodLogging
    @Override
    public GenericResponse changeImage(ChangeImageRequest request) {
        ApplicationUser applicationUser = ApplicationUserBuilder
                .buildChangeImage(request.getImagePath(), applicationUtil.loggedInUser());
        saveApplicationUser(applicationUser);
        return ResponseBuilder
                .buildSuccessMessage(MsgConstant.IMAGE_CHANGE_SUCCESS);
    }

    @MethodLogging
    @Override
    public GenericResponse changeMultiFactorAuth(ChangeOTPModeRequest request) {
        GenericResponse genericResponse;

        ApplicationUser user = applicationUtil.loggedInUser();

        log.debug("DB password :: " + user.getPassword());
        log.debug("password :: " + passwordEncoder.encode(request.getPassword()));

        if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {

            user.setIsMfaEnabled(request.getEnabled());
            user.setLastModifiedDate(new Date());

            if (request.getEnabled() == 'Y') {

                boolean isValid = codeVerifier.isValidCode(user.getSecret(), request.getToken());
                if (!isValid) {
                    return ResponseBuilder
                            .failureResponse(MsgConstant.Authorization.INVALID_OTP);
                } else {
                    genericResponse = ResponseBuilder
                            .buildSuccessMessage(MsgConstant.Authorization.OTP_ENABLED_SUCCESS);
                }

            } else {
                genericResponse = ResponseBuilder
                        .buildSuccessMessage(MsgConstant.Authorization.OTP_DISABLED_SUCCESS);
            }
            saveApplicationUser(user);
        } else {
            genericResponse = ResponseBuilder
                    .failureResponse(MsgConstant.CURRENT_PASSWORD_NOT_CORRECT_FAILURE);
        }
        return genericResponse;
    }

    @MethodLogging
    @Override
    public GenericResponse getOtpQR() {
        ApplicationUser user = applicationUtil.loggedInUser();
        OTPResponse otpResponse = new OTPResponse();
        otpResponse.setIsMfaEnabled(user.getIsMfaEnabled());
        String message = "";

        if (user.getIsMfaEnabled() == 'N') {
            String qrCode = qrBuilder.build(user);
            otpResponse.setQr(qrCode);
            message = "Qr fetched Successfully";
        } else {
            message = "Qr already scanned";
        }

        return ResponseBuilder.buildSuccessMessage(otpResponse, message);
    }

    @MethodLogging
    @Override
    public GenericResponse searchUser(UserSearchRequest request) {
        ApplicationUser loggedInUser = applicationUtil.loggedInUser();
        List<ApplicationUserDetail> applicationUserDetails = new ArrayList<>();

        Long loggedInUserGroup = loggedInUser.getUserGroup().getId();

        List<Long> childrenGroups = userGroupRepository
                .findChildGroup(loggedInUserGroup)
                .stream()
                .map(UserGroup::getId)
                .collect(Collectors.toList());

        log.debug("Finding user selected groups: {}", childrenGroups);

        /**
         *  Condition is all application user belonging to childrenGroups and given searchCriteria
         *  SELECT * FROM application_user WHERE user_group_id in (childrenGroups) AND name LIKE "%%" AND username LIKE "%%" AND status = 'Y'
         */
        final BooleanExpression condition = QApplicationUser
                .applicationUser
                .userGroup
                .id
                .in(childrenGroups)
                .and(search.buildPredicate(request));

        List<ApplicationUser> applicationUserList = OptionalList
                .from(applicationUserRepository.findAll(condition))
                .orElseThrow(MsgBuilder.emptySearchData(MsgConstant.Model.USER));

        applicationUserDetails = applicationUserList
                .stream()
                .map(user -> ApplicationUserMapper.mapToUserDetails(user, ApiConstant.GET_ALL))
                .collect(Collectors.toList());

        return ResponseBuilder
                .buildSuccessMessage(applicationUserDetails, MsgBuilder.successList(MsgConstant.Model.USER));
    }

    @MethodLogging
    @Override
    public GenericResponse updateUserProfile(UpdateUserProfileRequest request) {
        ApplicationUser loggedInUser = applicationUtil.loggedInUser();
        ApplicationUserBuilder.buildUpdateProfile(loggedInUser, request);
        saveApplicationUser(loggedInUser);
        return ResponseBuilder.buildSuccessMessage(MsgConstant.USER_PROFILE_UPDATE_SUCCESS);
    }

    private void saveApplicationUser(ApplicationUser applicationUser) {
        applicationUserRepository.save(applicationUser);

    }

    private ApplicationUser getApplicationUserById(Long userId) {
        return applicationUserRepository.findById(userId)
                .orElseThrow(MsgBuilder.doesntExist(MsgConstant.Model.USER));
    }
}