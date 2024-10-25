package com.gym.service;

import com.gym.model.TrainerAttendance;
import com.gym.model.User;
import com.gym.repository.TrainerAttendanceRepository;
import com.gym.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Transactional
public class TrainerAttendanceService {
    @Autowired
    private TrainerAttendanceRepository attendanceRepository;

    @Autowired
    private UserRepository userRepository;

    public TrainerAttendance clockIn(Long trainerId) {
        User trainer = userRepository.findById(trainerId)
                .orElseThrow(() -> new RuntimeException("Trainer not found"));
        TrainerAttendance attendance = new TrainerAttendance();
        attendance.setTrainer(trainer);
        attendance.setClockIn(LocalDateTime.now());
        return attendanceRepository.save(attendance);
    }

    public TrainerAttendance clockOut(Long attendanceId) {
        TrainerAttendance attendance = attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> new RuntimeException("Attendance record not found"));
        attendance.setClockOut(LocalDateTime.now());
        return attendanceRepository.save(attendance);
    }
}