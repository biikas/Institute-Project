package com.college.campaign.web.contact.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Bikash Shah
 */
@Getter
@Setter
public class ContactResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String dateOfBirth;
    private String email;
}
