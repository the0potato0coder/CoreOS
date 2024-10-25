package com.gym.repository;

import com.gym.model.TrainerAttendance;
import com.gym.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TrainerAttendanceRepository extends JpaRepository<TrainerAttendance, Long> {
    List<TrainerAttendance> findByTrainerAndClockInBetween(User trainer, LocalDateTime start, LocalDateTime end);
}
