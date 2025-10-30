# Smart Clinic Management System - Database Design

## MySQL Database Design

### Tables Structure

#### patients Table
CREATE TABLE patients (
    patient_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(20),
    date_of_birth DATE NOT NULL,
    address TEXT,
    emergency_contact VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
Justification: Stores core patient information with unique email constraint to prevent duplicates. Emergency contact for medical emergencies.

#### doctors Table
CREATE TABLE doctors (
    doctor_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(20),
    specialization VARCHAR(100) NOT NULL,
    license_number VARCHAR(50) UNIQUE NOT NULL,
    is_available BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
Justification: Doctor-specific fields like specialization and license number. Availability flag to manage scheduling.

#### appointments Table
CREATE TABLE appointments (
    appointment_id INT AUTO_INCREMENT PRIMARY KEY,
    patient_id INT NOT NULL,
    doctor_id INT NOT NULL,
    appointment_date DATE NOT NULL,
    appointment_time TIME NOT NULL,
    status ENUM('scheduled', 'completed', 'cancelled', 'no-show') DEFAULT 'scheduled',
    reason TEXT,
    notes TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (patient_id) REFERENCES patients(patient_id) ON DELETE CASCADE,
    FOREIGN KEY (doctor_id) REFERENCES doctors(doctor_id) ON DELETE CASCADE,
    UNIQUE KEY unique_doctor_timeslot (doctor_id, appointment_date, appointment_time)
);
Justification: Prevents double-booking with unique timeslot constraint. Status tracking for appointment lifecycle. CASCADE delete to maintain referential integrity.

#### users Table (for authentication)
CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    role ENUM('admin', 'doctor', 'patient') NOT NULL,
    reference_id INT NOT NULL, -- links to patient_id or doctor_id
    is_active BOOLEAN DEFAULT TRUE,
    last_login TIMESTAMP NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
Justification: Centralized authentication with role-based access. Reference_id links to specific role table.

#### clinic_hours Table
CREATE TABLE clinic_hours (
    hour_id INT AUTO_INCREMENT PRIMARY KEY,
    doctor_id INT NOT NULL,
    day_of_week ENUM('Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday') NOT NULL,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL,
    is_available BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (doctor_id) REFERENCES doctors(doctor_id) ON DELETE CASCADE
);
Justification: Manages doctor availability by day and time slots.

#### Relationships
* One patient can have many appointments (One-to-Many)
* One doctor can have many appointments (One-to-Many)
* One doctor has many clinic hours (One-to-Many)
* One user account links to one patient or doctor (One-to-One)

## MongoDB Collection Design

#### prescriptions Collection
{
  "_id": ObjectId("507f1f77bcf86cd799439011"),
  "patient_id": 123,
  "doctor_id": 456,
  "appointment_id": 789,
  "issue_date": "2024-01-15",
  "medications": [
    {
      "name": "Amoxicillin",
      "dosage": "500mg",
      "frequency": "Every 8 hours",
      "duration": "7 days",
      "instructions": "Take with food"
    }
  ],
  "notes": "Complete full course of antibiotics",
  "refills_remaining": 0,
  "created_by": "dr_smith",
  "created_at": "2024-01-15T10:30:00Z"
}
Justification: Flexible schema allows varying medication arrays. Embedded documents for medications avoid complex joins.

#### medical_records Collection
{
  "_id": ObjectId("507f1f77bcf86cd799439012"),
  "patient_id": 123,
  "record_date": "2024-01-15",
  "record_type": "consultation",
  "vitals": {
    "blood_pressure": "120/80",
    "heart_rate": 72,
    "temperature": 98.6,
    "weight": 70.5
  },
  "symptoms": ["fever", "cough", "headache"],
  "diagnosis": "Upper respiratory infection",
  "treatment_plan": "Rest, fluids, prescribed antibiotics",
  "attending_doctor_id": 456,
  "follow_up_required": true,
  "follow_up_date": "2024-01-22",
  "attachments": [
    {
      "file_name": "xray_chest.jpg",
      "file_type": "image",
      "upload_date": "2024-01-15T11:00:00Z"
    }
  ]
}
Justification: Flexible document structure accommodates various record types. Arrays for symptoms and attachments.

#### feedback Collection
{
  "_id": ObjectId("507f1f77bcf86cd799439013"),
  "patient_id": 123,
  "doctor_id": 456,
  "appointment_id": 789,
  "rating": 5,
  "comments": "Excellent care and attention",
  "wait_time_rating": 4,
  "facility_rating": 5,
  "recommend": true,
  "submitted_date": "2024-01-16T14:20:00Z",
  "is_anonymous": false
}
Justification: Flexible rating system that can evolve without schema changes.

#### audit_logs Collection
{
  "_id": ObjectId("507f1f77bcf86cd799439014"),
  "action": "appointment_created",
  "user_id": 123,
  "user_role": "patient",
  "target_entity": "appointments",
  "target_id": 789,
  "old_values": {},
  "new_values": {
    "patient_id": 123,
    "doctor_id": 456,
    "appointment_date": "2024-01-20",
    "appointment_time": "10:00:00"
  },
  "ip_address": "192.168.1.100,
  "timestamp": "2024-01-15T09:15:30Z"
}
Justification: Flexible logging for various system actions without fixed schema.

## Design Decisions Justification

#### MySQL for Structured Data:
* Patients, Doctors, Appointments: Require ACID properties, complex relationships, and data integrity
* Referential Integrity: Foreign key constraints ensure data consistency
* Structured Queries: Complex joins for reports and analytics

#### MongoDB for Flexible Data:
* Prescriptions: Variable medication regimens, nested data structures
* Medical Records: Evolving schema, unstructured clinical notes, file attachments
* Feedback: Flexible rating systems that may change over time
* Audit Logs: Various log formats without fixed schema

#### Hybrid Approach Benefits:
* Performance: MySQL handles transactional data, MongoDB handles document storage
* Scalability: MongoDB scales horizontally for growing document data
* Flexibility: Schema evolution without migration for document data
* Data Integrity: MySQL ensures relational integrity for core business data


