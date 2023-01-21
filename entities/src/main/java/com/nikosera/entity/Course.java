package com.nikosera.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Bikash Shah
 */
@Getter
@Setter
@Entity
@Table(name="COURSE")
public class Course {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;
    @Column(name="COURSE_NAME")
    private String courseName;
    @Column(name="ACTIVE")
    private char active;
    //
    /*
    no of students enrolled in the course can be aadded.
    no of teachers available for the course can be added.
     */
}
