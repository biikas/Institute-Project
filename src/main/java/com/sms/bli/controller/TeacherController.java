package com.sms.bli.controller;

import com.sms.bli.dto.ServerResponse;
import com.sms.bli.manager.TeacherService;
import com.sms.bli.request.TeacherRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Bikash Shah
 */

@RestController
@RequestMapping("teacher")
public class TeacherController {

    @Autowired
    private TeacherService service;

    @PostMapping(value = "create",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createTeacherProfile(@RequestBody TeacherRequest teacherRequest){
        ServerResponse serverResponse = service.createTeacherProfile(teacherRequest);
        return new ResponseEntity<>(serverResponse, HttpStatus.OK);
    }

    @GetMapping(value="",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getTeachers(){
        ServerResponse serverResponse = service.getTeachersProfile();
        return new ResponseEntity<>(serverResponse,HttpStatus.OK);
    }
}
