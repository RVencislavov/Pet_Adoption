package com.example.persistence.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "meeting_schedule")
@Getter
@Setter
public class MeetingScheduleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meeting_id")
    private long meetingID;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "animal_id")
    private AnimalsEntity animal;

    @Column(name = "meeting_date")
    private LocalDateTime meetingDate;

}
