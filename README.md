# 🏦 Bank Management System

A Java-based Bank Management System built using **Java, OOP, MySQL, and JDBC**.

## 🚀 Features

- Create a new bank account
- User login with account number and PIN
- Check account balance
- Deposit money
- Withdraw money
- Transfer money between accounts
- Transaction history
- Input validation
- JDBC transaction handling using `commit()` and `rollback()`

## 🛠️ Technologies Used

- Java
- Object-Oriented Programming (OOP)
- MySQL
- JDBC
- IntelliJ IDEA

## 🗄️ Database

The project uses a MySQL database named:

`bank_management`

Main tables:

- `accounts`
- `transactions`

## 📌 Project Structure

```text
Bank-Management-System
│
└── src
    └── bank
        ├── Main.java
        ├── AccountDAO.java
        ├── BalanceDAO.java
        ├── DepositDAO.java
        ├── LoginDAO.java
        ├── TransactionDAO.java
        ├── TransferDAO.java
        └── WithdrawDAO.java
