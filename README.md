# Bank Management System (Hibernate + Maven)

This is a console-based **Bank Management System** built using **Java**, **Hibernate**, and **Maven**. The system allows users to create and manage bank accounts, perform transactions, and offers an admin interface for oversight.

## Features
- **Admin**: View all accounts, transactions, and manage user details.
- **Users**: 
  - Open a bank account.
  - Deposit, withdraw, transfer funds.
  - Check account balance.

## Technologies
- **Java**: Business logic.
- **Hibernate**: ORM for database interaction.
- **MySQL**: Database.
- **Maven**: Dependency management.

## Setup

### Prerequisites
- Java JDK 8+
- Maven 3+
- MySQL

### Steps

1. **Clone the repository**:
   ```bash
   git clone https://github.com/yourusername/bank-management-system.git
   ```
2. **Setup MySQL**:
   ```sql
   CREATE DATABASE bankmanagement_system;
   ```
   Create tables using the provided SQL script.

3. **Configure Hibernate**: Update `hibernate.cfg.xml` with your database details.

4. **Build**:
   ```bash
   mvn clean install
   ```

5. **Run**:
   ```bash
   java -jar target/bank-management-system-1.0-SNAPSHOT.jar
   ```

## Key Classes
- **AccountService**: Manages account creation and updates.
- **AccountManagerService**: Handles transactions like deposits, withdrawals, and transfers.
- **AdminService**: Admin functions to oversee accounts and transactions.

---

This version provides a clear overview without too many details. Let me know if you'd like further adjustments!
