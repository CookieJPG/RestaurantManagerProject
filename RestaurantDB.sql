-- Create Database
CREATE DATABASE RestaurantSystem;
GO

USE RestaurantSystem;
GO

-- Customers Table (Child)
CREATE TABLE Customers (
    CustomerID INT PRIMARY KEY,
    PhoneNumber NVARCHAR(20),
    Email NVARCHAR(100) UNIQUE,
    LoyaltyPoints INT DEFAULT 0
);


-- Tables
CREATE TABLE Tables (
    TableID INT PRIMARY KEY IDENTITY(1,1),
    Capacity INT NOT NULL,
    Location NVARCHAR(50),
    Status NVARCHAR(20) DEFAULT 'Available'
);

-- Reservations
CREATE TABLE Reservations (
    ReservationID INT PRIMARY KEY IDENTITY(1,1),
    CustomerID INT REFERENCES Customers(CustomerID),
    TableID INT REFERENCES Tables(TableID),
    ReservationTime DATETIME NOT NULL,
    PartySize INT NOT NULL,
    Status NVARCHAR(20) DEFAULT 'Pending',
    SpecialRequests NVARCHAR(MAX)
);

-- Orders
CREATE TABLE Orders (
    OrderID INT PRIMARY KEY IDENTITY(1,1),
    TableID INT REFERENCES Tables(TableID),
    CustomerID INT REFERENCES Customers(CustomerID),
    OrderTime DATETIME DEFAULT GETDATE(),
    Status NVARCHAR(20) DEFAULT 'Pending',
    TotalAmount DECIMAL(10,2)
);

-- Payments
CREATE TABLE Payments (
    PaymentID INT PRIMARY KEY IDENTITY(1,1),
    OrderID INT REFERENCES Orders(OrderID),
    Amount DECIMAL(10,2) NOT NULL,
    PaymentMethod NVARCHAR(50) NOT NULL,
    TransactionID NVARCHAR(100),
    Status NVARCHAR(20) DEFAULT 'Pending',
    PaymentTime DATETIME
);


