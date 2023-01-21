package com.nikosera.clientweb.remoablecontroller;

import com.nikosera.common.annotation.IsValidHash;
import com.nikosera.common.annotation.Unhash;
import com.nikosera.common.constant.ApiConstant;
import static com.nikosera.common.constant.AuthorizationGrant.*;
import com.nikosera.common.dto.GenericResponse;
import com.nikosera.user.request.*;
import com.nikosera.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


/**
 * @author Narayan Joshi
 * <narenzoshi@gmail.com>
 */
@Slf4j
@Validated
@PreAuthorize(USER)
@RequestMapping(path = ApiConstant.Web.USER)
@RestController
@AllArgsConstructor
public class UserController {

    private final UserService service;

    @PreAuthorize(USER)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllUsers() {
        GenericResponse response = service.getAllUsers();
        return ResponseEntity.ok(response);
    }

    @PreAuthorize(VIEW_USER)
    @GetMapping(path = ApiConstant.Web.WRAP_USER_ID,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUserById(@NotNull @PathVariable(ApiConstant.Web.USER_ID) @IsValidHash @Unhash Long userId) {
        GenericResponse response = service.getUserById(userId);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(path = ApiConstant.Web.LOGGEDIN_USER,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getLoggedInUser() {
        GenericResponse response = service.getLoggedInUser();
        return ResponseEntity.ok(response);
    }

    @PreAuthorize(CREATE_USER)
    @PostMapping(path = ApiConstant.Web.CREATE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser(@NotNull @Valid @RequestBody AddUserRequest request) {
        GenericResponse response = service.saveUser(request);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize(MANAGE_USER)
    @PostMapping(path = ApiConstant.Web.MANAGE + "/" + ApiConstant.Web.WRAP_USER_ID,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> manageUser(@NotNull @PathVariable(ApiConstant.Web.USER_ID) @IsValidHash @Unhash Long userId,
                                             @Valid @RequestBody UpdateUserRequest request) {
        GenericResponse response = service.modifyUser(request, userId);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(path = ApiConstant.Web.CHANGE_PASSWORD,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordRequest request) {
        GenericResponse response = service.changePassword(request);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(path = ApiConstant.Web.CHANGE_IMAGE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> changeImage(@Valid @RequestBody ChangeImageRequest request) {
        GenericResponse response = service.changeImage(request);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(path = ApiConstant.Web.QR,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> fetchQR() {
        GenericResponse response = service.getOtpQR();
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(path = ApiConstant.Web.CHANGE_OTP,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> changeTOTP(@Valid @RequestBody ChangeOTPModeRequest request) {
        GenericResponse response = service.changeMultiFactorAuth(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping(path = ApiConstant.Web.SEARCH,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> searchClient(@Valid @RequestBody UserSearchRequest request) {
        GenericResponse response = service.searchUser(request);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(path = ApiConstant.Web.UPDATE_PROFILE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> changeTOTP(@Valid @RequestBody UpdateUserProfileRequest request) {
        GenericResponse response = service.updateUserProfile(request);
        return ResponseEntity.ok(response);
    }

}
