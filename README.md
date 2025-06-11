# QuizSystemBackend_Springboot

# 🧠 Online Quiz API

A Spring Boot-based backend API for an Online Quiz System. This project provides a complete backend for managing users, quizzes, questions, options, and results. JWT-based security, DTO mapping, and role-based functionality are implemented to ensure a clean, secure, and scalable API.

## 🚀 Features

- JWT-based authentication and authorization
- Role-based access control (Admin, User)
- Full CRUD operations for quizzes, questions, and options
- User quiz attempt flow with answer validation
- Result calculation and history
- Proper DTO usage and mapping
- Swagger/OpenAPI documentation

## 🛠️ Tech Stack

- Java 17
- Spring Boot 3+
- Spring Security + JWT
- Spring Data JPA
- Hibernate
- MySQL
- ModelMapper
- Maven
- Swagger / OpenAPI 3.1

## 📚 API Endpoints

### 👤 User Controller

| Method | Endpoint             | Description            |
|--------|----------------------|------------------------|
| GET    | `/api/users/{id}`    | Get user by ID         |
| PUT    | `/api/users/{id}`    | Update user by ID      |
| DELETE | `/api/users/{id}`    | Delete user by ID      |
| GET    | `/api/users`         | Get all users          |

---

### 🧩 Quiz Controller

| Method | Endpoint                              | Description                           |
|--------|----------------------------------------|---------------------------------------|
| PUT    | `/api/quizzes/{quizId}`               | Update quiz by ID                     |
| DELETE | `/api/quizzes/{quizId}`               | Delete quiz by ID                     |
| POST   | `/api/quizzes/{userId}/register`      | Create quiz by user ID                |
| GET    | `/api/quizzes`                        | Get all quizzes                       |
| GET    | `/api/quizzes/{id}`                   | Get quiz by ID                        |
| GET    | `/api/quizzes/user/{userId}`          | Get quizzes created by user           |
| GET    | `/api/quizzes/quiz/attempt/{quizId}`  | Attempt quiz (without correct answers)|

---

### 📊 Result Controller

| Method | Endpoint                           | Description                        |
|--------|------------------------------------|------------------------------------|
| POST   | `/api/results/submit/{userId}`     | Submit quiz result                 |
| GET    | `/api/results/user/{userId}`       | Get result history of a user       |
| GET    | `/api/results/admin/all`           | Admin access to all quiz results   |

---

### 🔐 Auth Controller

| Method | Endpoint              | Description           |
|--------|-----------------------|-----------------------|
| POST   | `/api/auth/register`  | User can register     |
| POST   | `/api/auth/login`     | User can login        |
| GET    | `/api/auth/profile`   | User can view profile |

---

## 🧾 Schemas (DTOs Used)

- `UserRequestDTO`, `UserResponseDTO`
- `QuizRequestDTO`, `QuizResponseDTO`
- `QuestionRequestDTO`, `QuestionResponseDTO`
- `OptionRequestDTO`, `OptionResponseDTO`
- `ResultRequestDTO`, `ResultResponseDTO`
- `AttemptQuizResponseDTO`, `AttemptQuestionDTO`, `AttemptOptionDTO`

---

## 🔗 API Documentation

Once the application is running:

- **Swagger UI:** `http://localhost:8080/swagger-ui/index.html`
- **OpenAPI Spec:** `http://localhost:8080/v3/api-docs`

---

## 🧪 Running Locally

```bash
git clone https://github.com/Paidi-Pavan-Kumar/QuizSystemBackend_Springboot.git
cd QuizSystemBackend_Springboot
mvnw.cmd spring-boot:run
