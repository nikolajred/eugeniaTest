
package com.nix.eugenia.services;

import com.nix.eugenia.exceptions.TeacherNotFoundException;
import com.nix.eugenia.model.Schedule;
import com.nix.eugenia.model.Student;
import com.nix.eugenia.model.Teacher;
import com.nix.eugenia.repositories.ScheduleRepository;
import com.nix.eugenia.repositories.TeacherRepository;
import com.nix.eugenia.structures.LessonPeriod;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final ScheduleRepository scheduleRepository;


    @Override
    public Teacher getTeacherById(Long id) {
        return teacherRepository.findById(id)
                .orElseThrow(() -> new TeacherNotFoundException(id));
    }

    @Override
    public List<Teacher> getTeacherBySchedule(Date startTime) {
        return teacherRepository.findTeachersByStartTime(Timestamp.from(startTime.toInstant()));
    }


    @Override
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    @Override
    public List<Teacher> getTeacherByName(String name) {
        return teacherRepository.findAllByName(name);
    }

    @Override
    public List<Student> getStudentsByTeacherId(Long teacherId) {
        return teacherRepository.findById(teacherId).get().getStudents();
    }

    @Override
    public void addSchedule(Long id, List<LessonPeriod> lessonTimeList) {

        Teacher newTeacher = teacherRepository.findById(id).get();

        List<Schedule> updatedSchedule = new ArrayList<>();

        updatedSchedule.addAll( newTeacher.getSchedules());
        //updatedSchedule.addAll( teacher.getSchedules());
        newTeacher.setSchedules(updatedSchedule);

        teacherRepository.save(newTeacher);

    }
}
