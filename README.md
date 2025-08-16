### Food Order App - Spring Boot Application

This project is a comprehensive food ordering system built with Spring Boot following the Spring MVC design pattern. It provides RESTful APIs for managing users, restaurants, food items, and orders.

---

## 🚀 Technologies Used
- **Backend**: Java 17, Spring Boot 3.x
- **Database**: MySQL
- **Build Tool**: Maven
- **API Documentation**: Spring REST
- **Caching**: Spring Cache
- **Validation**: Jakarta Validation API
- **File Handling**: Spring Multipart

---

## ⚙️ Application Configuration
`application.properties` configuration:
```properties
spring.application.name=FoodOrderApp
server.port=8080

# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/online_food_order_app?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=theebanm

# JPA Settings
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

---

## 🗂 Project Structure (Spring MVC)
```
src/main/java
└── orderapp
    ├── controller  # Spring MVC Controllers
    ├── service     # Business Logic Layer
    ├── repository  # Data Access Layer
    ├── entity      # JPA Entities
    └── dto         # Data Transfer Objects
```

---

## 🔑 Key Features
1. **User Management**
   - Create/update users
   - Upload/get profile images
   - Fetch user details

2. **Restaurant Management**
   - CRUD operations for restaurants
   - Menu management (assign foods)
   - Pagination support

3. **Food Item Management**
   - Create/update food items
   - Get all foods with pagination

4. **Order Processing**
   - Generate bills
   - Process payments
   - Order status management
   - Cancel orders

---

## 📚 API Documentation

### User Controller (`/api/users`)
| Endpoint | Method | Description | Status |
|----------|--------|-------------|--------|
| `/` | POST | Create new user | 200 OK |
| `/` | GET | Get all users | 200 OK |
| `/{id}` | GET | Get user by ID | 200 OK |
| `/{id}/uploadImage` | PATCH | Upload user profile image | 200 OK |
| `/{id}/getImage` | GET | Get user profile image | 200 OK |

### Restaurant Controller (`/restaurant/api`)
| Endpoint | Method | Description | Status |
|----------|--------|-------------|--------|
| `/save` | POST | Create restaurant | 201 Created |
| `/get/{id}` | GET | Get restaurant by ID | 200 OK |
| `/getAll` | GET | Get all restaurants (paginated) | 200 OK |
| `/update/{id}` | PUT | Update restaurant | 200 OK |
| `/{id}/delete` | DELETE | Delete restaurant | 204 No Content |
| `/{restaurantId}/assignFood` | PUT | Assign foods to restaurant | 200 OK |
| `/{restaurantId}/getAll` | GET | Get foods by restaurant | 200 OK |
| `/{id}/getAllOrders` | GET | Get orders by restaurant | 200 OK |

### Order Controller (`/api/order`)
| Endpoint | Method | Description | Status |
|----------|--------|-------------|--------|
| `/bill` | POST | Generate order bill | 201 Created |
| `/pay-and-place-order` | POST | Process payment & place order | 201 Created |
| `/restaurant/{id}` | GET | Get orders by restaurant ID | 200 OK |
| `/update-order/{id}` | PUT | Update order status | 200 OK |
| `/{id}` | DELETE | Delete order | 204 No Content |
| `/{id}` | GET | Get order by ID | 200 OK |
| `/{id}/cancel` | PATCH | Cancel order | 200 OK |

### Food Controller (`/food/api`)
| Endpoint | Method | Description | Status |
|----------|--------|-------------|--------|
| `/save` | POST | Create food item | 200 OK |
| `/id` | GET | Get food by ID | 201 Created |
| `/all` | GET | Get all foods (paginated) | 200 OK |
| `/update` | PUT | Update food item | 201 Created |
| `/id` | DELETE | Delete food item | 204 No Content |

---

## 🛠 Setup & Installation

### Prerequisites
- Java 17
- MySQL 8.x
- Maven

### Steps to Run
1. **Create MySQL Database**:
   ```bash
   mysql -u root -p
   CREATE DATABASE online_food_order_app;
   ```

2. **Clone and Build**:
   ```bash
   git clone https://github.com/yourusername/food-order-app.git
   cd food-order-app
   mvn clean install
   ```

3. **Run Application**:
   ```bash
   mvn spring-boot:run
   ```

4. **Access API**:
   ```
   http://localhost:8080
   ```

---

## 🧪 Testing the API
Use Postman or cURL to test endpoints:

**Create User**:
```bash
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John Doe",
    "email": "john@example.com",
    "password": "secure123"
  }'
```

**Get Restaurants**:
```bash
curl http://localhost:8080/restaurant/api/getAll?pageNum=0&pageSize=5
```

---

## 📝 Best Practices
1. Layered architecture (Controller-Service-Repository)
2. DTO pattern for data transfer
3. Proper HTTP status codes
4. Input validation
5. Pagination for collections
6. Proper error handling
7. RESTful resource naming

---

## 📜 License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
