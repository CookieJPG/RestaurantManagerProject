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


-- Insert Main Dishes
INSERT INTO Items (ItemName, ItemPrice, Category) VALUES
('Pabellón Criollo', 18.99, 'Dish'),
('Bandeja Paisa', 19.99, 'Dish'),
('Ceviche Peruano', 16.99, 'Dish'),
('Paella Valenciana', 22.99, 'Dish');

-- Insert Beverages
INSERT INTO Items (ItemName, ItemPrice, Category) VALUES
('Aguapanela', 4.99, 'Beverage'),
('Pisco Sour', 10.99, 'Beverage'),
('Cocada', 5.99, 'Beverage'),
('Tinto de Verano', 7.99, 'Beverage');

-- Insert Desserts
INSERT INTO Items (ItemName, ItemPrice, Category) VALUES
('Alfajores', 6.99, 'Dessert'),
('Tres Leches', 7.99, 'Dessert'),
('Arroz con Leche', 5.99, 'Dessert'),
('Crema Catalana', 8.99, 'Dessert');

DROP TABLE IF EXISTS Payments;

DROP TABLE IF EXISTS Orders;

DROP TABLE IF EXISTS Reservations;

DROP TABLE IF EXISTS Mesas;

DROP TABLE IF EXISTS Customers;

DROP TABLE IF EXISTS Items;

DROP TABLE IF EXISTS OrderItems;

