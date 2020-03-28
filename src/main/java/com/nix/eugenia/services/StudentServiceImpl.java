package com.nix.eugenia.services;

import com.nix.eugenia.exceptions.ResourceNotFoundException;
import com.nix.eugenia.model.Student;
import com.nix.eugenia.model.Timetable;
import com.nix.eugenia.repositories.StudentRepository;
import com.nix.eugenia.structures.LessonPeriod;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;


@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public Student getStudent(Long id) {
        return studentRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("I'm sorry but there's no student ", id));
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public List<Student> getStudentsByTimeTable(LessonPeriod lessonPeriod) {
        return studentRepository.findStudentsByLessonTime(Timestamp.from(lessonPeriod.getStartLesson().toInstant()), Timestamp.from(lessonPeriod.getEndLesson().toInstant()));
    }

    @Override
    public List<Student> findStudentByTouchingPeriod(LessonPeriod lessonPeriod) {
        return studentRepository.findStudentByTouchingPeriod(Timestamp.from(lessonPeriod.getStartLesson().toInstant()), Timestamp.from(lessonPeriod.getEndLesson().toInstant()));
    }

    @Override
    public List<Timetable> getStudentTimetable(Long id) {
        Student student = this.getStudent(id);
        return student.getTimetables();
    }

}