package com.nikosera.clientweb.repository;


import com.nikosera.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Bikash Shah
 */
public interface TeacherRepository extends JpaRepository<Teacher,Long> {
}
