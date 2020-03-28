package com.nix.eugenia.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nix.eugenia.exceptions.ClientRequestException;
import com.nix.eugenia.structures.LessonPeriod;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "schedule")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "start_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;

    @Column(name = "finish_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date finishTime;

    @ManyToMany(mappedBy = "schedules")
    @JsonIgnoreProperties("schedules")
    private List<Teacher> teachers = new ArrayList<>();


    public Schedule(LessonPeriod lessonPeriod) {
        if (lessonPeriod.getStartLesson().after(lessonPeriod.getEndLesson()) || lessonPeriod.getStartLesson().compareTo(lessonPeriod.getEndLesson()) == 0 ){
            throw new ClientRequestException("incorrect schedule data");
        }
        this.startTime = lessonPeriod.getStartLesson();
        this.finishTime = lessonPeriod.getEndLesson();
    }


    public Schedule(Schedule schedule) {
        if (schedule.getStartTime().after(schedule.getFinishTime()) || schedule.getStartTime().compareTo(schedule.getFinishTime()) == 0 ){
            throw new ClientRequestException("incorrect schedule data");
        }
        this.id = schedule.getId();
        this.startTime = schedule.getStartTime();
        this.finishTime = schedule.getFinishTime();
        this.teachers = schedule.getTeachers();
    }
}

