package com.f1soft.campaign.transaction.connector.transaction.dataPackage;

import com.f1soft.campaign.common.dto.RequestWrapper;
import com.f1soft.campaign.common.dto.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author Bikash Shah
 */
@Slf4j
@Component
@Qualifier("local")
public class LocalDataPackageOperations implements DataPackageOperations{
    @Override
    public ServerResponse dataPackagePayment(RequestWrapper requestWrapper) {
        ServerResponse serverResponse = new ServerResponse();
        requestWrapper.setSignature("QajN3+S4Ncq1tJD5A/8CVhUi/c/riyHyzmwUop/0nOBrN0mq4CXKQHbMnvsUJTSpnjFmThaUKUTs7d2br0g00BcbI0a5VeDSJbEu0V52BROpRKVQBpON2rdZ/VuU9SWoc/h1luJPoQ4nqFliZ0gbltePCtyb1cMSTDsk9VPvYgkWzg1LkFDpo/mqwYo9cA+BslrHW5POLiD8i28FihpLy4dL7VphAetSjIrmLtNKOzQm5Rg8WyGIvG67nCLYnnhiNbCJcXy2jU4bVA3kEbKG0eMafq8CmgOnGNOiYg+IqaExmr8hFac7leqUM5lJYngr2noZC7205A5Zlizvv+BBdw==");
        requestWrapper.setData("FldpOwU7JbDVW2titbgTP2+yp4ryvG9eF36cXEk1u8UN1KsNqq+d1H9zVp9g3Va8MDHxWAp1CKxnVyKHwDfzm9fjkEYIlwWPHeUuYA+OQgmCz31l3+TdDAaquzBs0RYOLuk2HRHZLR66iDry86ULmymUPfi38hqAEn6E5CJI5OrE60LRsXVnToTQqagrSHWiKJSpYladmmk86qhTgMWgHRnBM83RRc0wXwvXHBV7JulBnrUHz42tr8CeQY2FFV9vmiquZrgmzn06I2U7f7Z0Pwq3DBj4A9o/t/nYLgdc8NuFmxzQJsabjGgRbSb/EcpEPrXqqLg6Q1JmycU1MR6PT3aufemHTDP2cB+g5XenCTtt5jbkWQFLscdXayJZSlk4+qNROTYf78i1tcWShs3GHWYwTMxAwcohK21AE1O/iCMakKEtGVEbMv38GKgP0LYNbFiw821HDZ9veAHvoJ0zI5CUiGUIen1SxpmdPVlkaBZjUAQjpeyQFpnX9Pi89pWQ4bcl0JOyc6TH4q1CPyMLN21VufbGkUZzkdviGADuLZg=");
        requestWrapper.setSecretKey("DTFlShR49+q/DmznUlrjz5l/HKXBC4tYJnRVU1K/rD4t5n9uDVSBRCEEa8FqW8/3CREWsO8Xpzz3cghBwpohLc+0yOK/G4FZxmdjNwkiNVR7Sag7Vw6D18wdf3KfKzNrmJ5OtFPNJ1bAVzWPpCiqGXMsQKmbSzSFG4mgtrqQ5J3DhjpF+LqOyHk/oZiLTVf9HaeJO8GF44XqJHh5FEkiaNY0vGz7tzZgswCjH+f10VxZ49+18WwbN52vFyhUJWJ/qRniQb7VSBZrQMCae7WPTvVHjid1q6G8ojFBh0H9XRMRx/Sfvi8tt2kniFdiaLlqxu34YTASdW57JoKax7PhWA==");
        requestWrapper.setClientKey("CAMPAIGN");
        serverResponse.setSuccess(true);
        serverResponse.setObj(requestWrapper);
        return serverResponse;
    }
}
