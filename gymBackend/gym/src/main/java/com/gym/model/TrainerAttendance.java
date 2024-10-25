package com.gym.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "trainer_attendance")
public class TrainerAttendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "trainer_id", nullable = false) // Ensure trainer cannot be null
    private User trainer;

    private LocalDateTime clockIn;
    private LocalDateTime clockOut;

    // Method to determine if the trainer is present
    public boolean isPresent() {
        return clockIn != null && clockOut == null; // Present if clocked in and not clocked out
    }

    // Constructors
    public TrainerAttendance() {
    }

    public TrainerAttendance(User trainer, LocalDateTime clockIn) {
        this.trainer = trainer;
        this.clockIn = clockIn;
    }

    // Optional: Add a method to clock out
    public void clockOut(LocalDateTime clockOutTime) {
        this.clockOut = clockOutTime;
    }

    // Optional: Override toString for debugging purposes
    @Override
    public String toString() {
        return "TrainerAttendance{" +
                "id=" + id +
                ", trainer=" + trainer.getFullName() + // Assuming getFullName() exists
                ", clockIn=" + clockIn +
                ", clockOut=" + clockOut +
                ", present=" + isPresent() +
                '}';
    }
}
