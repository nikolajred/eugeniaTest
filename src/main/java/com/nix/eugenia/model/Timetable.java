package com.nix.eugenia.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nix.eugenia.exceptions.ClientRequestException;
import com.nix.eugenia.structures.LessonPeriod;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "timetable")
@NoArgsConstructor
@AllArgsConstructor
public class Timetable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;


    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date startLesson;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date endLesson;


    @ManyToMany(mappedBy = "timetables")
    @JsonIgnoreProperties("timetables")
    @JsonBackReference
    private List<Student> students;


    public Timetable(LessonPeriod lessonPeriod) {
        if (lessonPeriod.getStartLesson().after(lessonPeriod.getEndLesson()) || lessonPeriod.getStartLesson().compareTo(lessonPeriod.getEndLesson()) == 0 ){
            throw new ClientRequestException("incorrect timetable data");
        }
        this.startLesson = lessonPeriod.getStartLesson();
        this.endLesson = lessonPeriod.getEndLesson();

    }


    public Timetable(Timetable timetable) {
        if (timetable.getStartLesson().after(timetable.getEndLesson()) || timetable.getStartLesson().compareTo(timetable.getEndLesson()) == 0 ){
            throw new ClientRequestException("incorrect timetable data");
        }
        this.id = timetable.getId();
        this.startLesson = timetable.getStartLesson();
        this.endLesson = timetable.getEndLesson();
        this.students = timetable.getStudents();
    }
}
