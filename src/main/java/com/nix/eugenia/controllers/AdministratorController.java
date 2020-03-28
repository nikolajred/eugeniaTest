package com.nix.eugenia.controllers;

import com.nix.eugenia.DTO.UpdateEntity;
import com.nix.eugenia.model.Student;
import com.nix.eugenia.services.AdministratorService;
import com.nix.eugenia.services.AdministratorServiceImpl;
import com.nix.eugenia.structures.LessonPeriod;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PostUpdate;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class AdministratorController {

    private final AdministratorServiceImpl administratorServiceImpl;
    private final AdministratorService administratorService;


    @PostMapping(path = "/students/add", consumes = "application/json", produces = "application/json")
    public void addStudent(@RequestBody Student student) {
        administratorServiceImpl.addStudent(student);
    }

    @DeleteMapping(path = "/students/delete")
    public void deleteStudent(@Valid @RequestBody UpdateEntity updateEntity) {
        administratorServiceImpl.deleteStudent(updateEntity.getStudent().getId());
    }

    @PostMapping(path = "/students/{id}/add/timetable", consumes = "application/json", produces = "application/json")
    public void addStudent(@PathVariable Long id, @RequestBody List<LessonPeriod> lessonTimes) {
        administratorService.setStudentTimetable(id, lessonTimes);
    }
    @PostMapping(path = "teachers/{teacherId}/add/to/students/{studentId}")
    public void addTeacherToStudentById(@PathVariable Long studentId, @PathVariable Long teacherId){
            administratorService.addTeacherToStudent(studentId, teacherId);
    }



//[{"startLesson":"2020-10-22'T'10:00", "endLesson":"2020-10-22'T'11:00"}, {"startLesson":"2020-10-25'T'10:00", "endLesson":"2020-10-25'T'11:00"}]
}

