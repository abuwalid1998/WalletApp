# Wallet App

![wallet-money-cartoon](https://github.com/user-attachments/assets/3818ae7a-06c7-4dc3-aad2-d7f0acc3b0a1)

## Description

This project is a full-stack application that allows users to manage their Ethereum and USD wallets. Users can perform operations such as buying and selling Ethereum, and transaction logs are maintained for each user. The backend is built using Spring Boot, and the frontend is developed using Vue.js.

### Key Features
- User registration and authentication
- Main Wallet with two SubWallets: Ethereum and USD
- Buy and sell Ethereum functionality
- Transaction logging and history
- RESTful APIs for wallet operations
- Secure password handling with encryption

## Technologies Used

### Backend
- **Spring Boot**: Framework used for building the backend API.
- **Hibernate**: ORM tool for handling database operations.
- **MySQL**: Database for storing user, wallet, and transaction information.
- **Spring Security**: For securing user authentication and authorization.
- **Spring Data JPA**: For data persistence and repository management.

### Frontend
- **Vue.js**: Framework used for building the frontend application.
- **Vue Router**: For handling navigation between different views.
- **Axios**: For making HTTP requests to the backend API.
- **Bootstrap**: For responsive and modern UI design.

## Getting Started

### Prerequisites
- Java 21
- Node.js
- MySQL

### Backend Setup

1. Clone the repository:
    ```sh
    git clone https://github.com/your-username/project-name.git
    ```
2. Navigate to the backend directory:
    ```sh
    cd project-name/backend
    ```
3. Configure the database connection in `application.properties`:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/your-database
    spring.datasource.username=your-username
    spring.datasource.password=your-password
    spring.jpa.hibernate.ddl-auto=update
    ```
4. Build and run the backend application:
    ```sh
    ./mvnw spring-boot:run
    ```

### Frontend Setup

1. Navigate to the frontend directory:
    ```sh
    cd project-name/frontend
    ```
2. Install dependencies:
    ```sh
    npm install
    ```
3. Run the frontend application:
    ```sh
    npm run serve
    ```

### API Endpoints

- **POST** `/api/auth/register`: Register a new user
- **POST** `/api/auth/login`: Login a user
- **POST** `/api/wallet/buyEthereum`: Buy Ethereum
- **POST** `/api/wallet/sellEthereum`: Sell Ethereum
- **GET** `/api/transactions`: Get transaction logs

## Contributors

- Amjad Khaliliah

