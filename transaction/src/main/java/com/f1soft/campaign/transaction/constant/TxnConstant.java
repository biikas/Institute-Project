package com.f1soft.campaign.transaction.constant;

/**
 * @author user
 */
public class TxnConstant {
    //Fund Transfer

    public static final Integer FUND_TO_TRANSFER = 0;
    public static final Integer FUND_TRANSFER_SUCCESS = 1;
    public static final Integer INSUFFICIENT_FUND = 2;
    public static final Integer OBTAIN_BALANCE_FAILURE = 3;
    public static final Integer FUND_TRANSFER_FAILURE = 7;
    public static final Integer REVERSAL_SUCCESS = 8;
    public static final Integer SERVICE_INACTIVE = 9;

    public static final String FP_NO_EXTRA_CHARGE = "NONE";

    public static final String ISO_FIELD02 = "02";
    public static final String ISO_FIELD43 = "43";
    public static final String ISO_FIELD123 = "123";
    public static final String ISO_FIELD124 = "124";
    public static final String ISO_FIELD125 = "125";
    public static final String ISO_FIELD126 = "126";
    public static final String ISO_FIELD127 = "127";
    public static final String ISO_FIELD_NUMERIC_TYPE = "Numeric";
    public static final String ISO_FIELD_STRING_TYPE = "String";
    public static final String FUND_TRANSFER_FAILURE_ERROR_CODE = "11";
    //Txn Type
    public static final Character TRANSACTION_TYPE = 'T';
    public static final Character REVERSAL_TYPE = 'R';
    public static final String TXN_HISTORY_FIRST_LOOP = "FIRST";
    public static final Character PERCENTAGE_COMMISSION_TYPE = 'P';
    public static final Character FLAT_COMMISSION_TYPE = 'F';
    public static final Character SLAB_COMMISSION_TYPE = 'S';
    public static final Character NO_COMMISSION = 'N';

    //commission
    public static final String COMMISSION_TYPE = "CM";
    public static final String CHARGE_TYPE = "CH";
    public static final String DISCOUNT_TYPE = "D";
    //Credit Account Type
    public static final String SUPER_ACCOUNT_TYPE = "CSA";
    public static final String FEE_ACCOUNT_TYPE = "CFA";
    public static final String COMMISSION_ACCOUNT_TYPE = "COA";
    public static final String MCASH = "Mobile Payment";
    public static final String FONEPAY_TRANSFER = "Fonepay Transfer";
    public static final String GPRS_PAYMENT = "GPRS Payment";
    //Recharge Card
    public static final Integer CARD_REQUEST_TO_PROCESS = 0;
    public static final Integer CARD_REQUEST_PROCESSING = 2;
    public static final Integer CARD_REQUEST_PROCESSED = 3;
    public static final Integer CARD_REQUEST_IGNORED = 4;
    public static final Integer CARD_TO_PROCESS = 0;
    public static final Integer RECHARGE_CARD_SUCCESS = 2;
    public static final Integer RECHARGE_CARD_REVERSED = 3;
    public static final Integer RECHARGE_CARD_REVERSAL_FAILURE = 4;
    public static final Integer RECHARGE_CARD_NOTIFIED = 5;
    public static final Integer RECHARGE_CARD_NOT_CONFIRMED = 7;
    public static final Integer NOT_TO_NOTIFY = 6;
    public static final String RECHARGE_CARD = "Recharge";

    public static final String DELIMITER = "$";

    //Card server Connection Status
    public static final Integer CARD_GOT = 0;
    public static final Integer CARD_NOT_GOT = 1;
    public static final Integer CARD_SERVER_CONNECTION_FAILURE = 6;
    //Utility Payment Status;
    public static final Integer UTILITY_TO_PROCESS = 0;
    public static final Integer TRANSFERED_TO_UTILITY_PAYMENT_MODULE = 1;
    public static final Integer BILL_PAID = 2;
    public static final Integer BILL_PAID_FAILURE = 4;
    public static final Integer BILL_PAID_TIMEOUT = 6;
    public static final Integer BILL_PAID_TOPUP_FAILURE = 7;
    public static final String TOPUP_TIMEOUT_ERROR = "Connection Timeout";
    public static final Integer WAITING_ACKNOWLEDGEMENT = 3;
    public static final Integer REVERSAL_DATA_TRANSFERED = 5;
    public static final String SMS_NOTIFIER = "SMS";
    public static final String EMAIL_NOTIFIER = "EMAIL";
    public static final String URL_NOTIFIER = "URL";
    public static final String MERCHANT_NOTIFICATION_MESSAGE = "MERCHANT_NOTIFICATION";
    public static final String ACCOUNT_OPENING_RECOMMENDATION_REQUEST_MESSAGE = "ACCOUNT_OPENING_RECOMMENDATION_REQUEST";
    public static final String CONFIRM_ACCOUNT_OPENING_REQUEST_MESSAGE = "CONFIRM_ACCOUNT_OPENING_REQUEST";
    public static final Long ACCOUNT_OPENING_CONFIRMATION_STATIC_ID = new Long(27);
    public static final String NEW_LINE = "\n";
    public static final Integer LOW_PRIORITY = 2;
    public static final Integer BULK_PRIORITY = 4;
    public static final Integer HIGH_PRIORITY = 1;
    public static final String INTERNAL_SCHEDULE_PAYMENT_SUCCESS = "INTERNAL_SCHEDULE_PAYMENT_SUCCESS";
    public static final String INTERNAL_SCHEDULE_PAYMENT_FAILURE = "INTERNAL_SCHEDULE_PAYMENT_FAILURE";
    public static final String INTER_BANK_SCHEDULE_PAYMENT_SUCCESS = "INTER_BANK_SCHEDULE_PAYMENT_SUCCESS";
    public static final String INTER_BANK_SCHEDULE_PAYMENT_FAILURE = "INTER_BANK_SCHEDULE_PAYMENT_FAILURE";
    public static final String MERCHANT_SCHEDULE_PAYMENT_SUCCESS = "MERCHANT_SCHEDULE_PAYMENT_SUCCESS";
    public static final String MERCHANT_SCHEDULE_PAYMENT_FAILURE = "MERCHANT_SCHEDULE_PAYMENT_FAILURE";
    public static final String INTER_BANK_FUND_TRANSFER_CODE = "FPIBT";
    /**
     * CHARGE TXN TYPE
     */
    public static final String FUND_TRANSFER_PAYMENT_TYPE = "FT";
    public static final String MERCHANT_PAYMENT_TYPE = "MP";
    public static final String SERVICE_CHARGE_TYPE = "SC";
    public static final String RECHARGE_CARD_PAYMENT_TYPE = "RC";
    public static final String SERVICE_PAYMENT_TYPE = "SP";
    public static final String FONEPAY_PAYMENT_TYPE = "FP";
    public static final String CHARGING_MODE_ENABLED = "CME";
    public static final String CHARGING_MODE_DISABLED = "CMD";
    //
    public static final String FONEPAY_IBT_KEY = "FPIBT";
    public static final Character SERVICE_FLAG_ACTIVE = 'E';
    public static final Character SERVICE_FLAG_INACTIVE = 'D';
    public static final Character SERVICE_FLAG_IN_APPROVAL = 'A';

    //Exception
    public static final Integer EXCEPTION = 9;
    //Customer Transaction Limit
    public static final Integer VALID_TRANSACTION = 0;
    public static final Integer TRANSACTION_LIMIT_EXCEEDED = 1;
    public static final Integer TRANSACTION_AMOUNT_EXCEEDED = 2;
    public static final Integer PER_TRANSACTION_LIMIT_EXCEEDED = 3;
    //bank account type
    public static final Integer DEFAULT_ACCOUNT_TYPE = 0;
    public static final Integer SPECIFIED_ACCOUNT_TYPE = 1;
    //no of chequebook initial param
    public static final String NO_OF_CHEQUE_INITIAL = "NOC";
    //
    public static final String INVALID_BRANCH_CODE = "InvalidBranchCode";
    //Customer Default Bank Account
    public static final String DEFAULT_ACCOUNT = "Y";
    public static final Integer ACCOUNT_ALIAS_LENGTH = 5;
    public static final String CHARGE_MODE = "CHARGE_MODE";
    public static final String MODULE_WISE_CHARGE = "MODULE";
    public static final String ACCOUNT_WISE_CHARGE = "ACCOUNT";
    public static final String CHANNEL = "SMS Core";

    /**
     * Fonepay status
     */
    public static final String FONEPAY_TXN_IN_PENDING_STATE = "PENDING";
    public static final String FONEPAY_TXN_IN_COMPLETION_STATE = "COMPLETE";
    /**
     * Merchant types
     */
    public static final String MERCHANT_TYPE = "M";
    public static final String FONEPAY_TYPE = "F";
    public static final String RECHARGE_CARD_TYPE = "R";
    /**
     * Email templates
     */
    public static final String MERCHANT_PAY_NOTIFICATION_EMAIL_TEMPLATE = "merchantPaymentNotification";
    public static final String MERCHANT_PAY_NOTIFICATION_EMAIL_SUBJECT = "Payment Received";
    public static final String CHEQUE_REQUEST_TEMPELATE_NAME = "chequebookRequest";
    public static final String CHEQUE_REQUEST_SUBJECT = "Cheque Book Request !";
    public static final String CHEQUE_REQUEST_LINK_ACTION = "chequeRequest";
    public static final String STATEMENT_REQUEST_LINK_ACTION = "statementRequest";
    public static final String STATEMENT_REQUEST_TEMPLATE_NAME = "statementRequest";
    public static final String STATEMENT_REQUEST_SUBJECT = "Statement Request !";
    public static final String RECOMMENDATION_LINK_ACTION = "viewRecommendation";
    public static final String RECOMMENDATION_REQUEST_TEMPLATE_NAME = "recommendationRequest";
    public static final String RECOMMENDATION_REQUEST_SUBJECT = "Recommendation Request";
    public static final String SMS_FOOTER = "SMS_FOOTER";

    /**
     * Billing state
     */
    public static final Character BILLING_INITIAL = 'I';
    public static final Character BILLING_STARTED = 'S';
    public static final Character BILLING_COMPLETED = 'C';
    public static final String TXN_REQUEST_TYPE_NORMAL = "NORMAL";
    public static final String TXN_REQUEST_TYPE_SCHEDULE = "SCHEDULE";
    public static final String MODULE_CHARGE_GROUP = "MC";
    public static final String UTILITY_CHARGE_GROUP = "UC";
    public static final String DEPOSIT_TXN_TYPE = "CR";
    public static final String WITHDRAWL_TXN_TYPE = "DR";
    public static final String ACCOUNT_OPENING_RECOMMENDATION = "Account Opening Recommendation";
    public static final String COMMISSION_PAYEE_CHARGE_TEXT = "Charge";
    public static final String COMMISSION_PAYEE_DISCOUNT_TEXT = "Discount";
    public static final String COMMISSION_PAYEE_COMMISSION_TEXT = "Commission";
}
