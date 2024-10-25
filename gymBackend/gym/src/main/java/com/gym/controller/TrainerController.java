package com.gym.controller;

import com.gym.model.TrainerAttendance;
import com.gym.model.User;
import com.gym.model.UserRole;
import com.gym.service.TrainerAttendanceService;
import com.gym.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/trainer")
public class TrainerController {
    @Autowired
    private TrainerAttendanceService attendanceService;

    @Autowired
    private UserService userService;

    @PostMapping("/clock-in")
    public ResponseEntity<TrainerAttendance> clockIn(@RequestBody Long trainerId) {
        return ResponseEntity.ok(attendanceService.clockIn(trainerId));
    }

    @PostMapping("/clock-out")
    public ResponseEntity<TrainerAttendance> clockOut(@RequestBody Long attendanceId) {
        return ResponseEntity.ok(attendanceService.clockOut(attendanceId));
    }

    @PostMapping("/customers")
    public ResponseEntity<User> addCustomer(@RequestBody User customer) {
        customer.setRole(UserRole.CUSTOMER);
        return ResponseEntity.ok(userService.createUser(customer));
    }
}
