# CognitiveNotes 🧠

**A Secure Note-Taking and Intelligent Analysis Application**

CognitiveNotes is a Spring Boot backend application designed to help users securely create, manage, and organize their personal notes. It implements JWT-based authentication and authorization, user-specific data access, centralized exception handling, and PostgreSQL database integration.

The project is also being extended with AI-powered analysis to help users quickly understand documents, source code, and other notes.

## 🚀 Key Features

- **JWT Authentication:** Secure user authentication using JSON Web Tokens.
- **Authorization and Data Isolation:** Users can access only their own notes. One user cannot retrieve another user's private notes.
- **Note Management:** Backend functionality for managing user notes through REST APIs.
- **PostgreSQL Integration:** Persistent storage for user accounts and notes.
- **Global Exception Handling:** Centralized handling of application exceptions with structured error responses.
- **User Registration:** Dedicated registration DTO and authentication controller.
- **AI-Powered Analysis (Planned):** Intelligent analysis and summarization of documents, source code, and notes.

## 🛠️ Tech Stack

| Technology                  | Purpose                                    |
| --------------------------- | ------------------------------------------ |
| Java                        | Backend programming language               |
| Spring Boot                 | Backend application framework              |
| Spring Security             | Authentication and authorization           |
| JWT                         | Token-based authentication                 |
| Spring Data JPA / Hibernate | Database access and ORM                    |
| PostgreSQL                  | Relational database                        |
| Maven                       | Dependency management and build automation |
| REST APIs                   | Communication between clients and backend  |

## 🔐 Security and Data Privacy

A key feature of CognitiveNotes is its user-level data isolation.

For example:

- User 1 logs in and creates personal notes.
- User 1 can retrieve their own notes using authenticated requests.
- User 2 logs in with a different account and can retrieve only their own notes.
- User 2 cannot access User 1's notes through unauthorized API requests.

JWT authentication identifies the authenticated user, while authorization and user-specific data access enforce ownership restrictions.

## 🏗️ Project Structure

```text
src/main/java/com/strikes/cognitivenotes/
├── config/
├── controller/
│   ├── AuthController.java
│   └── NoteController.java
├── dto/
│   ├── ErrorResponse.java
│   └── UserRegistrationDto.java
├── exception/
│   └── GlobalExceptionHandler.java
├── model/
│   ├── Note.java
│   └── User.java
├── repository/
│   ├── NoteRepository.java
│   └── UserRepository.java
├── security/
│   ├── CustomUserDetailsService.java
│   ├── JwtFilter.java
│   └── JwtUtil.java
└── service/
   ├── NoteService.java
    └── UserService.java
```

### Architecture Overview

- **Controller Layer:** Handles incoming HTTP requests and API responses.
- **Service Layer:** Contains application business logic.
- **Repository Layer:** Manages database operations.
- **Model Layer:** Represents users and notes.
- **Security Layer:** Supports JWT processing and authenticated user identification.
- **DTO Layer:** Transfers structured request and error-response data.
- **Exception Layer:** Centralizes exception handling.

## ⚙️ Getting Started

### Prerequisites

Install the following before running the application:

- Java Development Kit (JDK)
- Maven or the included Maven Wrapper
- PostgreSQL
- An IDE such as IntelliJ IDEA or VS Code

### 1. Clone the Repository

```bash
git clone https://github.com/YOUR_USERNAME/CognitiveNotes.git
cd CognitiveNotes
```

Replace `YOUR_USERNAME` with your GitHub username.

### 2. Create the PostgreSQL Database

Open PostgreSQL or pgAdmin and execute:

```sql
CREATE DATABASE cognativenotes_db;
```

### 3. Configure Application Properties

Create `src/main/resources/application.properties` locally and configure your database credentials:

```properties
spring.application.name=cognitivenotes

spring.datasource.url=jdbc:postgresql://localhost:5432/cognativenotes_db
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.hibernate.ddl-auto=update
```

Set `DB_USERNAME` and `DB_PASSWORD` in your local environment. Configure any additional JWT-related properties required by your implementation.

**Security note:** Keep `application.properties`, database credentials, and JWT secrets out of version control. Do not commit real passwords or signing keys.

### 4. Run the Application

Using the Maven Wrapper on Windows:

```bash
mvnw.cmd spring-boot:run
```

Alternatively, if Maven is installed and configured in your system PATH:

```bash
mvn spring-boot:run
```

The application will start on the port configured for your Spring Boot application, typically `8080` unless changed.

## 🧪 Testing the Application

Use Postman or another REST API client to test the following workflows:

1. Register a new user.
2. Authenticate with the user's credentials.
3. Obtain and use the JWT token for protected requests.
4. Create notes while authenticated.
5. Retrieve notes belonging to the authenticated user.
6. Test that a different user cannot access another user's notes.
7. Test invalid requests and exception responses.

The exact endpoints and request formats depend on the controller implementation.

## 🗺️ Roadmap

- [x] Spring Boot backend setup
- [x] User registration and authentication workflow
- [x] JWT-based security components
- [x] User-specific note access
- [x] PostgreSQL integration
- [x] Centralized exception handling
- [ ] AI-powered document analysis
- [ ] AI-powered source code analysis
- [ ] AI-powered note summarization
- [ ] Automated unit and integration tests
- [ ] API documentation with Swagger / OpenAPI
- [ ] Cloud deployment

## 🎯 Project Objective

The goal of CognitiveNotes is to combine secure note management with intelligent information analysis. The project provides practical experience in backend development, REST API design, authentication, authorization, relational database management, and exception handling, with AI capabilities planned as the next development milestone.

## 👨‍💻 Developer

**Aman Naikwade**

Aspiring Java Backend Developer

- GitHub: [Aman8669](https://github.com/Aman8669)

---

*Built with Java, Spring Boot, PostgreSQL, and a focus on secure backend development.*
