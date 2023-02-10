package com.f1soft.campaign.transaction.connector;

/*
 * @Author Rashim Dhaubanjar
 */

public class BankConnectPath {

    private BankConnectPath() {
    }

    public interface Transaction {
        String FUND_TRANSFER = "fund/transfer";
        String FUND_REVERSAL = "fund/reversal";
    }

    public interface Topup {
        String TOP_UP = "payments/counterMerchantPayment";
    }

    public static String build(String bankConnectUrl, String endPath) {
        return bankConnectUrl.concat(endPath);
    }
}
