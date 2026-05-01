# 🧺 Laundry Order Management System

A production-style REST API built with Spring Boot for managing laundry orders with real-time status tracking.

## Tech Stack
- Java 21
- Spring Boot
- Spring Security + JWT Authentication
- PostgreSQL
- JPA / Hibernate

## Features
- User registration and login with JWT authentication
- Create and track laundry orders
- Automatic order status progression (RECEIVED → PICKED_UP → WASHING → DRYING → READY → DELIVERED)
- Protected endpoints — only authenticated users can access orders
- Global exception handling
- DTO pattern — raw entities never exposed through API

## API Endpoints

### Auth
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /auth/register | Register a new user |
| POST | /auth/login | Login and receive JWT token |

### Orders
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /orders | Create a new order |
| GET | /orders/{id} | Get order by ID |
| GET | /orders/user/{userId} | Get all orders for a user |
| PUT | /orders/{id}/status | Update order status |

## Setup
1. Clone the repo
2. Create a PostgreSQL database called `laundry_db`
3. Add your database credentials to `application.properties`
4. Run the application