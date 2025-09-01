# Spring Security JWT Monolith

## Description
This is a **Spring Boot monolith project** demonstrating JWT-based authentication and role-based authorization.  
It implements secure APIs for `ADMIN` and `CUSTOMER` roles using Spring Security.  
    -> implemented both authorization like
                  - RequestMatchers
                  - Method Level Security (@EnableMethodSecurity)




✅ **Security implemented using both:** 
- **Method-level security:** `@PreAuthorize`  
- **URL-level security:** `requestMatchers` in `SecurityFilterChain`  

---


### End-to-End Flow Structure

```text
1️⃣ User Registration
    Client  -->  POST /api/register  -->  AuthController.register()
                                         Password encoded and user saved
                                         Response: Success message

2️⃣ User Login
    Client  -->  POST /api/login  -->  AuthController.login()
                                         AuthenticationManager validates credentials
                                         JWT token generated and returned
    Client stores JWT token

3️⃣ Access Secured Endpoints
    Client  -->  Request with Authorization: Bearer <JWT>
                 └─> JwtAuthFilter
                        - Validates JWT
                        - Sets SecurityContext with user and roles
                     └─> SecurityConfig.requestMatchers()
                            - URL-level access check (ADMIN / CUSTOMER / PUBLIC)
                         └─> Controller Method
                                - @PreAuthorize checks method-level roles (if applied)
                             └─> Response returned to client

4️⃣ Example Access
    - /api/admin/** → ADMIN only 
      [Endpoints protected via requestMatchers in SecurityFilterChain]
    - /api/customer/** → CUSTOMER only 
      [Endpoints protected via requestMatchers in SecurityFilterChain]
    - /api/MethodBased/Auth/both → ADMIN & CUSTOMER via @PreAuthorize
    - /api/MethodBased/Auth/customer → CUSTOMER via @PreAuthorize
    - /api/MethodBased/Auth/admin → ADMIN via @PreAuthorize
    - /api/common/** → Public access, no authorization required

---
## Features
- JWT Authentication and Authorization
- Role-based access control (`ADMIN`, `CUSTOMER`)
- Method-level security using `@PreAuthorize`
- URL-level security using `requestMatchers`
- REST API endpoints for registration, login, and user operations
- Stateless session management

---

## Technologies
- Java 17
- Spring Boot 3.x
- Spring Security
- JWT (JSON Web Tokens)
- Maven / Gradle
- PostgreSQL(DBever)

---

## API Endpoints

### Public Endpoints
| Method | Endpoint       | Description                  |
|--------|----------------|------------------------------|
| POST   | /api/register  | Register a new user          |
| POST   | /api/login     | Login and get JWT token      |

### Customer Endpoints
| Method | Endpoint         | Role Required |
|--------|-----------------|---------------|
| GET    | /api/customer/   | CUSTOMER      |

### Admin Endpoints
| Method | Endpoint                 | Role Required |
|--------|-------------------------|---------------|
| GET    | /api/admin/             | ADMIN         |
| GET    | /api/admin/user/{id}    | ADMIN         |
| GET    | /api/admin/user/{name}  | ADMIN         |

### Method-Level Authorization Examples
| Method | Endpoint                            | Access Roles           |
|--------|------------------------------------|-----------------------|
| GET    | /api/MethodBased/Auth/customer      | CUSTOMER              |
| GET    | /api/MethodBased/Auth/admin         | ADMIN                 |
| GET    | /api/MethodBased/Auth/both          | ADMIN, CUSTOMER       |

### Common Controller
| Method | Endpoint      | Access Roles            |
|--------|---------------|------------------------|
| GET    | /api/common/  | PUBLIC (all roles)     |

git clone https://github.com/your-username/spring-security-jwt-monolith.git
cd spring-security-jwt-monolith
