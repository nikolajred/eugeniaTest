package com.nix.eugenia.repositories;

import com.nix.eugenia.model.Schedule;
import com.nix.eugenia.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    @Query("SELECT t FROM Teacher t JOIN t.schedules s WHERE s.startTime >= :startTime ORDER BY s.startTime")
    List<Teacher> findTeachersByStartTime(Timestamp startTime);

    @Query("SELECT t FROM Teacher t JOIN t.schedules s WHERE s.startTime = :startTime and s.finishTime = :finishTime ORDER BY s.startTime, s.finishTime")
    List<Teacher> findByPeriod(Timestamp startTime, Timestamp finishTime);



    List<Teacher> findAllByName(String name);

}



