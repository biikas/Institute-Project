package com.f1soft.campaign.client.mapper;

import com.f1soft.campaign.client.dto.TransactionRequesterData;
import com.f1soft.campaign.client.dto.UserInfoDTO;

/**
 * @Author Nitesh Poudel
 */
public class UserInfoMapper {

    public static UserInfoDTO convertToUserInfoDTO(TransactionRequesterData transactionRequesterData) {
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setEmailAddress(transactionRequesterData.getEmailAddress());
//        userInfoDTO.setName(transactionRequesterData.getName());

        return userInfoDTO;
    }
}
