package com.nikosera.user.validator;

import com.nikosera.common.exception.EmptyResultException;
import com.nikosera.common.exception.InvalidRequestException;
import com.nikosera.common.exception.ResourceAlreadyExistException;
import com.nikosera.common.util.OptionalList;
import com.nikosera.entity.ApplicationUser;
import com.nikosera.entity.QApplicationUser;
import com.nikosera.entity.UserGroup;
import com.nikosera.repository.repository.core.ApplicationUserRepository;
import com.nikosera.repository.repository.core.UserGroupRepository;
import com.nikosera.user.constant.UserConstant;
import com.nikosera.user.request.AddUserRequest;
import com.nikosera.user.request.UpdateUserRequest;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * @author Narayan Joshi
 * <narenzoshi@gmail.com>
 */
@Slf4j
@Component
@AllArgsConstructor
public class ApplicationUserValidator {

    private final ApplicationUserRepository repository;

    private final UserGroupRepository userGroupRepository;

    public void validateUserById(Long id, ApplicationUser loggedInUser) {

        List<Long> childrenGroups = userGroupRepository
                .findChildGroup(loggedInUser.getUserGroup().getId())
                .stream()
                .map(UserGroup::getId)
                .collect(Collectors.toList());

        /**
         *  Condition is all application user belonging to childrenGroups
         */
        final BooleanExpression condition = QApplicationUser.applicationUser.userGroup.id.in(childrenGroups);

        List<ApplicationUser> applicationUserList = OptionalList
                .from(repository.findAll(condition))
                .orElseThrow(() -> USER_NOT_FOUND());

        applicationUserList = applicationUserList.stream()
                .filter(user -> user.getId() == id)
                .collect(Collectors.toList());

        if (applicationUserList.isEmpty()) {
            throw USER_NOT_FOUND();

        }
    }

    public void validateNewUser(AddUserRequest request, ApplicationUser loggedInUser) {
        Optional<ApplicationUser> duplicateUser = repository.findByUsername(request.getUsername());

        if (duplicateUser.isPresent()) {
            DUPLICATE_USERNAME(request.getUsername());
        }

        if (request.getPasswordMode().equalsIgnoreCase(UserConstant.PasswordMode.MANUAL)
                && request.getPassword() == null) {
            throw INVALID_REQUEST();
        }

        List<Long> childrenGroups = userGroupRepository
                .findChildGroup(loggedInUser.getUserGroup().getId())
                .stream()
                .map(UserGroup::getId)
                .collect(Collectors.toList());

        childrenGroups.add(loggedInUser.getUserGroup().getId());

        compareUserGroup(childrenGroups, request.getUserGroupId());
    }

    public void validateUpdateUser(UpdateUserRequest request, ApplicationUser loggedInUser) {

        List<Long> childrenGroups = userGroupRepository
                .findChildGroup(loggedInUser.getUserGroup().getId())
                .stream()
                .map(UserGroup::getId)
                .collect(Collectors.toList());

        childrenGroups.add(loggedInUser.getUserGroup().getId());

        compareUserGroup(childrenGroups, request.getUserGroupId());
    }

    private void compareUserGroup(List<Long> assignedUserGroup, Long requestUserGroup) {
        if (!assignedUserGroup.contains(requestUserGroup)) {
            INVALID_REQUEST();
        }
    }
//
//    private Supplier<InvalidRequestException> INVALID_REQUEST=() -> {
//        log.error("Invalid request");
//        throw new InvalidRequestException("Invalid request");
//    };
//
//    private Supplier<EmptyResultException> USER_NOT_FOUND=() -> {
//        log.error("Users not found based on search parameter");
//        throw new EmptyResultException("Users not found based on search parameter");
//    };
//
//    private Consumer<String> DUPLICATE_USERNAME =(username) -> {
//        log.error("Duplicate username : " + username);
//        throw new ResourceAlreadyExistException("Duplicate username : " + username);
//    };

    private InvalidRequestException INVALID_REQUEST() {
        log.error("Invalid request");
        throw new InvalidRequestException("Invalid request");
    }

    private EmptyResultException USER_NOT_FOUND() {
        log.error("Users not found based on search parameter");
        throw new EmptyResultException("Users not found based on search parameter");
    }

    private ResourceAlreadyExistException DUPLICATE_USERNAME(String username) {
        log.error("Duplicate username : " + username);
        throw new ResourceAlreadyExistException("Duplicate username : " + username);
    }
}
