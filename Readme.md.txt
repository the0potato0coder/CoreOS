Gym Management System - Spring Boot BackendA comprehensive, robust backend for a gym management system built with Spring Boot. This application provides a complete RESTful API for managing members, subscriptions, class bookings, and authentication, with role-based access control for different user types.✨ FeaturesUser Authentication: Secure user registration and login system using JWT (JSON Web Tokens). Passwords are encrypted using BCrypt.Role-Based Authorization: The system supports different roles (MEMBER, STAFF, ADMIN) with protected endpoints ensuring users can only access authorized resources. Admins can register Staff and other Admins.Membership Management: Admins can create and manage different types of membership plans (e.g., Monthly, Quarterly, Annual).Subscription Handling: Members can subscribe to a membership plan, with automatic start and end date calculation.Class Booking System: Authenticated members with an active subscription can book available classes. The system validates class capacity before confirming a booking.Attendance Tracking: Members can mark their check-in attendance.Payment Processing: A dummy endpoint to simulate payment processing for subscriptions.🛠️ Tech StackJava 17Spring Boot 3: For building the RESTful API.Spring Security: For handling authentication and authorization.Spring Data JPA (Hibernate): For database interaction.MySQL: Relational database for data persistence.Lombok: To reduce boilerplate code.jjwt: For creating and validating JSON Web Tokens.⚙️ System ArchitectureThe application follows a classic layered architecture on the backend to ensure separation of concerns and maintainability.Controller Layer: Exposes the REST API endpoints and handles incoming HTTP requests.Service Layer: Contains all the business logic (e.g., checking if a user has an active subscription).Repository Layer: Handles data access and communication with the MySQL database using Spring Data JPA.Security Layer: Intercepts requests to validate JWTs and enforce authorization rules.🚀 Setup and RunPrerequisitesJava JDK 17 or laterMaven 3.8 or laterMySQL ServerA REST client like Postman or cURL.Backend SetupClone the repository:git clone <your-repo-url>
cd GymManagementBackend
Configure the database:Open src/main/resources/application.properties.Update spring.datasource.url, spring.datasource.username, and spring.datasource.password to match your local MySQL configuration.Update the JWT secret key application.security.jwt.secret-key. You can generate a Base64-encoded 256-bit key online.Run the application:mvn spring-boot:run
The backend server will start on http://localhost:8080.🧪 API Endpoints & Sample QueriesHere are sample cURL requests to test the API endpoints.Note: For protected endpoints, first log in to get a JWT token. Replace {{adminToken}} and {{memberToken}} with the actual tokens.1. AuthenticationRegister a New Member (Public)curl -X POST http://localhost:8080/api/auth/register/member \
-H "Content-Type: application/json" \
-d '{
    "firstName": "Test",
    "lastName": "User",
    "email": "testuser@example.com",
    "password": "password123",
    "dateOfBirth": "1990-01-01"
}'
Login (Public)curl -X POST http://localhost:8080/api/auth/login \
-H "Content-Type: application/json" \
-d '{
    "email": "testuser@example.com",
    "password": "password123"
}'
Register a New Admin (Admin-Only)curl -X POST http://localhost:8080/api/auth/register/admin \
-H "Content-Type: application/json" \
-H "Authorization: Bearer {{adminToken}}" \
-d '{
    "firstName": "Admin",
    "lastName": "User",
    "email": "admin@example.com",
    "password": "adminpass",
    "dateOfBirth": "1985-05-10"
}'
2. Memberships (Admin & Member)Create a Membership Plan (Admin-Only)curl -X POST http://localhost:8080/api/admin/membership-plans \
-H "Content-Type: application/json" \
-H "Authorization: Bearer {{adminToken}}" \
-d '{
    "name": "Gold Plan",
    "description": "Access to all gym facilities and classes.",
    "price": 50.0,
    "durationInDays": 30
}'
Subscribe to a Plan (Member-Only)curl -X POST http://localhost:8080/api/members/subscribe \
-H "Content-Type: application/json" \
-H "Authorization: Bearer {{memberToken}}" \
-d '{
    "userId": 1,
    "planId": 1
}'
3. Gym Classes (Admin & Member)Create a Gym Class (Admin-Only)curl -X POST http://localhost:8080/api/admin/gym-classes \
-H "Content-Type: application/json" \
-H "Authorization: Bearer {{adminToken}}" \
-d '{
    "name": "Morning Yoga",
    "trainerId": 2,
    "schedule": "2024-10-26T10:00:00",
    "durationInMinutes": 60,
    "capacity": 15
}'
Book a Class (Member-Only)curl -X POST http://localhost:8080/api/members/book-class \
-H "Content-Type: application/json" \
-H "Authorization: Bearer {{memberToken}}" \
-d '{
    "userId": 1,
    "classId": 1
}'
4. Attendance & Payments (Member)Mark Attendance (Member-Only)curl -X POST http://localhost:8080/api/attendance/check-in \
-H "Authorization: Bearer {{memberToken}}"
Make a Dummy Payment (Member-Only)curl -X POST http://localhost:8080/api/members/payments \
-H "Content-Type: application/json" \
-H "Authorization: Bearer {{memberToken}}" \
-d '{
    "userId": 1,
    "amount": 50.0
}'
📄 Database SchemaThe core database tables include users, membership_plans, subscriptions, gym_classes, bookings, attendance, and payments. The relationships between these tables form the backbone of the application's data layer.