package com.project.smart_clinic.controller;

import com.project.smart_clinic.models.Doctor;
import com.project.smart_clinic.service.DoctorService;
import com.project.smart_clinic.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {
    
    @Autowired
    private DoctorService doctorService;
    
    
    @GetMapping
    public ResponseEntity<?> getDoctors() {
        try {
            List<Doctor> doctors = doctorService.getDoctors();
            System.out.println("Found " + doctors.size() + " doctor"); // Debug log
            return ResponseEntity.ok(doctors);
        } catch (Exception e) {
            e.printStackTrace(); // This will show the actual error in console
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }
    // ... rest of your methods
}
