package com.nikosera.common.constant;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
public interface MsgConstant {

    String SUCCESS_LOGIN_USER_FETCH = "Logged in user fetched successfully";
    String SAME_PASSWORD_FAILURE = "Current password and new password cannot be same";
    String CHANGE_PASSWORD_SUCCESS = "Password Changed successfully";
    String CURRENT_PASSWORD_NOT_CORRECT_FAILURE = "Current password does not match";
    String IMAGE_CHANGE_SUCCESS = "Image Changed successfully";
    String USER_PROFILE_UPDATE_SUCCESS = "Profile updated";

    interface Image {
        String INVALID_IMAGE = "Invalid Image.";
        String UPLOAD_IMAGE_FAILED = "Image Upload Failed.";
        String UPLOAD_IMAGE_SUCCESS = "Image Upload Success.";
    }

    interface Authorization {
        String UNAUTHORIZED = "Unauthorized.";
        String AUTH_HEADER_NOT_FOUND = "Authorization header not found";
        String AUTH_HEADER_NOT_VALID = "Authorization header not valid";
        String BLOCKED = "User Blocked. Please contact admin.";
        String INCORRECT_CREDENTIAL = "Invalid username/ password. Please try again.";
        String CORRECT_CREDENTIAL = "Credential Validated.";
        String FINAL_ATTEMPT_WARNING = "Final attempt; Your account will be blocked after this attempt.";
        String SESSION_EXPIRED = "Session Expired! Please login again.";
        String SYSTEM_ERROR = "System Error";
        String INVALID_OTP = "Otp is not valid";
        String OTP_ENABLED_SUCCESS = "Otp enabled successfully";
        String OTP_DISABLED_SUCCESS = "Otp disabled successfully";

        interface JWT {
            String INVALID_TOKEN = "Invalid Token.";
            String TOKEN_GENERATION_FAILURE = "Failed to Generate Token.";
        }
    }

    String CACHE_CLEAR_FAILED = " and Cache clear failed";

    interface SuccessResponse {
        String SUCCESS_FETCH_LIST = "%s's fetched successfully";
        String SUCCESS_FETCH_SINGLE = "%s fetched successfully";
        String SUCCESS_STATUS_CHANGE = "%s status changed successfully";
        String SUCCESS_SAVE = "%s saved successfully";
        String SUCCESS_UPDATE = "%s updated successfully";
    }

    String EMPTY_LIST_DATA = "%s's not found.";
    String NO_DATA = "%s not found.";
    String NO_DATA_WITH_DATA_NAME = "%s not found for %s.";
    String EMPTY_SEARCH_DATA = "%s not found based on search parameter.";
    String INVALID_REQUEST = "Invalid Request.";


    interface Model {
        String CURRENCY = "Currency";
        String COUNTRY = "Country";
        String CLIENT_PROFILE = "Client profile";
        String USER_PROFILE = "User profile";
        String USER = "User";
        String USER_GROUP = "User Group";
        String CLIENT = "Client";
        String SERVICE = "Service";
        String APPLICATION_CONFIGURATION = "Application Configuration";
        String API_URL = "Api Url";
        String APARTMENT_COUNTER = "Apartment Counter";
        String APARTMENT_COUNTER_ACQUIRER = "Apartment Counter Acquirer";
        String ELECTRICITY_COUNTER = "Electricity Counter";
        String ELECTRICITY_COUNTER_ACQUIRER = "Electricity Counter Acquirer";
        String DEPOSITORY_PARTICIPANT = "Depository Participant";
        String NCELL_DATA = "Ncell Data";
        String NEA_COUNTER = "Nea Counter";
        String INSURANCE_COUNTER = "Insurance Counter";
        String INSURANCE_COUNTER_ACQUIRER = "Insurance Counter Acquirer";
        String SERVICE_GROUP = "Service Group";
        String PAYMENT_CODE_TYPE = "Payment Code Type";
        String CHANNEL = "Channel";
        String MENU = "Menu";
        String KHANEPANI_COUNTER = "Khanepani Counter";
        String KHANEPANI_COUNTER_ACQUIRER = "Khanepani Counter Acquirer";
        String TAX_COUNTER = "Tax Counter";
        String TAX_COUNTER_ACQUIRER = "Tax Counter Acquirer";
        String CLIENT_ACCOUNT = "Client Account";
        String CLIENT_ACCOUNT_HISTORY = "Client Account History";
        String ACQUIRER = "ACQUIRER";
        String IP_VALIDATION = "Ip Validation";
        String CLIENT_ACCESS_IP = "Client Access Ip";
        String CLIENT_AUTHENTICATION = "Client Authentication";
        String ESEWA_SETTLEMENT = "Esewa Settlement";
        String SERVICE_PAYABLE_LIMIT = "Service Payable Limit";
    }

}
