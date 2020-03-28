package com.nix.eugenia.controllers;

import com.nix.eugenia.DTO.UpdateEntity;
import com.nix.eugenia.model.Student;
import com.nix.eugenia.model.Teacher;
import com.nix.eugenia.repositories.TeacherRepository;
import com.nix.eugenia.services.AdministratorService;
import com.nix.eugenia.services.TeacherService;
import com.nix.eugenia.services.TeacherServiceImpl;
import com.nix.eugenia.structures.LessonPeriod;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;
    private final AdministratorService administratorService;

    @GetMapping("/{id}")
    public Teacher getTeacherById(@PathVariable Long id) {
        return teacherService.getTeacherById(id);
    }

    @GetMapping
    public List<Teacher> getAllTeachersOfList() {
        return teacherService.getAllTeachers();
    }

    @GetMapping(params = "startTime")
    public List<Teacher> getTeacherBySchedule(
            @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") @RequestParam(name = "startTime") Date startTime
    ) {
        return teacherService.getTeacherBySchedule(startTime);
    }

    @GetMapping(params = "name")
    public List<Teacher> getTeacherByName(@RequestParam(name = "name") String name) {
        return teacherService.getTeacherByName(name);
    }

    @GetMapping("/students/{id}")
    public List<Student> getTeacherByName(@PathVariable(name = "id") Long teacherId) {
        return teacherService.getStudentsByTeacherId(teacherId);
    }

    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
    public void addTeacher(@RequestBody Teacher teacher) {
        administratorService.addTeacher(teacher);
    }


    @DeleteMapping(path = "/delete")
    public void deleteTeacher(@Valid @RequestBody UpdateEntity updateEntity) {
        administratorService.deleteTeacher(updateEntity.getTeacher().getId());
    }

    @PostMapping(path = "schedule/add/to/teacher/{id}")
    public void addSchedule(@PathVariable Long id, @RequestBody  List<LessonPeriod> lessonTimes) {
        teacherService.addSchedule(id, lessonTimes);
    }
}

