package com.college.campaign.web.contact.service;

import com.college.campaign.common.dto.ServerResponse;
import com.college.campaign.web.contact.dto.ContactCreateRequest;
import com.college.campaign.web.contact.dto.ContactModifyRequest;

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
