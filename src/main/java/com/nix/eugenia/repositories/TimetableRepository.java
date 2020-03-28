package com.nix.eugenia.repositories;

import com.nix.eugenia.model.Teacher;
import com.nix.eugenia.model.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;


@Repository
public interface TimetableRepository extends JpaRepository<Timetable, Long> {

    @Query("SELECT t FROM Timetable t WHERE t.startLesson = :startTime and t.endLesson = :endTime ORDER BY t.startLesson, t.endLesson")
    Timetable findTimetableByPeriod(Timestamp startTime, Timestamp endTime);




}