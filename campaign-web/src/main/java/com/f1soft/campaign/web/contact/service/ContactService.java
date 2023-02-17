package com.f1soft.campaign.web.contact.service;

import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.web.contact.dto.ContactCreateRequest;
import com.f1soft.campaign.web.contact.dto.ContactModifyRequest;

/**
 * @author Bikash Shah
 */
public interface ContactService {

    ServerResponse createContact(ContactCreateRequest contactCreateRequest);

    ServerResponse contactDetail(Long contactId);

    ServerResponse modifyContact(ContactModifyRequest contactModifyRequest);

    ServerResponse getAllContact();

    ServerResponse deleteContact(Long contactId);

    ServerResponse searchContact(String keyword);


}
