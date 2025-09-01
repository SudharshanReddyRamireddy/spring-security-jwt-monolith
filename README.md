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
START: User interacts with the application

1️⃣ Registration & Login
    Client --> POST /api/register --> AuthController.register()
        - Checks if username exists
        - Encodes password and saves user
        - Response: "USER SUCCESSFULLY REGISTERED."
    Client --> POST /api/login --> AuthController.login()
        - AuthenticationManager validates credentials
        - JWT token generated and returned
        - Client stores JWT token

2️⃣ Common Access
    Client --> GET /api/common/ --> CommonController
        - Accessible by both CUSTOMER and ADMIN
        - Response: "Hi.. Hello, This is From Common Controller"

3️⃣ Admin Access
    Client --> GET /api/admin/ --> AdminController
        - Accessible only by ADMIN
        - Response: "Admin Controller"
    Client --> GET /api/admin/user/{userId} --> AdminController
        - Fetch user details by ID
    Client --> GET /api/admin/user/{userName} --> AdminController
        - Fetch user details by username

4️⃣ Customer Access
    Client --> GET /api/customer/ --> CustomerController
        - Accessible only by CUSTOMER
        - Response: "Customer Controller"

5️⃣ Method-Based Authorization
    Client --> GET /api/MethodBased/Auth/customer --> MethodBasedAuthorizationController
        - Accessible only by CUSTOMER
        - Response: "METHOD BASED AUTHORIZATION FOR CUSTOMER"
    Client --> GET /api/MethodBased/Auth/admin --> MethodBasedAuthorizationController
        - Accessible only by ADMIN
        - Response: "METHOD BASED AUTHORIZATION FOR ADMIN"
    Client --> GET /api/MethodBased/Auth/both --> MethodBasedAuthorizationController
        - Accessible by both ADMIN and CUSTOMER
        - Response: "METHOD BASED AUTHORIZATION FOR BOTH"

END

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

