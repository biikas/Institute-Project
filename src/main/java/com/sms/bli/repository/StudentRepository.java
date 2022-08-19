package com.sms.bli.repository;

import com.sms.bli.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Bikash Shah
 */
public interface StudentRepository extends JpaRepository<Student,Long> {
}
