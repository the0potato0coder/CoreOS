package com.gym.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TrainerAttendanceReport {  // Use the same name in the controller
    private Long trainerId;        // ID of the trainer
    private String trainerName;    // Name of the trainer
    private LocalDate date;        // Date of the attendance
    private boolean isPresent;     // Whether the trainer was present

    public TrainerAttendanceReport(Long trainerId, String trainerName, LocalDate date, boolean isPresent) {
        this.trainerId = trainerId;
        this.trainerName = trainerName;
        this.date = date;
        this.isPresent = isPresent;
    }

    // Default constructor (for serialization/deserialization)
    public TrainerAttendanceReport() {
    }

    @Override
    public String toString() {
        return "TrainerAttendanceReport{" +
                "trainerId=" + trainerId +
                ", trainerName='" + trainerName + '\'' +
                ", date=" + date +
                ", isPresent=" + isPresent +
                '}';
    }
}
