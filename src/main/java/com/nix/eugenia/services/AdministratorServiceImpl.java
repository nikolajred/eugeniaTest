package com.nix.eugenia.services;

import com.nix.eugenia.exceptions.ClientRequestException;
import com.nix.eugenia.exceptions.ResourceNotFoundException;
import com.nix.eugenia.model.Schedule;
import com.nix.eugenia.model.Student;
import com.nix.eugenia.model.Teacher;
import com.nix.eugenia.model.Timetable;
import com.nix.eugenia.repositories.ScheduleRepository;
import com.nix.eugenia.repositories.StudentRepository;
import com.nix.eugenia.repositories.TeacherRepository;
import com.nix.eugenia.repositories.TimetableRepository;
import com.nix.eugenia.structures.LessonPeriod;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AdministratorServiceImpl implements AdministratorService {

    private final StudentRepository studentRepository;
    private final StudentService studentService;
    private final TeacherRepository teacherRepository;
    private final TimetableRepository timetableRepository;
    private final ScheduleRepository scheduleRepository;

    @Override
    public void addTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    @Override
    public void deleteTeacher(Long teacherId) {
        teacherRepository.deleteById(teacherId);
    }





    @Override
    public List<Teacher> updateTeacher() {
        return null;
    }

    @Override
    public void setStudentTimetable(Long studentId, List<LessonPeriod> lessonPeriods) {
        if (lessonPeriods.size() == 0) throw new ClientRequestException("timetable can`t be empty");
        Student student = studentRepository.findById(studentId).
                orElseThrow(() -> new ResourceNotFoundException("This student doesn't exist", studentId));
        Teacher teacher = student.getTeacher();
        if (teacher == null) {
            throw new ResourceNotFoundException("Student " + studentId + " doesnt have a teacher");
        }
        List<Schedule> schedules = teacher.getSchedules();
        List<Timetable> timetables = student.getTimetables();
        for (LessonPeriod lessonPeriod : lessonPeriods) {
            if (studentService.findStudentByTouchingPeriod(lessonPeriod).stream()
                    .anyMatch((s) -> s.getTeacher().getId() == teacher.getId())) {
                throw new ClientRequestException(lessonPeriod.toString() + "is not available");
            }
            if (studentService.getStudentsByTimeTable(lessonPeriod).size() == 0) {
                timetables.add(new Timetable(lessonPeriod));
                schedules.add(new Schedule(lessonPeriod));
            } else {
                if (studentService.findStudentByTouchingPeriod(lessonPeriod).stream()
                        .anyMatch((s) -> s.getTeacher().getId() == teacher.getId())) {
                    throw new ClientRequestException(lessonPeriod.toString() + "is not available");
                }
                timetables.add(new Timetable(timetableRepository.findTimetableByPeriod(
                        Timestamp.from(lessonPeriod.getStartLesson().toInstant()),
                        Timestamp.from(lessonPeriod.getEndLesson().toInstant()))));
                schedules.add(new Schedule(scheduleRepository.findScheduleByPeriod(
                        Timestamp.from(lessonPeriod.getStartLesson().toInstant()),
                        Timestamp.from(lessonPeriod.getEndLesson().toInstant()))));
            }

        }
        student.setTimetables(timetables);
        teacher.setSchedules(schedules);
        studentRepository.save(student);
        teacherRepository.save(teacher);
    }

//    @Override
//    public void changeStudentTimetable(Long studentId, LessonPeriod lessonPeriod, LessonPeriod newLessonPeriod) {
//        Student student = studentRepository.findById(studentId).
//                orElseThrow(() -> new ResourceNotFoundException("This student doesn't exist", studentId));
//        Teacher teacher = student.getTeacher();
//        if (teacher == null) {
//            throw new ResourceNotFoundException("Student " + studentId + " doesnt have a teacher");
//        }
//        List<Timetable> timetables = student.getTimetables();
//        List<Timetable> tmpTimetable = timetables.stream().filter((s) -> s.getStartLesson().compareTo(lessonPeriod.getStartLesson()) == 0 && s.getEndLesson().compareTo(lessonPeriod.getEndLesson()) == 0).collect(Collectors.toList());
//        if (tmpTimetable.size() == 0) throw new ClientRequestException("This timetable doesn't exist:" + lessonPeriod.toString());
//                timetables.remove(tmpTimetable.get(1));
//        student.setTimetables(timetables);
//        studentRepository.save(student);
//        try{
//
//            if (studentService.findStudentByTouchingPeriod(newLessonPeriod).stream()
//                    .anyMatch((s) -> s.getTeacher().getId() == teacher.getId())) {
//                throw new RuntimeException();
//            }
//        }catch (RuntimeException e){
//            timetables.add(new Timetable(timetableRepository.findTimetableByPeriod(
//                    Timestamp.from(lessonPeriod.getStartLesson().toInstant()),
//                    Timestamp.from(lessonPeriod.getEndLesson().toInstant()))));
//            student.setTimetables(timetables);
//            studentRepository.save(student);
//            e.addSuppressed(new ClientRequestException(lessonPeriod.toString() + "is not available"));
//        }
//
//        timetables.add(new Timetable(timetableRepository.findTimetableByPeriod(
//                Timestamp.from(lessonPeriod.getStartLesson().toInstant()),
//                Timestamp.from(lessonPeriod.getEndLesson().toInstant()))));
//
//        List<Schedule> schedules = teacher.getSchedules();
//        Schedule schedule = schedules.stream().filter((s) -> s.getStartTime().compareTo(lessonPeriod.getStartLesson()) == 0 && s.getFinishTime().compareTo(lessonPeriod.getEndLesson()) == 0).findFirst().get();
//        schedules.remove(schedule);
//        schedules.add(new Schedule(scheduleRepository.findScheduleByPeriod(
//                Timestamp.from(lessonPeriod.getStartLesson().toInstant()),
//                Timestamp.from(lessonPeriod.getEndLesson().toInstant()))));
//
//        student.setTimetables(timetables);
//        teacher.setSchedules(schedules);
//        studentRepository.save(student);
//        teacherRepository.save(teacher);
//    }


    @Override
    public void addTeacherToStudent(Long studentId, Long teacherId) {
        Student student = studentRepository.findById(studentId).
        orElseThrow(() -> new ResourceNotFoundException("This student doesn't exist", studentId));
        student.setTeacher(teacherRepository.findById(teacherId).
                orElseThrow(() -> new ResourceNotFoundException("This teacher doesn't exist", teacherId)));
        studentRepository.save(student);
    }

    @Override
    public void changeCurrentTeacher(Long studentId, Long teacherId) {
        Student student = studentRepository.findById(studentId).
                orElseThrow(() -> new ResourceNotFoundException("This student doesn't exist", studentId));
        Teacher teacher = teacherRepository.findById(teacherId).
                orElseThrow(() -> new ResourceNotFoundException("This teacher doesn't exist", teacherId));
        student.setTeacher(teacher);
        teacher.getStudents().add(student);
        teacherRepository.save(teacher);
    }



    @Override
    public void addStudent(Student student) {
        studentRepository.save(student);
    }
    @Override
    public void deleteStudent(Long studentID) {
        studentRepository.deleteById(studentID);
    }

    @Override
    public void updateStudentLessons(Long studentId, Long lessonsLeft) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("This student doesn't exist", studentId));

        student.setLessonsLeft((Long) student.getLessonsLeft() + lessonsLeft);

        studentRepository.save(student);
    }



}

