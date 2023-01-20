package com.nikosera.clientweb.controller;

import com.nikosera.common.annotation.IsValidHash;
import com.nikosera.common.annotation.Unhash;
import com.nikosera.common.constant.ApiConstant;
import com.nikosera.common.constant.AuthorizationGrant;
import com.nikosera.common.dto.GenericResponse;
import com.nikosera.user.request.AddUserGroupRequest;
import com.nikosera.user.request.UpdateGroupRequest;
import com.nikosera.user.request.UserGroupSearchRequest;
import com.nikosera.user.service.UserGroupService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import static com.nikosera.common.constant.AuthorizationGrant.*;

/**
 * @author Narayan Joshi
 * <narenzoshi@gmail.com>
 */
@Slf4j
@Validated
@RestController
@RequestMapping(path = ApiConstant.Web.USER_GROUP)
@AllArgsConstructor
public class UserGroupController {

    private final UserGroupService service;

    @PreAuthorize(GROUP_PERMISSION + OR + CREATE_USER + OR + MANAGE_USER)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAllUserGroups() {
        GenericResponse response = service.getAllUserGroups();
        return ResponseEntity.ok(response);
    }

    @PreAuthorize(VIEW_GROUP_PERMISSION)
    @GetMapping(path = ApiConstant.Web.WRAP_GROUP_ID,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getUserGroupById(@NotNull @PathVariable(ApiConstant.Web.GROUP_ID) @IsValidHash @Unhash Long groupId) {
        GenericResponse response = service.getGroup(groupId);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize(CREATE_GROUP_PERMISSION)
    @PostMapping(path = ApiConstant.Web.CREATE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveUserGroup(@Valid @RequestBody AddUserGroupRequest request) {
        GenericResponse response = service.saveUserGroup(request);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize(MANAGE_GROUP_PERMISSION)
    @PostMapping(path = ApiConstant.Web.MANAGE + "/" + ApiConstant.Web.WRAP_GROUP_ID,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> manageUserGroup(@NotNull @PathVariable(ApiConstant.Web.GROUP_ID) @IsValidHash @Unhash Long groupId,
                                                  @Valid @RequestBody UpdateGroupRequest request) {
        GenericResponse response = service.modifyUserGroup(request, groupId);
        return ResponseEntity.ok(response);
    }

    @PostMapping(path = ApiConstant.Web.SEARCH,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> searchClient(@Valid @RequestBody UserGroupSearchRequest request) {
        GenericResponse response = service.searchUserGroup(request);
        return ResponseEntity.ok(response);
    }
}
