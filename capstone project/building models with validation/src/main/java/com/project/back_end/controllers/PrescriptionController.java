package com.project.smart_clinic.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.smart_clinic.models.Prescription;
import com.project.smart_clinic.service.MainService;
import com.project.smart_clinic.service.PrescriptionService;

@RestController
@RequestMapping("/api/prescription")  // Hardcoded path
public class PrescriptionController {
    
    @Autowired
    private PrescriptionService prescriptionService;
    
    @Autowired
    private MainService service;

    @PostMapping("/{token}")
    public ResponseEntity<Map<String, String>> savePrescription(@RequestBody Prescription prescription,
                                                              @PathVariable String token) {
        ResponseEntity<Map<String, String>> validationResponse = service.validateToken(token, "doctor");
        if (validationResponse != null) {
            return validationResponse;
        }
        
        return prescriptionService.savePrescription(prescription);
    }

    @GetMapping("/{appointmentId}/{token}")
    public ResponseEntity<?> getPrescription(@PathVariable Long appointmentId,
                                           @PathVariable String token) {
        ResponseEntity<Map<String, String>> validationResponse = service.validateToken(token, "doctor");
        if (validationResponse != null) {
            return validationResponse;
        }
        
        return prescriptionService.getPrescription(appointmentId);
    }
}
