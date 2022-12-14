package com.sms.bli.controller;

import com.sms.bli.dto.ServerResponse;
import com.sms.bli.manager.StudentService;
import com.sms.bli.request.StudentRequest;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Bikash Shah
 */

@RestController
@RequestMapping("student")
public class StudentController {

    @Autowired
    private StudentService service;


    @PostMapping(value="create",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createStudent(@RequestBody @NotNull StudentRequest studentRequest){
        ServerResponse serverResponse = service.createStudent(studentRequest);
        return new ResponseEntity<>(serverResponse, HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllStudent(){
        ServerResponse serverResponse = service.getAllStudent();
        return new ResponseEntity<>(serverResponse,HttpStatus.OK);
    }
}
