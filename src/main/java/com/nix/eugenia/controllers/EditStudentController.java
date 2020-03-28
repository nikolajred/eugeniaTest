package com.nix.eugenia.controllers;

import com.nix.eugenia.DTO.UpdateEntity;
import com.nix.eugenia.exceptions.ResourceNotFoundException;
import com.nix.eugenia.services.AdministratorServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;



@RestController
@RequiredArgsConstructor
public class EditStudentController {

    private final AdministratorServiceImpl administratorServiceImpl;

    @PutMapping(path = "/students/edit/lessons", consumes = "application/json", produces = "application/json")
    public void updateStudentLessons(@Valid @RequestBody UpdateEntity updateEntity ) throws ResourceNotFoundException {
        administratorServiceImpl.updateStudentLessons(updateEntity.getStudent().getId(), updateEntity.getStudent().getLessonsLeft());
    }


    //functional
    @PutMapping(path = "students/edit/changeteacher", consumes = "application/json", produces = "application/json")
    public void changeCurrentTeacher(@Valid @RequestBody UpdateEntity updateEntity ) throws ResourceNotFoundException {
        administratorServiceImpl.changeCurrentTeacher(updateEntity.getStudent().getId(), updateEntity.getTeacher().getId());
    }

}
