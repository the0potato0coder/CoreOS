package com.gym.controller;


import org.springframework.web.bind.annotation.*;
import com.gym.model.*;
import com.gym.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;


import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        // Set the user role based on the request body
        return ResponseEntity.ok(userService.createUser(user));
    }

    @GetMapping("/trainers")
    public ResponseEntity<List<User>> getAllTrainers() {
        return ResponseEntity.ok(userService.getAllTrainers());
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllCustomers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/admins")
    public ResponseEntity<List<User>> getAllAdmins() {
        return ResponseEntity.ok(userService.getAllAdmins());
    }

    @GetMapping("/reports/trainer-attendance")
    public ResponseEntity<List<TrainerAttendanceReport>> getTrainerAttendanceReport(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        return ResponseEntity.ok(userService.getTrainerAttendanceReport(startDate, endDate));
    }
}
