package com.project.back_end.services;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import com.project.smart_clinic.repository.AdminRepository;
import com.project.smart_clinic.repository.DoctorRepository;
import com.project.smart_clinic.repository.PatientRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class TokenService {
    // Remove @Value and use hardcoded secret for now
    private final String jwtSecret = "your-secret-key-here-make-it-very-long-and-secure-for-production";
    
    // Remove the @Value line and keep the rest of the code

    
//    @Autowired
    private AdminRepository adminRepository;
    
//    @Autowired
    private DoctorRepository doctorRepository;
    
//    @Autowired
    private PatientRepository patientRepository;
    
    public String generateToken(String identifier) {
        return Jwts.builder()
                .setSubject(identifier)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000)) // 7 days
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    
    public String extractIdentifier(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
    
    public boolean validateToken(String token, String user) {
        try {
            String identifier = extractIdentifier(token);
            
            return switch (user.toLowerCase()) {
                case "admin" -> adminRepository.findByUsername(identifier) != null;
                case "doctor" -> doctorRepository.findByEmail(identifier) != null;
                case "patient" -> patientRepository.findByEmail(identifier) != null;
                default -> false;
            };
        } catch (Exception e) {
            return false;
        }
    }
    
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }
}
