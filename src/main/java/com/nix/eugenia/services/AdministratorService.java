package com.nix.eugenia.services;

import com.nix.eugenia.model.Student;
import com.nix.eugenia.model.Teacher;
import com.nix.eugenia.structures.LessonPeriod;

import java.util.List;

public interface AdministratorService {

    void addTeacher(Teacher teacher);

   void deleteTeacher(Long teacherId);

    List<Teacher> updateTeacher();

    void setStudentTimetable(Long studentId, List<LessonPeriod> lessonTimeList);

    void addTeacherToStudent(Long studentId, Long teacherId);

    void addStudent(Student student);

    void deleteStudent(Long studentID);

    void updateStudentLessons(Long studentId, Long lessonsLeft);

    void changeCurrentTeacher(Long studentId, Long teacherId);

//    void changeStudentTimetable(Long studentId, LessonPeriod lessonPeriod, LessonPeriod newLessonPeriod);

//    void deleteStudentTimetables(Long studentId);


}
