# 🏦 Bank System - Microservices Architecture

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.2-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Spring Cloud](https://img.shields.io/badge/Spring%20Cloud-5.0.0-blue.svg)](https://spring.io/projects/spring-cloud)
[![MySQL](https://img.shields.io/badge/MySQL-8.0+-blue.svg)](https://www.mysql.com/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

A comprehensive **Banking System** built with **Spring Boot Microservices Architecture**. This project demonstrates a modern, scalable, and maintainable banking application with service discovery, API gateway, and inter-service communication.

---

## 📋 Table of Contents

- [Architecture Overview](#-architecture-overview)
- [Microservices](#-microservices)
- [Technology Stack](#-technology-stack)
- [Features](#-features)
- [API Documentation](#-api-documentation)
- [Getting Started](#-getting-started)
- [Configuration](#-configuration)
- [API Endpoints](#-api-endpoints)
- [Database Schema](#-database-schema)
- [Testing](#-testing)
- [Project Structure](#-project-structure)
- [Contributing](#-contributing)
- [Author](#-author)

---

## 🏗 Architecture Overview

```
┌─────────────────────────────────────────────────────────────────────────────┐
│                              Client Applications                             │
└─────────────────────────────────────────────────────────────────────────────┘
                                      │
                                      ▼
┌─────────────────────────────────────────────────────────────────────────────┐
│                           API Gateway (Port 8080)                           │
│                    - Request Routing                                        │
│                    - Load Balancing                                         │
│                    - Service Aggregation                                    │
└─────────────────────────────────────────────────────────────────────────────┘
                                      │
        ┌─────────────────────────────┼─────────────────────────────┐
        │                             │                             │
        ▼                             ▼                             ▼
┌───────────────────┐     ┌───────────────────┐     ┌───────────────────┐
│  Customer Service │     │   Account Service │     │Transaction Service│
│   (Port 8081)     │     │   (Port 8082)     │     │   (Port 8083)     │
│                   │     │                   │     │                   │
│ - Customer CRUD   │◄───►│ - Account CRUD    │◄───►│ - Transaction Log │
│ - Validation      │     │ - Deposits        │     │ - History         │
│ - Status Mgmt     │     │ - Withdrawals     │     │ - Status Tracking │
└───────────────────┘     └───────────────────┘     └───────────────────┘
        │                             │                             │
        └─────────────────────────────┼─────────────────────────────┘
                                      │
                                      ▼
┌─────────────────────────────────────────────────────────────────────────────┐
│                        Eureka Server (Port 8761)                            │
│                    - Service Discovery & Registration                        │
└─────────────────────────────────────────────────────────────────────────────┘
                                      │
                                      ▼
┌─────────────────────────────────────────────────────────────────────────────┐
│                              MySQL Database                                  │
│                    - Persistent Data Storage                                 │
└─────────────────────────────────────────────────────────────────────────────┘
```

---

## 🔧 Microservices

### 1. **Eureka Server** (Service Discovery)
- **Port**: 8761
- **Purpose**: Service registration and discovery
- All microservices register with Eureka for dynamic service location

### 2. **API Gateway**
- **Port**: 8080
- **Purpose**: Single entry point for all client requests
- Routes requests to appropriate microservices
- Load balancing with client-side discovery

### 3. **Customer Service**
- **Port**: 8081 (configurable)
- **Purpose**: Customer management operations
- **Features**:
  - Customer registration and profile management
  - Customer validation and status tracking
  - National ID uniqueness validation

### 4. **Account Service**
- **Port**: 8082 (configurable)
- **Purpose**: Bank account management
- **Features**:
  - Account creation with multiple account types
  - Deposit and withdrawal operations
  - Account status management (PENDING, ACTIVE, CLOSED, FROZEN)
  - Balance tracking with optimistic locking

### 5. **Transaction Service**
- **Port**: 8083 (configurable)
- **Purpose**: Transaction logging and history
- **Features**:
  - Transaction recording for all operations
  - Support for DEPOSIT, WITHDRAWAL, and TRANSFER types
  - Transaction history tracking

---

## 💻 Technology Stack

| Category | Technology |
|----------|------------|
| **Language** | Java 21 |
| **Framework** | Spring Boot 4.0.2 |
| **Microservices** | Spring Cloud 5.0.0 |
| **Service Discovery** | Netflix Eureka |
| **API Gateway** | Spring Cloud Gateway |
| **Inter-Service Communication** | OpenFeign |
| **Database** | MySQL |
| **ORM** | Spring Data JPA / Hibernate |
| **Validation** | Jakarta Validation |
| **Mapping** | MapStruct 1.6.3 |
| **Boilerplate Reduction** | Lombok |
| **API Documentation** | SpringDoc OpenAPI (Swagger) |
| **Build Tool** | Maven |

---

## ✨ Features

### Account Management
- ✅ Create accounts with different types (SAVINGS, CHECKING, BUSINESS, INVESTMENT, LOAN)
- ✅ Multi-currency support
- ✅ Account status management (PENDING → ACTIVE → CLOSED/FROZEN)
- ✅ Deposit and withdrawal operations
- ✅ Balance validation and optimistic locking
- ✅ Account closure with zero-balance validation

### Customer Management
- ✅ Customer registration with national ID validation
- ✅ Customer profile management
- ✅ Customer status tracking (PENDING, ACTIVE, INACTIVE)
- ✅ Unique national ID enforcement

### Transaction Management
- ✅ Automatic transaction logging
- ✅ Transaction types: DEPOSIT, WITHDRAWAL, TRANSFER
- ✅ Transaction history and audit trail

### Cross-Cutting Concerns
- ✅ Centralized exception handling
- ✅ Structured error responses
- ✅ Input validation with Jakarta Validation
- ✅ API documentation with OpenAPI/Swagger
- ✅ Service-to-service communication with OpenFeign

---

## 📖 API Documentation

Each service exposes Swagger UI documentation:

| Service | Swagger UI URL |
|---------|---------------|
| Customer Service | `http://localhost:8081/swagger-ui.html` |
| Account Service | `http://localhost:8082/swagger-ui.html` |
| Transaction Service | `http://localhost:8083/swagger-ui.html` |

---

## 🚀 Getting Started

### Prerequisites

- **Java 21** or higher
- **Maven 3.8+**
- **MySQL 8.0+**
- **IDE** (IntelliJ IDEA recommended)

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/your-username/BankSystem.git
   cd BankSystem
   ```

2. **Create MySQL databases**
   ```sql
   CREATE DATABASE customer_db;
   CREATE DATABASE account_db;
   CREATE DATABASE transaction_db;
   ```

3. **Configure database connections**
   
   Update `application.properties` in each service:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/[database_name]
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

4. **Start services in order**
   ```bash
   # 1. Start Eureka Server
   cd Eureka-Server
   mvn spring-boot:run
   
   # 2. Start Customer Service
   cd ../Customer-Service
   mvn spring-boot:run
   
   # 3. Start Account Service
   cd ../Account-Service
   mvn spring-boot:run
   
   # 4. Start Transaction Service
   cd ../Transaction-Service
   mvn spring-boot:run
   
   # 5. Start API Gateway
   cd ../Api-Gateway
   mvn spring-boot:run
   ```

5. **Verify services**
   - Eureka Dashboard: `http://localhost:8761`
   - API Gateway: `http://localhost:8080`

---

## ⚙️ Configuration

### Service Ports (Default)

| Service | Port |
|---------|------|
| Eureka Server | 8761 |
| API Gateway | 8080 |
| Customer Service | 8081 |
| Account Service | 8082 |
| Transaction Service | 8083 |

### Environment Variables

Each service can be configured using environment variables:

```bash
# Database Configuration
SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/db_name
SPRING_DATASOURCE_USERNAME=root
SPRING_DATASOURCE_PASSWORD=password

# Eureka Configuration
EUREKA_CLIENT_SERVICEURL_DEFAULTZONE=http://localhost:8761/eureka/
```

---

## 🔌 API Endpoints

### Customer Service APIs

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/customers` | Get all customers |
| `GET` | `/customers/{id}` | Get customer by ID |
| `POST` | `/customers` | Create new customer |
| `PUT` | `/customers/{id}` | Update customer |
| `DELETE` | `/customers/{id}` | Delete customer |
| `GET` | `/customers/exists/{id}` | Check if customer exists |

### Account Service APIs

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/accounts` | Get all accounts |
| `GET` | `/accounts/{id}` | Get account by ID |
| `GET` | `/accounts/number/{accountNumber}` | Get account by number |
| `GET` | `/accounts/customer/{customerId}` | Get accounts by customer |
| `POST` | `/accounts` | Create new account |
| `PUT` | `/accounts/{id}` | Update account |
| `DELETE` | `/accounts/{id}` | Delete account |
| `POST` | `/accounts/{id}/deposit` | Deposit money |
| `POST` | `/accounts/{id}/withdraw` | Withdraw money |
| `POST` | `/accounts/{id}/close` | Close account |

### Transaction Service APIs

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/transactions` | Get all transactions |
| `GET` | `/transactions/{id}` | Get transaction by ID |
| `POST` | `/transactions/save` | Save transaction |

---

## 🗄 Database Schema

### Customer Entity

| Field | Type | Description |
|-------|------|-------------|
| `id` | Long | Primary Key |
| `nationalId` | String | Unique national ID |
| `name` | String | Customer name |
| `dateOfBirth` | Date | Date of birth |
| `email` | String | Email address |
| `phone` | String | Phone number |
| `address` | String | Physical address |
| `status` | Enum | PENDING, ACTIVE, INACTIVE |
| `createdAt` | DateTime | Creation timestamp |

### Account Entity

| Field | Type | Description |
|-------|------|-------------|
| `id` | Long | Primary Key |
| `accountNumber` | String | Unique account number |
| `balance` | BigDecimal | Current balance |
| `currency` | String | Currency code (EGP, USD, etc.) |
| `accountType` | Enum | SAVINGS, CHECKING, BUSINESS, INVESTMENT, LOAN |
| `status` | Enum | PENDING, ACTIVE, CLOSED, FROZEN |
| `customerId` | Long | Foreign key to Customer |
| `createdAt` | DateTime | Creation timestamp |
| `updatedAt` | DateTime | Last update timestamp |
| `version` | Long | Optimistic lock version |

### Transaction Entity

| Field | Type | Description |
|-------|------|-------------|
| `id` | Long | Primary Key |
| `transactionType` | Enum | DEPOSIT, WITHDRAWAL, TRANSFER |
| `amount` | BigDecimal | Transaction amount |
| `fromAccountId` | Long | Source account ID |
| `toAccountId` | Long | Destination account ID |
| `transactionDate` | DateTime | Transaction timestamp |

---

## 🧪 Testing

The project includes Bruno API test collections located in the `APIs Test/` directory.

### Using Bruno

1. Install [Bruno](https://www.usebruno.com/)
2. Open the `APIs Test/Bank` folder
3. Run the requests to test the APIs

### Test Scenarios

- **Customer Operations**: Create, read, update, delete customers
- **Account Operations**: Create accounts, deposits, withdrawals
- **Transaction Operations**: View transaction history

---

## 📁 Project Structure

```
BankSystem/
├── Eureka-Server/              # Service Discovery Server
│   └── src/main/java/bank/eurekaserver/
├── Api-Gateway/                # API Gateway Service
│   └── src/main/java/bank/apigatway/
├── Customer-Service/           # Customer Microservice
│   └── src/main/java/bank/customerservice/
│       ├── Constants/
│       ├── Controller/
│       ├── Dto/
│       ├── Entity/
│       ├── Exception/
│       ├── Mapper/
│       ├── Repository/
│       └── Service/
├── Account-Service/            # Account Microservice
│   └── src/main/java/bank/
│       ├── Constants/
│       ├── Controller/
│       ├── Dto/
│       ├── Entity/
│       ├── Exception/
│       ├── Mapper/
│       ├── OutServices/
│       ├── Repository/
│       └── Service/
├── Transaction-Service/        # Transaction Microservice
│   └── src/main/java/bank/transactionservice/
│       ├── Constants/
│       ├── Controller/
│       ├── DTOs/
│       ├── Entity/
│       ├── Exception/
│       ├── Mapper/
│       ├── OutServices/
│       ├── Repository/
│       └── Service/
└── APIs Test/                  # Bruno API Test Collections
    └── Bank/
        ├── Accounts/
        ├── Customers/
        └── Transactions/
```

---

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

### Code Style Guidelines

- Follow Java naming conventions
- Use Lombok annotations to reduce boilerplate
- Write meaningful commit messages
- Add proper exception handling
- Document APIs with OpenAPI annotations

---

## 👤 Author

**Mo'men Abdulrahman**

- GitHub: [@momen-abdulrahman](https://github.com/R00t105/)
- Email: momen.abdulrahman@outlook.com

---

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## 🙏 Acknowledgments

- Spring Boot and Spring Cloud communities
- Netflix for Eureka
- All contributors and open-source projects used in this application

---

⭐ If you find this project useful, please consider giving it a star!

