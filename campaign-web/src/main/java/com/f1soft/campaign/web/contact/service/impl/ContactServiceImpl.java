package com.f1soft.campaign.web.contact.service.impl;

import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.common.util.DateFormat;
import com.f1soft.campaign.common.util.DateFormatter;
import com.f1soft.campaign.entities.model.Contact;
import com.f1soft.campaign.repository.ContactRepository;
import com.f1soft.campaign.web.contact.dto.ContactCreateRequest;
import com.f1soft.campaign.web.contact.dto.ContactListResponse;
import com.f1soft.campaign.web.contact.dto.ContactModifyRequest;
import com.f1soft.campaign.web.contact.dto.ContactResponseDto;
import com.f1soft.campaign.web.contact.service.ContactService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Bikash Shah
 */
@Slf4j
@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public ServerResponse createContact(ContactCreateRequest contactCreateRequest) {

        Contact contact = new Contact();

        contact.setFirstName(contactCreateRequest.getFirstName());
        contact.setLastName(contactCreateRequest.getLastName());
        contact.setMobileNumber(contactCreateRequest.getMobileNumber());
        contact.setActive("Y");
        contact.setDateOfBirth(DateFormatter.convertToDate(contactCreateRequest.getDateOfBirth()));
        contact.setEmail(contactCreateRequest.getEmail());

        contactRepository.save(contact);

        ServerResponse serverResponse = new ServerResponse();
        serverResponse.setSuccess(true);
        serverResponse.setCode("0");
        serverResponse.setMessage("Contact Has been created Successfully");

        return serverResponse;
    }

    @Override
    public ServerResponse contactDetail(Long contactId) {

        Optional<Contact> optionalContact = contactRepository.findById(contactId);
        Contact contact = optionalContact.get();

        ContactResponseDto contactResponseDto = new ContactResponseDto();
        contactResponseDto.setFirstName(contact.getFirstName());
        contactResponseDto.setLastName(contact.getLastName());
        contactResponseDto.setMobileNumber(contact.getMobileNumber());
        contactResponseDto.setDateOfBirth(DateFormatter.getDayMonthYear(DateFormatter.changeFormat(contact.getDateOfBirth(), DateFormat.SQL_DATE_FORMAT)));
        contactResponseDto.setEmail(contact.getEmail());

        ServerResponse serverResponse = new ServerResponse();
        serverResponse.setSuccess(true);
        serverResponse.setCode("0");
        serverResponse.setMessage("Contact Detail Fetched Successfully");
        serverResponse.setObj(contactResponseDto);

        return serverResponse;
    }

    @Override
    public ServerResponse modifyContact(ContactModifyRequest contactModifyRequest) {
        Contact contact = contactRepository.findById(contactModifyRequest.getId()).get();

        contact.setMobileNumber(contactModifyRequest.getMobileNumber());
        contact.setFirstName(contactModifyRequest.getFirstName());
        contact.setLastName(contactModifyRequest.getLastName());

        contactRepository.save(contact);

        ServerResponse serverResponse = new ServerResponse();
        serverResponse.setSuccess(true);
        serverResponse.setCode("0");
        serverResponse.setMessage("Contact Has been updated Successfully");
        return serverResponse;
    }

    @Override
    public ServerResponse getAllContact() {
        List<Contact> contacts = contactRepository.findAll().stream()
                .filter(contact -> contact.getActive().equalsIgnoreCase("Y")).collect(Collectors.toList());
        ContactListResponse contactList = new ContactListResponse();
        List<ContactResponseDto> contactsResponses = new ArrayList<>();

        for (Contact contact : contacts) {
            ContactResponseDto contactResponseDto = new ContactResponseDto();
            contactResponseDto.setFirstName(contact.getFirstName());
            contactResponseDto.setLastName(contact.getLastName());
            contactResponseDto.setEmail(contact.getEmail());
            contactResponseDto.setMobileNumber(contact.getMobileNumber());
            contactResponseDto.setId(contact.getId());
            if (contact.getDateOfBirth() != null) {
                contactResponseDto.setDateOfBirth(DateFormatter.getDayMonthYear(DateFormatter.changeFormat(contact.getDateOfBirth(), DateFormat.SQL_DATE_FORMAT)));
            }
            contactsResponses.add(contactResponseDto);
        }
        contactList.setContactList(contactsResponses);

        ServerResponse serverResponse = new ServerResponse();
        serverResponse.setSuccess(true);
        serverResponse.setCode("0");
        serverResponse.setMessage("Contact List fetched successfully");
        serverResponse.setObj(contactList);
        return serverResponse;
    }





    @Override
    public ServerResponse deleteContact(Long contactId) {
        Contact contact = contactRepository.findById(contactId).get();

        contact.setActive("N");
        contactRepository.save(contact);

        ServerResponse serverResponse = new ServerResponse();
        serverResponse.setSuccess(true);
        serverResponse.setMessage("Contact Deleted Successfully");
        serverResponse.setCode("0");
        return serverResponse;
    }

    @Override
    public ServerResponse searchContact(String keyword) {
        List<Contact> contacts = contactRepository.searchContact(keyword).stream()
                .filter(contact -> contact.getActive().equalsIgnoreCase("Y")).collect(Collectors.toList());

        ContactListResponse contactList = new ContactListResponse();
        List<ContactResponseDto> contactsResponses = new ArrayList<>();

        for (Contact contact : contacts) {
            ContactResponseDto contactResponseDto = new ContactResponseDto();
            contactResponseDto.setFirstName(contact.getFirstName());
            contactResponseDto.setLastName(contact.getLastName());
            contactResponseDto.setEmail(contact.getEmail());
            contactResponseDto.setMobileNumber(contact.getMobileNumber());
            contactResponseDto.setId(contact.getId());
            if (contact.getDateOfBirth() != null) {
                contactResponseDto.setDateOfBirth(DateFormatter.getDayMonthYear(DateFormatter.changeFormat(contact.getDateOfBirth(), DateFormat.SQL_DATE_FORMAT)));
            }
            contactsResponses.add(contactResponseDto);
        }
        contactList.setContactList(contactsResponses);

        ServerResponse serverResponse = new ServerResponse();
        serverResponse.setSuccess(true);
        serverResponse.setCode("0");
        serverResponse.setMessage("Contact List fetched successfully");
        serverResponse.setObj(contactList);
        return serverResponse;
    }

}
