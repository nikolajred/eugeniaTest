package com.nix.eugenia.repositories;

import com.nix.eugenia.model.Schedule;
import com.nix.eugenia.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {


    @Query("SELECT t FROM Schedule t  WHERE t.startTime = :startTime and t.finishTime = :finishTime ORDER BY t.startTime, t.finishTime")
    Schedule findScheduleByPeriod(Timestamp startTime, Timestamp finishTime);

//    @Query("SELECT t FROM Schedule t  WHERE (t.startTime = :startTime and t.finishTime = :finishTime) or (startTime between t.startTime and t.finishTime) or (finishTime between t.startTime and t.finishTime) ORDER BY t.startTime, t.finishTime")
//    List<Schedule> findByTouchingPeriod(Timestamp startTime, Timestamp finishTime);
}
