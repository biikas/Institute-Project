package com.f1soft.campaign.web.contact.dto;

import com.f1soft.campaign.entities.model.Contact;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Bikash Shah
 */
@Getter
@Setter
public class ContactListResponse {

    private List<ContactResponseDto> contactList;
}
