# Smart Clinic Management System - Architecture Design

## 1. Architecture Summary

The Smart Clinic Management System follows a three-tier web architecture that separates the application into three distinct layers: Presentation, Application, and Data tiers. The system uses Spring Boot as its foundation, supporting both Thymeleaf server-rendered views for admin/doctor dashboards and REST APIs for mobile apps and external integrations. It employs dual databases - MySQL for structured relational data (patients, doctors, appointments) and MongoDB for flexible document-based data (prescriptions). This architecture provides scalability, maintainability, and deployment flexibility, allowing each tier to be independently developed, tested, and scaled.

## 2. Data and Control Flow

1. **User Interface Layer**: Users access the system through Thymeleaf-based web dashboards (Admin Dashboard, Doctor Dashboard) that render HTML server-side, or through REST API clients (mobile apps, frontend modules) that communicate via HTTP/JSON.

2. **Controller Layer**: User requests are routed to appropriate controllers based on URL paths and HTTP methods. Thymeleaf controllers handle server-rendered views, while REST controllers process API requests and return JSON responses.

3. **Service Layer**: Controllers delegate business logic to the service layer, which applies business rules, validations, and coordinates workflows (e.g., checking doctor availability before scheduling appointments).

4. **Repository Layer**: Services communicate with repositories for data access. Spring Data JPA repositories manage MySQL data, while Spring Data MongoDB repositories handle document-based data.

5. **Database Access**: Repositories interface directly with database engines - MySQL stores structured relational data with normalized schemas, and MongoDB stores flexible document data like prescriptions.

6. **Model Binding**: Retrieved data is mapped into Java model classes. MySQL data becomes JPA entities (@Entity), while MongoDB data becomes document objects (@Document).

7. **Response Generation**: In MVC flows, models are passed to Thymeleaf templates and rendered as dynamic HTML. In REST flows, models or DTOs are serialized to JSON and returned as HTTP responses.
