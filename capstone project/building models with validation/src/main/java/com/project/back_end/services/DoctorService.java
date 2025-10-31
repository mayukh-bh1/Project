package com.project.back_end.services;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.smart_clinic.dto.Login;
import com.project.smart_clinic.models.Doctor;
import com.project.smart_clinic.repository.AppointmentRepository;
import com.project.smart_clinic.repository.DoctorRepository;

@Service
public class DoctorService {
    
    @Autowired
    private DoctorRepository doctorRepository;
    
    @Autowired
    private AppointmentRepository appointmentRepository;
    
    @Autowired
    private TokenService tokenService;
    
    public List<String> getDoctorAvailability(Long doctorId, LocalDate date) {
        // Implementation for doctor availability
        return List.of("09:00-10:00", "10:00-11:00", "14:00-15:00");
    }
    
    public int saveDoctor(Doctor doctor) {
        try {
            if (doctorRepository.findByEmail(doctor.getEmail()) != null) {
                return -1; // Doctor already exists
            }
            doctorRepository.save(doctor);
            return 1; // Success
        } catch (Exception e) {
            return 0; // Internal error
        }
    }
    
    public int updateDoctor(Doctor doctor) {
        try {
            if (!doctorRepository.existsById(doctor.getId())) {
                return -1; // Doctor not found
            }
            doctorRepository.save(doctor);
            return 1; // Success
        } catch (Exception e) {
            return 0; // Internal error
        }
    }
    
    public List<Doctor> getDoctors() {
        return doctorRepository.findAll();
    }
    
    public int deleteDoctor(long id) {
        try {
            if (!doctorRepository.existsById(id)) {
                return -1; // Doctor not found
            }
            appointmentRepository.deleteAllByDoctorId(id);
            doctorRepository.deleteById(id);
            return 1; // Success
        } catch (Exception e) {
            return 0; // Internal error
        }
    }
    
    public ResponseEntity<Map<String, String>> validateDoctor(Login login) {
        Map<String, String> response = new HashMap<>();
        try {
            Doctor doctor = doctorRepository.findByEmail(login.getIdentifier());
            if (doctor != null && doctor.getPassword().equals(login.getPassword())) {
                String token = tokenService.generateToken(login.getIdentifier());
                response.put("token", token);
                return ResponseEntity.ok(response);
            } else {
                response.put("error", "Invalid credentials");
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            response.put("error", "Internal server error");
            return ResponseEntity.internalServerError().body(response);
        }
    }
    
    public Map<String, Object> findDoctorByName(String name) {
        Map<String, Object> response = new HashMap<>();
        List<Doctor> doctors = doctorRepository.findByNameLike(name);
        response.put("doctors", doctors);
        return response;
    }
    
    // Other filter methods can be implemented similarly
}
