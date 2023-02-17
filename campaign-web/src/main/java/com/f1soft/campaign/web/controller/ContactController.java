package com.f1soft.campaign.web.controller;

import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.common.log.SkipAPILogging;
import com.f1soft.campaign.common.util.ResponseBuilder;
import com.f1soft.campaign.web.contact.dto.ContactCreateRequest;
import com.f1soft.campaign.web.contact.dto.ContactModifyRequest;
import com.f1soft.campaign.web.contact.dto.SearchContactRequest;
import com.f1soft.campaign.web.contact.service.ContactService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author Bikash Shah
 */
@Slf4j
@RestController
@RequestMapping("contact")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping(value = "create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createContact(@NotNull @Valid @RequestBody ContactCreateRequest contactCreateRequest) {
        ServerResponse serverResponse = contactService.createContact(contactCreateRequest);
        return ResponseBuilder.response(serverResponse);
    }

    @SkipAPILogging
    @GetMapping(value = "{contactId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> contactDetail(@PathVariable("contactId") Long contactId) {
        ServerResponse serverResponse = contactService.contactDetail(contactId);
        return ResponseBuilder.response(serverResponse);
    }

    @PostMapping(value = "modify", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> modifyContact(@NotNull @Valid @RequestBody ContactModifyRequest contactModifyRequest) {
        ServerResponse serverResponse = contactService.modifyContact(contactModifyRequest);
        return ResponseBuilder.response(serverResponse);
    }

    @SkipAPILogging
    @GetMapping(value = "list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getContactList() {
        ServerResponse serverResponse = contactService.getAllContact();
        return ResponseBuilder.response(serverResponse);
    }

    @GetMapping(value = "delete/{contactId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteContact(@PathVariable("contactId")Long id) {
        ServerResponse serverResponse = contactService.deleteContact(id);
        return ResponseBuilder.response(serverResponse);
    }

    @PostMapping(value = "search",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> searchContact(@RequestBody SearchContactRequest searchContactRequest) {
        ServerResponse serverResponse = contactService.searchContact(searchContactRequest.getKeyword());
        return ResponseBuilder.response(serverResponse);
    }

}
