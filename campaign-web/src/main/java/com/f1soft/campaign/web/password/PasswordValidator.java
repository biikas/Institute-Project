package com.f1soft.campaign.web.password;

import com.f1soft.campaign.common.constant.MsgConstant;
import com.f1soft.campaign.common.dto.Message;
import com.f1soft.campaign.common.util.MessageComposer;
import com.f1soft.campaign.web.constant.MsgParameter;
import com.f1soft.campaign.web.password.dto.PasswordPolicy;
import com.f1soft.campaign.web.util.StringHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class PasswordValidator {

    private String message;
    private String unicodeMessage;
    private String password;
    private PasswordPolicy passwordPolicy;


    private void initilaize(PasswordPolicy passwordPolicy) {
        this.passwordPolicy = passwordPolicy;
    }

    public boolean isValidPassword(String password, PasswordPolicy passwordPolicy) {
        initilaize(passwordPolicy);

        this.password = password;
        if (!validateLength()) {
            return false;
        }

        if (!validateAlpha()) {
            return false;
        }

        if (!validateNumber()) {
            return false;
        }

        return validateSpecialChar();
    }

    private boolean validateLength() {
        int passLen = password.length();
        if (passLen >= passwordPolicy.getMinLength() && passLen <= passwordPolicy.getMaxLength()) {
        } else {
            Message gprsMessage = MessageComposer.compose(MsgConstant.Password.INVALID_PASSWORD_LENGTH,
                    MessageComposer.getParameters());

            message = gprsMessage.getMessage();
            return false;
        }
        return true;
    }

    private boolean validateAlpha() {
        int countAlpha = 0;
        for (int i = 0; i < password.length(); i++) {
            if (!Character.isDigit(password.charAt(i))) {
                ++countAlpha;
            }
        }
        if (countAlpha < passwordPolicy.getAlphaMinLength()) {
            Message gprsMessage = MessageComposer.compose(MsgConstant.Password.MIN_ALPHA_CHARACTER,
                    MessageComposer.getParameters(MsgParameter.ALPHA_CHARACTER_LENGTH, String.valueOf(passwordPolicy.getAlphaMinLength())));

            message = gprsMessage.getMessage();
            return false;
        }
        if (passwordPolicy.getAlphaIndex() != null && passwordPolicy.getAlphaIndex().trim().length() > 0) {
            List<String> indexList = StringHelper.stringToList(passwordPolicy.getAlphaIndex(), ",");
            for (int i = 0; i < password.length(); i++) {
                int index = i + 1;
                if (indexList.contains(String.valueOf(index))) {
                    if (!isAlpha(password.charAt(i))) {
                        Message gprsMessage = MessageComposer.compose(MsgConstant.Password.INVALID_ALPHA_POSITION,
                                MessageComposer.getParameters());

                        message = gprsMessage.getMessage();
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean validateNumber() {

        int countNumber = 0;
        for (int i = 0; i < password.length(); i++) {
            if (Character.isDigit(password.charAt(i))) {
                ++countNumber;
            }
        }
        if (countNumber < passwordPolicy.getNumberMinLength()) {
            Message gprsMessage = MessageComposer.compose(MsgConstant.Password.MIN_NUMERIC_CHARACTER,
                    MessageComposer.getParameters(MsgParameter.NUMERIC_CHARACTER_LENGTH, String.valueOf(passwordPolicy.getNumberMinLength())));

            message = gprsMessage.getMessage();
            return false;
        }

        if (passwordPolicy.getNumberIndex() != null && passwordPolicy.getNumberIndex().trim().length() > 0) {
            List<String> indexList = StringHelper.stringToList(passwordPolicy.getNumberIndex(), ",");
            for (int i = 0; i < password.length(); i++) {
                int index = i + 1;
                if (indexList.contains(String.valueOf(index))) {
                    if (!isNumeric(password.charAt(i))) {
                        Message gprsMessage = MessageComposer.compose(MsgConstant.Password.INVALID_NUMERIC_POSITION,
                                MessageComposer.getParameters());

                        message = gprsMessage.getMessage();
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean validateSpecialChar() {

        int countSpecialChar = 0;

        String specialCharacters = passwordPolicy.getSpecialCharacters();

        String[] splitAlpha = specialCharacters.split(",");
        List<String> specialChars = new ArrayList<>();
        specialChars.addAll(Arrays.asList(splitAlpha));

        for (int i = 0; i < password.length(); i++) {
            if (specialChars.contains(String.valueOf(password.charAt(i)))) {
                ++countSpecialChar;
            }
        }
        if (countSpecialChar < Integer.parseInt(passwordPolicy.getSpecialCharMinLength())) {
            Message gprsMessage = MessageComposer.compose(MsgConstant.Password.MIN_SPECIAL_CHARACTER,
                    MessageComposer.getParameters());

            message = gprsMessage.getMessage();
            return false;
        }

        if (passwordPolicy.getSpecialCharIndex() != null && passwordPolicy.getSpecialCharIndex().trim().length() > 0) {
            List<String> indexList = StringHelper.stringToList(passwordPolicy.getSpecialCharIndex(), ",");
            for (int i = 0; i < password.length(); i++) {
                int index = i + 1;
                if (indexList.contains(String.valueOf(index))) {
                    if (specialChars.contains(String.valueOf(password.charAt(i)))) {
                        Message gprsMessage = MessageComposer.compose(MsgConstant.Password.INVALID_SPECIAL_CHARACTER_POSITION,
                                MessageComposer.getParameters());

                        message = gprsMessage.getMessage();
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public String getMessage() {
        return message;
    }

    public String getUnicodeMessage() {
        return unicodeMessage;
    }

    private boolean isAlpha(char data) {
        return !Character.isDigit(data);
    }

    private boolean isNumeric(char data) {
        return Character.isDigit(data);
    }
}
