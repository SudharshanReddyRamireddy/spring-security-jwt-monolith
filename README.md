# Spring Security JWT Monolith

A **Spring Boot monolith project** demonstrating **JWT-based authentication** and **role-based authorization**.  
Supports both **URL-level security** (`requestMatchers`) and **method-level security** (`@PreAuthorize`).

---

## Features
- JWT authentication & logout (with token blocklist)  
- Role-based access control: `ADMIN`, `CUSTOMER`  
- Method-level security using `@PreAuthorize`  
- URL-level security using `requestMatchers`  
- Spring Bean validations  
- Unit testing (Spring Test + Mockito)  
- Global exception handling  
- Logging with SLF4J  
- Spring Scheduler to clean blocked tokens every 24h  
- APIs return consistent DTO responses for both success and error  

---

## Entities & Relationships

### User
Represents a system user with authentication details and assigned roles.

**Fields:**
- `id` (Long) – Primary key  
- `username` (String) – Unique username  
- `password` (String) – Encrypted password  
- `roles` (Set<Role>) – Roles assigned to the user (Many-to-Many)  

### Role
Represents a role assigned to a user (e.g., ADMIN, CUSTOMER).

**Fields:**
- `id` (Long) – Primary key  
- `name` (String) – Role name (ADMIN, CUSTOMER)  
- `users` (Set<User>) – Users assigned to this role (Many-to-Many)  

### Relationship
- **User ↔ Role**: Many-to-Many  
  - A user can have multiple roles  
  - A role can be assigned to multiple users  

**ER Diagram (optional)**  
![ER Diagram](path/to/er-diagram.png)  

---

## Technologies
- Java 17  
- Spring Boot 3.x  
- Spring Security + JWT  
- PostgreSQL (via DBeaver)  
- Gradle  

---

## Flow

1. **Register & Login**
   - `POST /api/register` → Register a new user (supports multiple roles)  
   - `POST /api/login` → Authenticate and return JWT  

2. **Access APIs**
   - Include JWT in `Authorization: Bearer <token>` header  
   - Role-based access for `CUSTOMER` and `ADMIN`  

3. **Logout**
   - `POST /api/logout` → Adds the JWT token to the blocklist  
   - Filter chain rejects blocked tokens  

4. **Token Cleanup**
   - Spring Scheduler clears blocked tokens every 24 hours  

---

## API Endpoints

### Public
| Method | Endpoint       | Description            |
|--------|----------------|------------------------|
| POST   | /api/register  | Register a new user    |
| POST   | /api/login     | Login & get JWT token  |
| POST   | /api/logout    | Logout & block token   |

### Common
| Method | Endpoint     | Access Roles   |
|--------|--------------|----------------|
| GET    | /api/common/ | All roles      |

### Customer
| Method | Endpoint                    | Role Required |
|--------|-----------------------------|---------------|
| GET    | /api/customer/              | CUSTOMER      |
| GET    | /api/customer/myProfile     | CUSTOMER      |
| PATCH  | /api/customer/updateProfile | CUSTOMER      |

### Admin
| Method | Endpoint                 | Role Required |
|--------|--------------------------|---------------|
| GET    | /api/admin/              | ADMIN         |
| GET    | /api/admin/user/{id}     | ADMIN         |
| GET    | /api/admin/user/{name}   | ADMIN         |
| PATCH  | /api/admin/user/update   | ADMIN         |

### Method-Level Authorization
| Method | Endpoint                          | Access Roles       |
|--------|----------------------------------|-------------------|
| GET    | /api/MethodBased/Auth/customer   | CUSTOMER          |
| GET    | /api/MethodBased/Auth/admin      | ADMIN             |
| GET    | /api/MethodBased/Auth/both       | ADMIN, CUSTOMER   |

---

## Notes
- JWT blocklist prevents reuse of logged-out tokens  
- Scheduler automatically cleans blocked tokens  
- Centralized exception handling improves API error responses  
- SLF4J logs provide authentication and authorization traceability  

> **Note:** These APIs are implemented for practice. The main focus is **JWT authentication**, **role-based authorization**, and **secure API flows**.
