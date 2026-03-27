# RoleBasedProject

A Spring Boot application for employee management with role-based access control. This project allows users to register, login, and manage their profiles, while administrators have full access to manage all employees.

## Features

- **User Registration and Authentication**: Secure user registration and login with HTTP Basic Authentication
- **Role-Based Access Control**: Two roles - USER and ADMIN with different permissions
- **Employee Management**: CRUD operations for employee data
- **RESTful API**: Well-structured REST endpoints for all operations
- **Data Validation**: Input validation using Bean Validation
- **Database Integration**: MySQL database with JPA/Hibernate

## Tech Stack

- **Backend**: Spring Boot 4.0.5
- **Language**: Java 21
- **Database**: MySQL
- **Security**: Spring Security with HTTP Basic Authentication
- **ORM**: Spring Data JPA with Hibernate
- **Validation**: Bean Validation (Jakarta Validation)
- **Build Tool**: Maven
- **Other**: Lombok for reducing boilerplate code

## Prerequisites

Before running this application, make sure you have the following installed:

- Java 21 or higher
- MySQL Server
- Maven 3.6+

## Installation and Setup

1. **Clone the repository** (if applicable) or navigate to the project directory

2. **Set up MySQL Database**:
   - Create a database named `project`
   - Update database credentials in `src/main/resources/application.properties` if needed

3. **Build the project**:
   ```bash
   mvn clean install
   ```

4. **Run the application**:
   ```bash
   mvn spring-boot:run
   ```
   Or using the Maven wrapper:
   ```bash
   ./mvnw spring-boot:run
   ```

The application will start on `http://localhost:8081`

## API Endpoints

### Authentication Endpoints

- `POST /api/auth/register` - Register a new employee
- `POST /api/auth/login` - Login and authenticate user

### User Endpoints (Accessible by USER and ADMIN roles)

- `GET /api/user/{empId}` - Get own employee data
- `PUT /api/user/{empId}` - Update own employee data
- `DELETE /api/user/{empId}` - Delete own account

### Admin Endpoints (Accessible by ADMIN role only)

- `GET /api/admin` - Get all employees
- `GET /api/admin/{empId}` - Get employee by ID
- `PUT /api/admin/{empId}` - Update employee by ID
- `DELETE /api/admin/{empId}` - Delete employee by ID

## Database Schema

The application uses a single `employees` table with the following structure:

- `empId` (Primary Key, Auto-generated)
- `empName` (String, Not Null)
- `empEmail` (String, Not Null, Unique)
- `password` (String, Not Null)
- `empSalary` (Double, Not Null)
- `empDepartment` (String, Not Null)
- `empPosition` (String, Not Null)
- `role` (Enum: USER, ADMIN, Not Null)

## Usage

1. **Register a new user**:
   ```json
   POST /api/auth/register
   {
     "empName": "John Doe",
     "empEmail": "john@example.com",
     "password": "password123",
     "empSalary": 50000.0,
     "empDepartment": "IT",
     "empPosition": "Developer",
     "role": "USER"
   }
   ```

2. **Login**:
   ```json
   POST /api/auth/login
   {
     "empEmail": "john@example.com",
     "password": "password123"
   }
   ```

3. **Use HTTP Basic Auth** with your credentials for subsequent requests.

## Security

- Passwords are securely stored (implementation depends on AuthService)
- HTTP Basic Authentication is used for login
- Role-based authorization using Spring Security's @PreAuthorize annotations
- Input validation prevents malicious data

## Project Structure

```
src/main/java/com/springboot/rolebasedproject/
├── Controller/          # REST controllers
├── DTO/                 # Data Transfer Objects
├── Entity/              # JPA entities
├── Exception/           # Custom exceptions
├── Handler/             # Exception handlers
├── Mapper/              # Object mappers
├── Repository/          # JPA repositories
├── Response/            # API response wrappers
├── Service/             # Business logic services
└── RoleBasedProjectApplication.java
```

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if applicable
5. Submit a pull request

## License

This project is licensed under the MIT License.
