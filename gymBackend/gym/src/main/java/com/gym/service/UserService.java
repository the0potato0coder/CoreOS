package com.gym.service;

import com.gym.model.TrainerAttendance;
import com.gym.model.TrainerAttendanceReport;
import com.gym.model.User;
import com.gym.model.UserRole;
import com.gym.repository.TrainerAttendanceRepository;
import com.gym.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TrainerAttendanceRepository trainerAttendanceRepository;

    public User createUser(User user) {
        if (user.getRole() == null) {
            throw new IllegalArgumentException("Role must be specified for the user.");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public List<User> getAllTrainers() {
        return userRepository.findByRole(UserRole.TRAINER);
    }

    // Get all customers from the database
    public List<User> getAllCustomers() {
        return userRepository.findByRole(UserRole.CUSTOMER);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> getAllAdmins() {
        return userRepository.findByRole(UserRole.ADMIN);
    }

    // Method to fetch trainer attendance report between startDate and endDate
    public List<TrainerAttendanceReport> getTrainerAttendanceReport(LocalDate startDate, LocalDate endDate) {
        // Convert LocalDate to LocalDateTime (start of the day for startDate, end of the day for endDate)
        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.atTime(LocalTime.MAX);

        List<TrainerAttendanceReport> attendanceReports = new ArrayList<>();
        List<User> trainers = userRepository.findByRole(UserRole.TRAINER);  // Fetch all trainers

        for (User trainer : trainers) {
            // Fetch attendance records for the current trainer within the date range
            List<TrainerAttendance> attendances = trainerAttendanceRepository
                    .findByTrainerAndClockInBetween(trainer, startDateTime, endDateTime);

            // Convert each TrainerAttendance record into a TrainerAttendanceReport and add it to the report list
            for (TrainerAttendance attendance : attendances) {
                TrainerAttendanceReport report = new TrainerAttendanceReport(
                        trainer.getId(),
                        trainer.getFullName(),
                        attendance.getClockIn().toLocalDate(),  // Convert LocalDateTime to LocalDate
                        attendance.isPresent()
                );
                attendanceReports.add(report);
            }
        }
        return attendanceReports;
    }
}
