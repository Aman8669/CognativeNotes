# 🧠 CognitiveNotes — Secure Smart Notes Platform

A full-stack note management application built with **Java, Spring Boot, React, and PostgreSQL**, featuring JWT-based authentication, user-level data isolation, and a foundation for AI-powered content analysis.

🌐 **Live Demo:** [Open CognitiveNotes](https://cognative-notes-frontend-oo2iyhw1r-aman09.vercel.app/)  
💻 **Backend Repository:** [GitHub – CognativeNotes](https://github.com/Aman8669/CognativeNotes)

## 📌 Overview

CognitiveNotes is a web application designed to help users create, manage, and organize personal notes securely.

The project focuses on backend security, REST API development, database integration, and full-stack deployment. Each authenticated user can access their own notes without accessing another user's private data.

The application uses a separate React frontend and Spring Boot backend, with PostgreSQL for persistent storage.

## ✨ Key Features

- **JWT Authentication:** Token-based authentication for user login and protected API access.
- **Authorization:** Secures protected resources for authenticated users.
- **User-Specific Data Isolation:** Users can access their own notes without accessing another user's notes.
- **Note Management:** Create, retrieve, update, and delete personal notes through REST APIs.
- **PostgreSQL Integration:** Stores user and note data in a relational database.
- **Global Exception Handling:** Centralizes application error handling and provides structured error responses.
- **RESTful API Architecture:** Separates request handling, business logic, and database operations.
- **Cloud Deployment:** Frontend hosted on Vercel, backend on Render, and PostgreSQL hosted on Neon.
- **AI-Powered Analysis:** Planned extension for analyzing and summarizing notes, documents, and source code. Mark this feature as completed once it is implemented and tested.

## 🛠️ Tech Stack

| Technology | Purpose |
|---|---|
| Java 21 | Backend development |
| Spring Boot | Backend application framework |
| Spring Security | Authentication and authorization |
| JWT | Token-based authentication |
| Spring Data JPA | Database access |
| Hibernate | Object-relational mapping |
| PostgreSQL | Relational database |
| Maven | Build automation and dependency management |
| React | Frontend development |
| Vite | Frontend development server and build tool |
| Axios | HTTP communication with backend APIs |
| Git & GitHub | Version control |
| Render | Backend deployment |
| Vercel | Frontend deployment |
| Neon | Cloud PostgreSQL database |

## 🏗️ Architecture

```text
                 User / Browser
                       |
                       v
              React + Vite Frontend
                    (Vercel)
                       |
                 HTTP / REST API
                       |
                       v
              Spring Boot Backend
                    (Render)
                       |
          +------------+------------+
          |            |            |
          v            v            v
     Controllers    Services    Security
          |                         |
          v                         v
      Repositories            JWT Validation
          |
          v
      PostgreSQL
         (Neon)
```

### Backend Architecture

The backend follows a layered architecture to keep responsibilities organized:

- **Controller Layer:** Handles incoming HTTP requests and API responses.
- **Service Layer:** Implements application business logic.
- **Repository Layer:** Performs database operations using Spring Data JPA.
- **Model Layer:** Represents entities such as users and notes.
- **Security Layer:** Handles JWT processing and authenticated user identification.
- **DTO Layer:** Transfers structured request and response data.
- **Exception Layer:** Centralizes exception handling.

## 🔐 Security and Data Isolation

One of the core design goals of CognitiveNotes is protecting users' private notes.

The authentication and authorization flow works as follows:

1. A user registers and logs in with their credentials.
2. The backend authenticates the user and returns a JWT.
3. The frontend stores the token and sends it with protected API requests.
4. Spring Security and the JWT filter process authenticated requests.
5. The backend identifies the logged-in user and restricts note access to that user's data.

**Example:** If User A creates a note, User B must not be able to retrieve or modify that note through their own authenticated account.

This demonstrates practical implementation of authentication, authorization, and user-level data isolation.

## 🚀 Live Deployment

| Component | Platform |
|---|---|
| Frontend | [Vercel](https://cognative-notes-frontend-oo2iyhw1r-aman09.vercel.app/) |
| Backend | [Render](https://cognativenotes.onrender.com) |
| Database | [Neon PostgreSQL](https://neon.tech/) |

The frontend communicates with the deployed Spring Boot backend through REST APIs. The backend connects to the cloud PostgreSQL database for persistent data storage.

## 📂 Backend Project Structure

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

## ⚙️ Run Locally

### Prerequisites

- JDK 21
- PostgreSQL
- Git
- Maven or the included Maven Wrapper
- Node.js and npm for the separate frontend project

### 1. Clone the Backend Repository

```bash
git clone https://github.com/Aman8669/CognativeNotes.git
cd CognativeNotes
```

### 2. Create the Database

Create a PostgreSQL database:

```sql
CREATE DATABASE cognativenotes_db;
```

### 3. Configure Environment Variables

Create `src/main/resources/application.properties` locally and configure your database connection.

Example configuration:

```properties
spring.application.name=cognitivenotes

spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

spring.jpa.hibernate.ddl-auto=update
```

Configure `DB_URL`, `DB_USERNAME`, and `DB_PASSWORD` in your local environment. Add any additional JWT, security, or AI-provider settings required by your implementation.

Never commit database passwords, JWT signing secrets, or API keys to GitHub.

### 4. Start the Backend

On Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

On macOS or Linux:

```bash
./mvnw spring-boot:run
```

The application typically starts at `http://localhost:8080`, unless configured otherwise.

### 5. Start the Frontend

Clone or open the separate React frontend repository and configure its API base URL for the local backend.

Install dependencies and start the development server:

```bash
npm install
npm run dev
```

The Vite development server typically runs at `http://localhost:5173`.

## 🧪 Testing Checklist

The following workflows can be tested using the frontend or an API client such as Postman:

- Register a new user and log in.
- Verify that successful login returns a JWT.
- Access protected endpoints with a valid token.
- Create, retrieve, update, and delete notes.
- Verify that users cannot access another user's private notes.
- Test invalid credentials and unauthorized requests.
- Verify appropriate error responses for invalid requests.
- Test the deployed frontend-to-backend connection.

## 🎯 What I Learned

- Building REST APIs using Java and Spring Boot.
- Implementing JWT-based authentication and authorization.
- Understanding Spring Security and request filtering.
- Designing a layered backend architecture.
- Integrating PostgreSQL with Spring Data JPA and Hibernate.
- Implementing centralized exception handling.
- Managing cross-origin requests between a separate frontend and backend.
- Configuring environment variables and deploying a full-stack application.
- Connecting a cloud-hosted backend to a managed PostgreSQL database.

## 🔮 Future Enhancements

- AI-powered summarization and analysis of notes, documents, and source code.
- Automated unit and integration testing.
- API documentation using Swagger/OpenAPI.
- CI/CD automation with GitHub Actions.
- Improved frontend validation, loading states, and error messages.

## 👨‍💻 Developer

**Aman Nasir Naikwade**  
Aspiring Java Full Stack Developer

- GitHub: [Aman8669](https://github.com/Aman8669)
- Project Repository: [CognativeNotes](https://github.com/Aman8669/CognativeNotes)
- Live Application: [CognitiveNotes](https://cognative-notes-frontend-oo2iyhw1r-aman09.vercel.app/)

---

*Built to demonstrate practical skills in Java backend development, application security, database integration, and full-stack deployment.*
