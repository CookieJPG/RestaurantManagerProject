-- Create Database
CREATE DATABASE RestaurantSystem;

GO USE RestaurantSystem;

GO
-- Customers Table (Child)
CREATE TABLE
    Customers (
        CustomerID NCHAR(12) PRIMARY KEY,
        CustomerName NVARCHAR (30),
		CustomerType NVARCHAR (10),
        Email NVARCHAR (100) UNIQUE,
        PhoneNumber NVARCHAR (20),
        LoyaltyPoints DECIMAL
    );

-- Tables
CREATE TABLE
    Mesas (
        TableID INT PRIMARY KEY IDENTITY (1, 1),
        IsAvailable BIT
    );

-- Reservations
CREATE TABLE
    Reservations (
        ReservationID INT PRIMARY KEY IDENTITY (1, 1),
        CustomerID NCHAR(12) REFERENCES Customers (CustomerID),
        TableID INT REFERENCES Mesas (TableID),
        ReservationDate DATETIME NOT NULL,
        NumberOfGuest INT NOT NULL,
        Status NVARCHAR (20) DEFAULT 'Pending',
        CONSTRAINT CK_ReservationStatus CHECK (Status IN ('Pending', 'Confirmed', 'Cancelled'))
    );

-- Orders
CREATE TABLE
    Orders (
        OrderID INT PRIMARY KEY IDENTITY (1, 1),
        TableID INT REFERENCES Mesas (TableID),
        CustomerID NCHAR(12) REFERENCES Customers (CustomerID),
        Status NVARCHAR (20) DEFAULT 'Pending',
        OrderDate DATETIME DEFAULT GETDATE (),
        CONSTRAINT CK_OrderStatus CHECK (Status IN ('Pending', 'Confirmed', 'Cancelled'))
    );

-- Items
CREATE TABLE
    Items (
        ItemID INT PRIMARY KEY IDENTITY (1, 1),
        ItemName NVARCHAR (50),
        ItemPrice DECIMAL(10, 2),
        Category NVARCHAR (20) NOT NULL DEFAULT ''
    );

-- OrderItems
CREATE TABLE
    OrderItems (
        OrderID INT,
        ItemID INT,
        Quantity INT,
        PRIMARY KEY (OrderID, ItemID),
        FOREIGN KEY (OrderID) REFERENCES Orders (OrderID),
        FOREIGN KEY (ItemID) REFERENCES Items (ItemID)
    );

-- Payments
CREATE TABLE
    Payments (
        PaymentID INT PRIMARY KEY IDENTITY (1, 1),
        OrderID INT REFERENCES Orders (OrderID),
        Amount DECIMAL(10, 2) NOT NULL,
        PaymentMethod NVARCHAR (50) NOT NULL,
        TransactionID NVARCHAR (100),
        Status NVARCHAR (20) DEFAULT 'Pending',
        PaymentTime DATETIME
    );

DROP TABLE IF EXISTS Payments;

DROP TABLE IF EXISTS Orders;

DROP TABLE IF EXISTS Reservations;

DROP TABLE IF EXISTS Mesas;

DROP TABLE IF EXISTS Customers;

DROP TABLE IF EXISTS Items;

DROP TABLE IF EXISTS OrderItems;