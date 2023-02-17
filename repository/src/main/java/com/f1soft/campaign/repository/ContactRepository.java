package com.f1soft.campaign.repository;

import com.f1soft.campaign.entities.model.Contact;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Bikash Shah
 */
@Repository
public interface ContactRepository extends BaseRepository<Contact> {

    @Query("SELECT p FROM Contact p WHERE CONCAT(p.firstName, p.lastName, p.mobileNumber, p.dateOfBirth,p.email) LIKE %:keyword%")
    List<Contact> searchContact(String keyword);
}
