package com.f1soft.campaign.transaction.connector.transaction.topup;

import com.f1soft.campaign.common.config.application.SystemConfig;
import com.f1soft.campaign.common.constant.AppConfigConstant;
import com.f1soft.campaign.common.dto.GenericResponse;
import com.f1soft.campaign.common.dto.RequestWrapper;
import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.transaction.dto.request.CounterMerchantPaymentRequest;
import com.f1soft.campaign.common.pki.PKISecurityTool;
import com.f1soft.campaign.common.util.JacksonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Bikash Shah
 */
@Slf4j
@Component
public class TopupManager {

    @Autowired
    private SystemConfig systemConfig;


    @Autowired
    private TopupRequestContext topupRequestContext;

    public ServerResponse processTopupPayment(CounterMerchantPaymentRequest counterMerchantPaymentRequest) {
        ServerResponse serverResponse = new ServerResponse();
        try {

            RequestWrapper requestWrapper = encryptData(counterMerchantPaymentRequest);

            serverResponse = topupRequestContext.topUp(requestWrapper);
            if(serverResponse.isSuccess()){
                GenericResponse genericResponse = JacksonUtil.get(decryptData((RequestWrapper) serverResponse.getObj()), GenericResponse.class);
                serverResponse.setSuccess(genericResponse.isSuccess());
                serverResponse.setMessage(genericResponse.getMessage());
                serverResponse.setCode(genericResponse.getCode());
                serverResponse.setObj(genericResponse.getData());
            }
        }catch (Exception e){
            log.error("Exception :", e);
            serverResponse.setSuccess(false);
            serverResponse.setCode(e.getMessage());
        }
        return serverResponse;
    }

    private RequestWrapper encryptData(Object data) {
        try {
            String serverSignaturePrivateKey = systemConfig.appConfig(AppConfigConstant.BANKXP_SIGNATURE_PRIVATE_KEY);
            String clientEncryptionPublicKey = systemConfig.appConfig(AppConfigConstant.CAMPAIGN_ENCRYPTION_PUBLIC_KEY);

            RequestWrapper requestWrapper = PKISecurityTool.encryptSigner(clientEncryptionPublicKey, serverSignaturePrivateKey, JacksonUtil.getString(data));
            requestWrapper.setClientKey("CAMPAIGN");
            return requestWrapper;
        } catch (Exception e) {
            log.error("Exception: ", e);
        }
        return null;
    }

    private String decryptData(RequestWrapper requestWrapper) {
        try {
            String serverEncryptionPrivateKey = systemConfig.appConfig(AppConfigConstant.BANKXP_ENCRYPTION_PRIVATE_KEY);
            String clientSignaturePublicKey = systemConfig.appConfig(AppConfigConstant.CAMPAIGN_SIGNATURE_PUBLIC_KEY);

            String decryptedData = PKISecurityTool.decryptVerifier(serverEncryptionPrivateKey,clientSignaturePublicKey,requestWrapper);
            return decryptedData;

        } catch (Exception e) {
            log.error("Exception: ", e);
        }
        return null;
    }
}
