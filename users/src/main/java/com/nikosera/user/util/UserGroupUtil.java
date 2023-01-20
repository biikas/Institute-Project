package com.nikosera.user.util;

import com.nikosera.common.builder.MsgBuilder;
import com.nikosera.common.constant.ApiConstant;
import com.nikosera.common.constant.MsgConstant;
import com.nikosera.common.util.OptionalList;
import com.nikosera.entity.ApplicationUser;
import com.nikosera.entity.UserGroup;
import com.nikosera.repository.querydsl.GenericSearch;
import com.nikosera.repository.repository.core.UserGroupRepository;
import com.nikosera.user.mapper.UserGroupMapper;
import com.nikosera.user.request.UserGroupSearchRequest;
import com.nikosera.user.response.dto.UserGroupDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserGroupUtil {

    private final UserGroupRepository repository;
    private final GenericSearch<UserGroupSearchRequest> search;

    public List<UserGroupDTO> getUserGroups(ApplicationUser loginUser) {
        List<UserGroupDTO> userGroupDTOs = new ArrayList<>();
        List<UserGroup> childGroups = repository.findChildGroup(loginUser.getUserGroup().getId());
        for (UserGroup childGroup : childGroups) {
            userGroupDTOs.add(UserGroupMapper.createUserGroupResponse(childGroup, ApiConstant.GET_ALL));
        }
        return userGroupDTOs;
    }

    public List<UserGroupDTO> searchUserGroup(ApplicationUser loginUser, UserGroupSearchRequest request) {
        List<UserGroup> childGroups = OptionalList
                .of(repository
                        .findChildGroupConditional(
                                loginUser.getUserGroup().getId(),
                                StringUtils.isEmpty(request.getName()) ? null : "%" + request.getName() + "%", request.getActive() // TODO: Refine logic
                        )
                ).orElseThrow(MsgBuilder.emptySearchData(MsgConstant.Model.USER_GROUP));

        return childGroups
                .stream()
                .map((userGroup) -> UserGroupMapper.createUserGroupResponse(userGroup, ApiConstant.GET_ALL))
                .collect(Collectors.toList());
    }
}
