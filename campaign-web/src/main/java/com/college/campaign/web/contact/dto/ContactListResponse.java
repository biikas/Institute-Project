package com.college.campaign.web.contact.dto;

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
