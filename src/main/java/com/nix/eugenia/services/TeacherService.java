package com.nix.eugenia.services;

import com.nix.eugenia.model.Student;
import com.nix.eugenia.model.Teacher;
import com.nix.eugenia.structures.LessonPeriod;


import java.util.Date;
import java.util.List;

public interface TeacherService {
    Teacher getTeacherById(Long id);

    List<Teacher> getTeacherBySchedule(Date startTime);

//    List<Teacher>getTeacherByPeriod(LessonPeriod LessonTime);

    List<Teacher> getAllTeachers();

    List<Teacher> getTeacherByName(String name);

    List<Student> getStudentsByTeacherId(Long teacherId);


    void addSchedule(Long id, List<LessonPeriod> lessonTimeList);
}
