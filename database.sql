CREATE DATABASE bank_management;

USE bank_management;

CREATE TABLE accounts (
    account_number INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    pin VARCHAR(10) NOT NULL,
    balance DECIMAL(10,2) DEFAULT 0.00
);

CREATE TABLE transactions (
    transaction_id INT AUTO_INCREMENT PRIMARY KEY,
    account_number INT,
    type VARCHAR(20),
    amount DECIMAL(10,2),
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (account_number)
    REFERENCES accounts(account_number)
);
