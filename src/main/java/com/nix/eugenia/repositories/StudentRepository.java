package com.nix.eugenia.repositories;

import com.nix.eugenia.model.Schedule;
import com.nix.eugenia.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT t FROM Student t JOIN t.timetables s WHERE s.startLesson = :startTime and s.endLesson = :endTime ORDER BY s.startLesson, s.endLesson")
    List<Student> findStudentsByLessonTime(Timestamp startTime, Timestamp endTime);

    @Query("SELECT t FROM Student t JOIN t.timetables s WHERE (s.startLesson = :startTime and s.endLesson = :startTime) or (s.startLesson between :startTime and :endTime) or (s.endLesson between :startTime and :endTime) or (:startTime between s.startLesson and s.endLesson) ORDER BY s.startLesson, s.endLesson")
    List<Student> findStudentByTouchingPeriod(Timestamp startTime, Timestamp endTime);




}
