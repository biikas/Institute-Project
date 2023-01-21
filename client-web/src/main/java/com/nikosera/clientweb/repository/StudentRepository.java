package com.nikosera.clientweb.repository;

import com.nikosera.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Bikash Shah
 */
public interface StudentRepository extends JpaRepository<Student,Long> {
}
