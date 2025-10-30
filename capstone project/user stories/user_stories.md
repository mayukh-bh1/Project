# User Stories - Smart Clinic Management System

## Admin User Stories

### Title: Admin Login
**As a** admin, **I want** to log into the portal with my username and password, **so that** I can manage the platform securely.
**Acceptance Criteria:**
1. Admin can access login page
2. System validates admin credentials
3. Successful login redirects to admin dashboard
4. Failed login shows appropriate error message
**Priority:** High
**Story Points:** 3
**Notes:**
- Must include secure authentication

### Title: Admin Logout
**As a** admin, **I want** to log out of the portal, **so that** I can protect system access.
**Acceptance Criteria:**
1. Logout option is available in admin dashboard
2. Session is properly terminated
3. User is redirected to login page after logout
**Priority:** High
**Story Points:** 2
**Notes:**
- Ensure session cleanup

### Title: Add Doctor Profile
**As a** admin, **I want** to add doctors to the portal, **so that** the clinic can expand its medical staff.
**Acceptance Criteria:**
1. Admin can access add doctor form
2. System validates doctor information
3. New doctor profile is saved to database
4. Confirmation message is displayed
**Priority:** High
**Story Points:** 5
**Notes:**
- Include required fields: name, specialization, contact info

### Title: Delete Doctor Profile
**As a** admin, **I want** to delete doctor's profile from the portal, **so that** I can manage clinic staff changes.
**Acceptance Criteria:**
1. Admin can view list of doctors
2. Delete option is available for each doctor
3. System confirms deletion action
4. Doctor profile is removed from system
**Priority:** Medium
**Story Points:** 3
**Notes:**
- Consider archiving instead of permanent deletion

### Title: Appointment Statistics Report
**As a** admin, **I want** to run a stored procedure in MySQL CLI to get the number of appointments per month, **so that** I can track usage statistics.
**Acceptance Criteria:**
1. Stored procedure exists in database
2. Procedure returns monthly appointment counts
3. Results are formatted clearly
4. Admin can execute procedure successfully
**Priority:** Medium
**Story Points:** 8
**Notes:**
- Requires database administration access

## Patient User Stories

### Title: View Doctors List
**As a** patient, **I want** to view a list of doctors without logging in, **so that** I can explore options before registering.
**Acceptance Criteria:**
1. Doctor list is publicly accessible
2. List shows doctor names and specializations
3. No authentication required
4. Information is up-to-date
**Priority:** High
**Story Points:** 5
**Notes:**
- Should not show sensitive doctor information

### Title: Patient Registration
**As a** patient, **I want** to sign up using my email and password, **so that** I can book appointments.
**Acceptance Criteria:**
1. Registration form collects required information
2. Email validation is performed
3. Password meets security requirements
4. Account is created upon successful registration
**Priority:** High
**Story Points:** 5
**Notes:**
- Include email verification

### Title: Patient Login
**As a** patient, **I want** to log into the portal, **so that** I can manage my bookings.
**Acceptance Criteria:**
1. Patient can access login page
2. System validates patient credentials
3. Successful login redirects to patient dashboard
4. Failed login shows appropriate error message
**Priority:** High
**Story Points:** 3
**Notes:**
- Secure authentication required

### Title: Patient Logout
**As a** patient, **I want** to log out of the portal, **so that** I can secure my account.
**Acceptance Criteria:**
1. Logout option is available in patient dashboard
2. Session is properly terminated
3. User is redirected to home page after logout
**Priority:** High
**Story Points:** 2
**Notes:**
- Ensure complete session cleanup

### Title: Book Appointment
**As a** patient, **I want** to book an hour-long appointment to consult with a doctor, **so that** I can receive medical care.
**Acceptance Criteria:**
1. Patient can view available time slots
2. System prevents double-booking
3. Appointment confirmation is provided
4. Appointment is saved to database
**Priority:** High
**Story Points:** 8
**Notes:**
- Must check doctor availability

### Title: View Upcoming Appointments
**As a** patient, **I want** to view my upcoming appointments, **so that** I can prepare accordingly.
**Acceptance Criteria:**
1. Patient can see list of future appointments
2. Appointment details include date, time, and doctor
3. List is sorted chronologically
4. Cancellation option is available
**Priority:** Medium
**Story Points:** 4
**Notes:**
- Include appointment status

## Doctor User Stories

### Title: Doctor Login
**As a** doctor, **I want** to log into the portal, **so that** I can manage my appointments.
**Acceptance Criteria:**
1. Doctor can access login page
2. System validates doctor credentials
3. Successful login redirects to doctor dashboard
4. Failed login shows appropriate error message
**Priority:** High
**Story Points:** 3
**Notes:**
- Secure authentication required

### Title: Doctor Logout
**As a** doctor, **I want** to log out of the portal, **so that** I can protect my data.
**Acceptance Criteria:**
1. Logout option is available in doctor dashboard
2. Session is properly terminated
3. User is redirected to login page after logout
**Priority:** High
**Story Points:** 2
**Notes:**
- Ensure complete session cleanup

### Title: View Appointment Calendar
**As a** doctor, **I want** to view my appointment calendar, **so that** I can stay organized.
**Acceptance Criteria:**
1. Calendar shows all scheduled appointments
2. Time slots are clearly displayed
3. Appointment details are accessible
4. Calendar is updated in real-time
**Priority:** High
**Story Points:** 6
**Notes:**
- Should show patient names and appointment reasons

### Title: Mark Unavailability
**As a** doctor, **I want** to mark my unavailability, **so that** patients see only the available slots.
**Acceptance Criteria:**
1. Doctor can set unavailable time periods
2. System blocks appointment booking during unavailable times
3. Patients see updated availability
4. Unavailability can be modified
**Priority:** Medium
**Story Points:** 5
**Notes:**
- Should support recurring unavailability patterns

### Title: Update Doctor Profile
**As a** doctor, **I want** to update my profile with specialization and contact information, **so that** patients have up-to-date information.
**Acceptance Criteria:**
1. Doctor can edit profile information
2. Changes are saved to database
3. Updated information reflects immediately
4. Profile validation is performed
**Priority:** Medium
**Story Points:** 4
**Notes:**
- Some fields may require admin approval

### Title: View Patient Details
**As a** doctor, **I want** to view the patient details for upcoming appointments, **so that** I can be prepared.
**Acceptance Criteria:**
1. Doctor can access patient profiles
2. Patient medical history is available
3. Contact information is displayed
4. Privacy regulations are followed
**Priority:** Medium
**Story Points:** 5
**Notes:**
- Must comply with healthcare privacy laws
