package com.nix.eugenia.services;

import com.nix.eugenia.model.Schedule;
import com.nix.eugenia.model.Student;
import com.nix.eugenia.model.Timetable;
import com.nix.eugenia.structures.LessonPeriod;

import java.util.List;

public interface StudentService {

    Student getStudent(Long id);

    List<Student> getAllStudents();

    List<Student> getStudentsByTimeTable(LessonPeriod lessonPeriod);

    List<Student> findStudentByTouchingPeriod(LessonPeriod lessonPeriod);

    List<Timetable> getStudentTimetable(Long id);

}
