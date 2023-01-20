package com.nikosera.common.constant;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
public interface ValidationConstant {
    String ALPHABET = "^[A-Za-z]*$";
    String ALPHABET_SPACE = "^[A-Za-z\\s]*$";
    String NUMERIC = "^[0-9]+$";
    String NUMERIC_CONVENTION = "[0-9-()\\[\\]]";
    String ALPHANUMERIC_SPACE = "^[A-Za-z0-9\\s]*$";
    String ALPHANUMERIC = "^[A-Za-z0-9]*$";
    String ALPHANUMERIC_COMMA_SPACE = "^[A-Za-z0-9,\\s]*$";
    String ALPHANUMERIC_COMMA_DASH_SPACE = "^[A-Za-z0-9,\\s-]*$";
    String ALPHANUMERIC_UNDERSCORE_DASH = "^[A-Za-z0-9_-]*$";
    String ALPHANUMERIC_COMMA_SPACE_UNDERSCORE_DASH = "^[A-Za-z0-9,\\s_-]*$";
    String LEADING_TRAILING_WHITESPACE = "^\\s+|\\s+$";
    String ONLY_AUTO_OR_MANUAL = "Auto|Manual";
    String XSS_SCRIPT = "^(?:(?!<script.*?>([\\s\\S]*?)<\\/script>).)+$";
    String PASSWORD = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$";

    String CONTACT_NO_PATTERN = "^(([9][8][0-9]{8})|([9][7][0-9]{8})|([9][6][0-9]{8})|([0-9]{6,10}))$";
    String MOBILE_PATTERN = "^(([9][8][0-9]{8})|([9][7][0-9]{8})|([9][6][0-9]{8}))$";
    String LANDLINE_PATTERN = "^([0-9]{6,10})$";

    String INTEGER_PATTERN = "^(-)?\\d+$";
    String POSITIVE_INTEGER_PATTER = "^\\d+$";
    String NEGATIVE_INTEGER_PATTERN = "^(-)\\d+$";

    String IP_ADDRESS_PATTERN ="^(([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])(\\.(?!$)|$)){4}$";

}
