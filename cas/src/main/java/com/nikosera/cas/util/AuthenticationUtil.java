package com.nikosera.cas.util;

import com.nikosera.common.dto.CustomUserDetail;
import com.nikosera.common.exception.UserPermanentBlockedException;
import com.nikosera.common.exception.UserTemporarilyBlockedException;
import com.nikosera.entity.ApplicationUser;
import com.nikosera.repository.repository.core.ApplicationUserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.nikosera.common.constant.MsgConstant.Authorization.*;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
@Slf4j
@Component
@AllArgsConstructor
public class AuthenticationUtil {

    private final ApplicationUserRepository applicationUserRepository;

    public void increaseIncorrectDetailAttempt(CustomUserDetail userDetails) {
        int wrongPasswordAttemptsAllowed = 4;
        ApplicationUser applicationUser = userDetails.getCurrentUser();
        if (applicationUser.getWrongAttemptCount() >= wrongPasswordAttemptsAllowed) {
            log.debug("Authentication failed: User Blocked due to multiple wrong password attempt.");
            throw new UserPermanentBlockedException(BLOCKED);
        }
        if (applicationUser.getWrongAttemptCount() < wrongPasswordAttemptsAllowed) {
            applicationUser.setWrongAttemptCount(applicationUser.getWrongAttemptCount() + 1);
            if (applicationUser.getWrongAttemptCount() == wrongPasswordAttemptsAllowed) {
                applicationUser.setActive('N');
                applicationUser.setBlockedDate(new Date());
            }
            applicationUserRepository.save(applicationUser);
            showMessageAccordingToWrongPasswordAttemptsLeft(wrongPasswordAttemptsAllowed, applicationUser);
        }
    }

    public void showMessageAccordingToWrongPasswordAttemptsLeft(int wrongPasswordAttemptsAllowed, ApplicationUser user) throws NumberFormatException {
        int passwordAttemptsLeft = wrongPasswordAttemptsAllowed - user.getWrongAttemptCount();

        if (passwordAttemptsLeft == 0) {
            throw new UserTemporarilyBlockedException(BLOCKED);
        } else if (passwordAttemptsLeft == 1) {
            throw new UserTemporarilyBlockedException(FINAL_ATTEMPT_WARNING);
        } else if (passwordAttemptsLeft >= 2) {
            throw new UserTemporarilyBlockedException(INCORRECT_CREDENTIAL);
        }

    }


    public void updateUser(ApplicationUser applicationUser) {
        if (applicationUser.getWrongAttemptCount() > 0) {
            applicationUser.setWrongAttemptCount(0);
            applicationUserRepository.save(applicationUser);
        }
    }
}
