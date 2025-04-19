package com.example.restaurantmanagerproject.dao;

import com.example.restaurantmanagerproject.Db.DbUtil;
import com.example.restaurantmanagerproject.model.Beverage;
import com.example.restaurantmanagerproject.model.CTFirstTime;
import com.example.restaurantmanagerproject.model.CTRegular;
import com.example.restaurantmanagerproject.model.CTVIP;
import com.example.restaurantmanagerproject.model.Customer;
import com.example.restaurantmanagerproject.model.Dessert;
import com.example.restaurantmanagerproject.model.Dish;
import com.example.restaurantmanagerproject.model.IRewardable;
import com.example.restaurantmanagerproject.model.ISellable;
import com.example.restaurantmanagerproject.model.Order;
import com.example.restaurantmanagerproject.model.Payment;
import com.example.restaurantmanagerproject.model.Reservation;
import com.example.restaurantmanagerproject.model.Status;
import com.example.restaurantmanagerproject.model.Table;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.LocalDateTime;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Objects;

public class DAOCRUDManager implements DAOReservations, DAOOrders, DAOPayments, DAOTable, DAOCustomer {

    // Orders

    @Override // * Funcionando
    public Order getOrderById(int orderId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        PreparedStatement stmtItems = null;
        ResultSet rsItems = null;
        PreparedStatement stmtCustomer = null;
        ResultSet rsCustomer = null;

        try {
            conn = DbUtil.getConnection();

            // Obtener datos básicos del pedido
            stmt = conn.prepareStatement("SELECT * FROM Orders WHERE OrderID = ?");
            stmt.setInt(1, orderId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                int id = orderId;
                int tableId = rs.getInt("TableID");
                String customerId = rs.getString("CustomerID");
                String status = rs.getString("Status");
                LocalDateTime orderDate = rs.getTimestamp("OrderDate").toLocalDateTime();

                // Obtener datos del cliente
                Customer customer = null;
                if (customerId != null) {
                    stmtCustomer = conn.prepareStatement("SELECT * FROM Customers WHERE CustomerID = ?");
                    stmtCustomer.setString(1, customerId);
                    rsCustomer = stmtCustomer.executeQuery();

                    if (rsCustomer.next()) {
                        String name = rsCustomer.getString("CustomerName");
                        String email = rsCustomer.getString("Email");
                        String phone = rsCustomer.getString("PhoneNumber");
                        double loyaltyPoints = rsCustomer.getDouble("LoyaltyPoints");

                        // Determinar el tipo de cliente
                        char typePrefix = customerId.charAt(0);
                        switch (typePrefix) {
                            case '1':
                                customer = new CTFirstTime(customerId, name, email, phone, loyaltyPoints);
                                break;
                            case '2':
                                customer = new CTRegular(customerId, name, email, phone, loyaltyPoints);
                                break;
                            case '3':
                                customer = new CTVIP(customerId, name, email, phone, loyaltyPoints);
                                break;
                            default:
                                throw new IllegalArgumentException("Tipo de cliente desconocido: " + typePrefix);
                        }
                    }
                }

                // Obtener items del pedido con su categoría
                stmtItems = conn
                        .prepareStatement("SELECT oi.ItemID, oi.Quantity, i.ItemName, i.ItemPrice, i.Category " +
                                "FROM OrderItems oi JOIN Items i ON oi.ItemID = i.ItemID " +
                                "WHERE oi.OrderID = ?");
                stmtItems.setInt(1, orderId);
                rsItems = stmtItems.executeQuery();

                ArrayList<ISellable> orderItems = new ArrayList<>();
                while (rsItems.next()) {
                    int itemId = rsItems.getInt("ItemID");
                    int quantity = rsItems.getInt("Quantity");
                    String itemName = rsItems.getString("ItemName");
                    double itemPrice = rsItems.getDouble("ItemPrice");
                    String category = rsItems.getString("Category");

                    ISellable item = createSellableItem(itemId, itemName, itemPrice, category);
                    for (int i = 0; i < quantity; i++) {
                        orderItems.add(item);
                    }
                }
                return new Order(id, tableId, customer, orderItems, status, orderDate);
            }
            return null;
        } catch (SQLException e) {
            System.err.println("Error finding order: " + e.getMessage());
            return null;
        } finally {
            DbUtil.closeQuietly(rsCustomer);
            DbUtil.closeQuietly(stmtCustomer);
            DbUtil.closeQuietly(rsItems);
            DbUtil.closeQuietly(stmtItems);
            DbUtil.closeQuietly(rs);
            DbUtil.closeQuietly(stmt);
            DbUtil.closeQuietly(conn);
        }

        // throw new UnsupportedOperationException("Unimplemented method
        // 'RemoveReservation'");
    }

    // Método auxiliar para crear items según su categoría
    private ISellable createSellableItem(int id, String name, double price,
            String category) {
        switch (category.toLowerCase()) {
            case "dessert":
                return new Dessert(id, name, "Standard", price); // Asume tamaño estándar
            case "dish":
                return new Dish(id, name, "Regular", price); // Asume tamaño regular
            case "beverage":
                return new Beverage(id, name, "Medium", price); // Asume tamaño mediano
            default:
                // Throw an exception or handle the error as needed
                return null; // Optionally return null or throw an exception
        }
    }

    @Override // * Funcionando
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmtCustomer = null;
        ResultSet rsCustomer = null;
        try {
            conn = DbUtil.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Orders");

            while (rs.next()) {
                int orderId = rs.getInt("OrderID");
                int tableId = rs.getInt("TableID");
                String customerId = rs.getString("CustomerID");
                String status = rs.getString("Status");
                LocalDateTime orderDate = rs.getTimestamp("OrderDate").toLocalDateTime();

                // Obtener datos del cliente
                Customer customer = null;
                if (customerId != null) {
                    stmtCustomer = conn.prepareStatement("SELECT * FROM Customers WHERE CustomerID = ?");
                    stmtCustomer.setString(1, customerId);
                    rsCustomer = stmtCustomer.executeQuery();

                    if (rsCustomer.next()) {
                        String name = rsCustomer.getString("CustomerName");
                        String email = rsCustomer.getString("Email");
                        String phone = rsCustomer.getString("PhoneNumber");
                        double loyaltyPoints = rsCustomer.getDouble("LoyaltyPoints");

                        // Determinar el tipo de cliente
                        char typePrefix = customerId.charAt(0);
                        switch (typePrefix) {
                            case '1':
                                customer = new CTFirstTime(customerId, name, email, phone, loyaltyPoints);
                                break;
                            case '2':
                                customer = new CTRegular(customerId, name, email, phone, loyaltyPoints);
                                break;
                            case '3':
                                customer = new CTVIP(customerId, name, email, phone, loyaltyPoints);
                                break;
                            default:
                                throw new IllegalArgumentException("Tipo de cliente desconocido: " + typePrefix);
                        }
                    }
                }

                orders.add(new Order(orderId, tableId, customer, null, status, orderDate));
            }

        } catch (SQLException e) {
            System.err.println("Error getting all orders: " + e.getMessage());
        } finally {
            DbUtil.closeQuietly(conn);
        }
        return orders;
    }

    @Override // * Funcionando
    public boolean SaveOrder(Order order) {
        Connection conn = null;
        ResultSet generatedKeys = null;
        boolean success = false;

        try {
            conn = DbUtil.getConnection();
            conn.setAutoCommit(false); // Begin transaction

            if (order.getId() == 0) {
                // Insert new order
                PreparedStatement stmt = conn.prepareStatement(
                        "INSERT INTO Orders (TableID, CustomerID, Status, OrderDate) VALUES (?, ?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS);
                stmt.setInt(1, order.getTableId());
                stmt.setString(2, order.getCustomer().getId());
                stmt.setString(3, order.getOrderStatus());
                stmt.setTimestamp(4, Timestamp.valueOf(order.getOrderDate()));

                int affectedRows = stmt.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("Creating order failed, no rows affected.");
                }

                generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    order.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating order failed, no ID obtained.");
                }
            } else {
                // Update existing order
                PreparedStatement stmt = conn.prepareStatement(
                        "UPDATE Orders SET TableID = ?, CustomerID = ?, Status = ? WHERE OrderID = ?");
                stmt.setInt(1, order.getTableId());
                stmt.setString(2, order.getCustomer() != null ? order.getCustomer().getId() : null);
                stmt.setString(3, order.getOrderStatus());
                stmt.setInt(4, order.getId());
                stmt.executeUpdate();

                // Delete old items
                PreparedStatement deleteStmt = conn.prepareStatement(
                        "DELETE FROM OrderItems WHERE OrderID = ?");
                deleteStmt.setInt(1, order.getId());
                deleteStmt.executeUpdate();
            }

            // Save associated items
            if (order.getOrderItems() != null && !order.getOrderItems().isEmpty()) {
                // Group items by ID and count quantities
                Map<Integer, Integer> itemQuantities = new HashMap<>();
                for (ISellable item : order.getOrderItems()) {
                    itemQuantities.merge(item.getId(), 1, Integer::sum);
                }

                // Insert items in batch
                PreparedStatement itemStmt = conn.prepareStatement(
                        "INSERT INTO OrderItems (OrderID, ItemID, Quantity) VALUES (?, ?, ?)");

                for (Map.Entry<Integer, Integer> entry : itemQuantities.entrySet()) {
                    itemStmt.setInt(1, order.getId());
                    itemStmt.setInt(2, entry.getKey());
                    itemStmt.setInt(3, entry.getValue());
                    itemStmt.addBatch();
                }

                int[] batchResults = itemStmt.executeBatch();
                for (int result : batchResults) {
                    if (result == PreparedStatement.EXECUTE_FAILED) {
                        throw new SQLException("Failed to insert some order items");
                    }
                }
            }

            conn.commit();
            success = true;

        } catch (SQLException e) {
            System.err.println("Error saving order: " + e.getMessage());
            try {
                if (conn != null)
                    conn.rollback();
            } catch (SQLException ex) {
                System.err.println("Error on rollback: " + ex.getMessage());
            }
        } finally {
            DbUtil.closeQuietly(generatedKeys);
            try {
                if (conn != null)
                    conn.setAutoCommit(true);
            } catch (SQLException e) {
                System.err.println("Error resetting auto-commit: " + e.getMessage());
            }
            DbUtil.closeQuietly(conn);
        }

        return success;
    }

    @Override // * Funcionando
    public void RemoveOrder(Order order) {
        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            conn.setAutoCommit(false);

            // 1. Eliminar pagos asociados
            PreparedStatement deletePayments = conn.prepareStatement(
                    "DELETE FROM Payments WHERE OrderID = ?");
            deletePayments.setInt(1, order.getId());
            deletePayments.executeUpdate();

            // 2. Eliminar items de la orden
            PreparedStatement deleteItems = conn.prepareStatement(
                    "DELETE FROM OrderItems WHERE OrderID = ?");
            deleteItems.setInt(1, order.getId());
            deleteItems.executeUpdate();

            // 3. Eliminar la orden
            PreparedStatement deleteOrder = conn.prepareStatement(
                    "DELETE FROM Orders WHERE OrderID = ?");
            deleteOrder.setInt(1, order.getId());
            deleteOrder.executeUpdate();

            conn.commit();
        } catch (SQLException e) {
            System.err.println("Error removing order: " + e.getMessage());
            try {
                if (conn != null)
                    conn.rollback();
            } catch (SQLException ex) {
                System.err.println("Error on rollback: " + ex.getMessage());
            }
        } finally {
            try {
                if (conn != null)
                    conn.setAutoCommit(true);
            } catch (SQLException e) {
                System.err.println("Error resetting auto-commit: " + e.getMessage());
            }
            DbUtil.closeQuietly(conn);
        }
    }

    // Reservations
    @Override // * Funcionando
    public void SaveReservation(Reservation reservation) {
        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            if (reservation.getReservationId() == 0) {
                // Insert new reservation
                PreparedStatement stmt = conn.prepareStatement(
                        "INSERT INTO Reservations (TableID, CustomerID, ReservationDate, NumberOfGuest, Status) " +
                                "VALUES (?, ?, ?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS);
                stmt.setInt(1, reservation.getTableID());
                stmt.setString(2, reservation.getCustomerID());
                stmt.setTimestamp(3, Timestamp.valueOf(reservation.getReservationDate()));
                stmt.setInt(4, reservation.getNumberOfGuests());
                stmt.setString(5, reservation.getStatus());

                int affectedRows = stmt.executeUpdate();
                if (affectedRows > 0) {
                    try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            reservation.setReservationId(generatedKeys.getInt(1));
                        }
                    }
                }
            } else {
                // Update existing reservation
                PreparedStatement stmt = conn.prepareStatement(
                        "UPDATE Reservations SET TableID = ?,  CustomerID = ?, ReservationDate = ?, " +
                                "NumberOfGuest = ?, Status = ? WHERE ReservationID = ?");
                stmt.setInt(1, reservation.getTableID());
                stmt.setString(2, reservation.getCustomerID());
                stmt.setTimestamp(3, Timestamp.valueOf(reservation.getReservationDate()));
                stmt.setInt(4, reservation.getNumberOfGuests());
                stmt.setString(5, reservation.getStatus());
                stmt.setInt(6, reservation.getReservationId());

                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println("Error saving reservation: " + e.getMessage());
        } finally {
            DbUtil.closeQuietly(conn);
        }
    }

    @Override // * Funcionando
    public void RemoveReservation(Reservation reservation) {
        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(
                    "DELETE FROM Reservations WHERE ReservationID = ?");
            stmt.setInt(1, reservation.getReservationId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error removing reservation: " + e.getMessage());
        } finally {
            DbUtil.closeQuietly(conn);
        }
    }

    @Override // * Funcionando
    public List<Reservation> getAllReservations() {
        List<Reservation> reservations = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Reservations");

            while (rs.next()) {
                int reservationId = rs.getInt("ReservationID");
                int tableId = rs.getInt("TableID");
                String customerId = rs.getString("CustomerID");
                LocalDateTime reservationDate = rs.getTimestamp("ReservationDate").toLocalDateTime();
                int numberOfGuests = rs.getInt("NumberOfGuest");
                String status = rs.getString("Status");

                reservations.add(new Reservation(reservationId, customerId, tableId,
                        reservationDate, numberOfGuests, status));
            }
        } catch (SQLException e) {
            System.err.println("Error getting all reservations: " + e.getMessage());
        } finally {
            DbUtil.closeQuietly(conn);
        }
        return reservations;
    }

    // Tables
    @Override // * Funcionando
    public void addTable(Table table) {
        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO Mesas(IsAvailable) VALUES (?)",
                    Statement.RETURN_GENERATED_KEYS);
            stmt.setBoolean(1, table.isAvailable());
            stmt.executeUpdate();

            // Obtener el ID generado
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                table.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            System.err.println("Error adding table: " + e.getMessage());
        } finally {
            DbUtil.closeQuietly(conn);
        }
    }

    @Override // * Funcionando
    public void updateTableStatus(Table table) {
        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(
                    "UPDATE Mesas SET IsAvailable = ? WHERE TableID = ?");
            stmt.setBoolean(1, table.isAvailable());
            stmt.setInt(2, table.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating table status: " + e.getMessage());
        } finally {
            DbUtil.closeQuietly(conn);
        }
    }

    // Payments
    @Override // * Funcionando a medias porque no se si el date vaya a guardar en la DB
    public Payment createPayment(Payment payment) {
        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            // Insertar el pago
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO Payments (OrderID, Amount, PaymentMethod, TransactionID, Status, PaymentTime) " +
                            "VALUES (?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            stmt.setInt(1, payment.getOrder().getId());
            stmt.setDouble(2, payment.getAmount());
            stmt.setString(3, payment.getPaymentMethod());
            stmt.setString(4, payment.getTransactionId());
            stmt.setString(5, payment.getStatus().toString());
            stmt.setTimestamp(6, Timestamp.valueOf(payment.getPaymentDate()));

            int result = stmt.executeUpdate();

            if (result > 0) {
                // Obtener el ID generado
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        payment.setPaymentId(generatedKeys.getInt(1));
                    }
                }
                // Actualizar puntos de lealtad
                updateCustomerLoyaltyPoints(payment);
            }

            // 2. Actualizar el estado de la orden SOLO si el pago es CONFIRMED
            if (payment.getStatus() == Status.CONFIRMED) {
                PreparedStatement orderStmt = conn.prepareStatement(
                        "UPDATE Orders SET Status = 'Confirmed' WHERE OrderID = ?");
                orderStmt.setInt(1, payment.getOrder().getId());

                int orderResult = orderStmt.executeUpdate();
                if (orderResult == 0) {
                    conn.rollback();
                    System.err.println("No se pudo actualizar el estado de la orden");
                    return null;
                }
            }
            return payment;

        } catch (SQLException e) {
            System.err.println("Error creating payment: " + e.getMessage());
            return null;
        } finally {
            DbUtil.closeQuietly(conn);
        }
    }

    @Override // * Funcionando
    public Payment readPayment(int paymentId) {
        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT p.*, o.OrderID as order_id, o.CustomerID, c.CustomerName, c.LoyaltyPoints " +
                            "FROM Payments p " +
                            "LEFT JOIN Orders o ON p.OrderID = o.OrderID " +
                            "LEFT JOIN Customers c ON o.CustomerID = c.CustomerID " +
                            "WHERE p.PaymentID = ?");
            stmt.setInt(1, paymentId);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // Obtener la orden asociada al pago
                int orderId = rs.getInt("Order_ID");
                Order order = getOrderById(orderId);

                // VALIDACIÓN: Verificar si la orden existe
                if (order == null) {
                    System.err.println("Warning: No se encontró la orden con ID " + orderId +
                            " asociada al pago " + paymentId);
                    return null; // O podrías lanzar una excepción según tus necesidades
                }

                // Obtener y convertir el estado del pago usando switch case
                String statusStr = rs.getString("Status");
                Status status;

                // Convertir el string a enum Status usando switch
                switch (statusStr != null ? statusStr.toUpperCase() : "PENDING") {
                    case "CONFIRMED":
                        status = Status.CONFIRMED;
                        break;
                    case "CANCELLED":
                        status = Status.CANCELLED;
                        break;
                    case "PENDING":
                    default:
                        status = Status.PENDING;
                        break;
                }

                // Obtener la fecha de pago
                Timestamp paymentTimestamp = rs.getTimestamp("PaymentTime");
                LocalDateTime paymentDate = paymentTimestamp != null ? paymentTimestamp.toLocalDateTime()
                        : LocalDateTime.now();

                // Crear el objeto Payment
                Payment payment = new Payment(
                        rs.getInt("PaymentID"),
                        order,
                        rs.getDouble("Amount"),
                        rs.getString("PaymentMethod"),
                        rs.getString("TransactionID"),
                        status,
                        paymentDate);

                return payment;
            }
            return null;
        } catch (SQLException e) {
            System.err.println("Error reading payment: " + e.getMessage());
            return null;
        } finally {
            DbUtil.closeQuietly(conn);
        }
    }

    @Override // * Funcionando
    public void deletePayment(int paymentId) {
        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            // Eliminar el pago
            PreparedStatement stmt = conn.prepareStatement(
                    "DELETE FROM Payments WHERE PaymentID = ?");
            stmt.setInt(1, paymentId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting payment: " + e.getMessage());
        } finally {
            DbUtil.closeQuietly(conn);
        }
    }

    public List<Payment> getAllPayments() {
        List<Payment> payments = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Payments");

            while (rs.next()) {
                int paymentId = rs.getInt("PaymentID");
                int orderId = rs.getInt("OrderID");
                double amount = rs.getDouble("Amount");
                String paymentMethod = rs.getString("PaymentMethod");
                String transactionId = rs.getString("TransactionID");
                String statusStr = rs.getString("Status");
                LocalDateTime paymentTime = rs.getTimestamp("PaymentTime").toLocalDateTime();

                Status status;
                switch (statusStr != null ? statusStr.toUpperCase() : "PENDING") {
                    case "CONFIRMED":
                        status = Status.CONFIRMED;
                        break;
                    case "CANCELLED":
                        status = Status.CANCELLED;
                        break;
                    default:
                        status = Status.PENDING;
                }

                Order order = getOrderById(orderId);
                if (order == null) {
                    order = new Order(orderId, 0, null, null, null, null);
                }

                Payment payment = new Payment(
                        paymentId,
                        order,
                        amount,
                        paymentMethod,
                        transactionId,
                        status,
                        paymentTime
                );

                payments.add(payment);
            }
        } catch (SQLException e) {
            System.err.println("Error getting all payments: " + e.getMessage());
        } finally {
            DbUtil.closeQuietly(conn);
        }
        return payments;
    }

    // Actualizar puntos de lealtad del cliente despues de un pago // * Funcionando
    private void updateCustomerLoyaltyPoints(Payment payment) {
        if (payment.getCustomer() == null)
            return;

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            Customer customer = payment.getCustomer();

            // 1. Agregar puntos ganados
            int pointsEarned = payment.getPointsGained();
            if (pointsEarned > 0) {
                PreparedStatement stmt = conn.prepareStatement(
                        "UPDATE Customers SET LoyaltyPoints = LoyaltyPoints + ? WHERE CustomerID = ?");
                stmt.setInt(1, pointsEarned);
                stmt.setString(2, customer.getId());
                stmt.executeUpdate();

                customer.addLoyaltyPoints(pointsEarned);
            }

            // 2. Restar puntos usados (si aplica)
            if (Objects.equals(payment.getPaymentMethod(), "Loyalty Points")) {
                PreparedStatement stmt = conn.prepareStatement(
                        "UPDATE Customers SET LoyaltyPoints = LoyaltyPoints - ? WHERE CustomerID = ?");
                stmt.setInt(1, (int) payment.getAmount() * 1000);
                stmt.setString(2, customer.getId());
                stmt.executeUpdate();

                customer.removeLoyaltyPoints(payment.getPointsGained());
            }
        } catch (SQLException e) {
            System.err.println("Error updating loyalty points: " + e.getMessage());
        } finally {
            DbUtil.closeQuietly(conn);
        }
    }

    // Customers

    // * SOLUCION A LA EXCEPCION DE CUSTOMER NO INSTANCIABLE
    private Customer createCustomerInstance(String id, String name, IRewardable.Type type, String email, String phone,
            double loyaltyPoints) {
        switch (type) {
            case FIRST:
                return new CTFirstTime(id, name, email, phone, loyaltyPoints);
            case REGULAR:
                return new CTRegular(id, name, email, phone, loyaltyPoints);
            case VIP:
                return new CTVIP(id, name, email, phone, loyaltyPoints);
            default:
                throw new IllegalArgumentException("Tipo de cliente desconocido: " + type);
        }
    }

    @Override // * Funcionando
    public Customer findCustomerById(String id) {
        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Customers WHERE CustomerID = ?");
            stmt.setString(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String name = rs.getString("CustomerName");
                String typeStr = rs.getString("CustomerType");
                IRewardable.Type type = null;
                try {
                    type = Customer.Type.valueOf(typeStr.toUpperCase());
                } catch (IllegalArgumentException e) {
                    // Handle unknown types gracefully
                    System.err.println("Unknown customer type: " + typeStr);
                    type = Customer.Type.FIRST; // Default value
                }
                String email = rs.getString("Email");
                String phone = rs.getString("PhoneNumber");
                double loyaltyPoints = rs.getDouble("LoyaltyPoints");

                // Customer customer = new Customer(name, type, email, phone);
                // customer.addLoyaltyPoints(loyaltyPoints);
                // return customer;
                return createCustomerInstance(id, name, type, email, phone, loyaltyPoints);
            }
            return null;
        } catch (SQLException e) {
            System.err.println("Error finding customer: " + e.getMessage());
            return null;
        } finally {
            DbUtil.closeQuietly(conn);
        }
    }

    @Override // * Funcionando
    public List<Customer> findAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Customers");

            while (rs.next()) {
                String id = rs.getString("CustomerID");
                String name = rs.getString("CustomerName");
                String typeStr = rs.getString("CustomerType");
                IRewardable.Type type = null;
                try {
                    type = Customer.Type.valueOf(typeStr.toUpperCase());
                } catch (IllegalArgumentException e) {
                    // Handle unknown types gracefully
                    System.err.println("Unknown customer type: " + typeStr);
                    type = Customer.Type.FIRST; // Default value
                }
                String email = rs.getString("Email");
                String phone = rs.getString("PhoneNumber");
                double loyaltyPoints = rs.getDouble("LoyaltyPoints");

                // Customer customer = new Customer(id, name, type, email, phone,
                // loyaltyPoints);
                // customer.addLoyaltyPoints(loyaltyPoints);
                // customers.add(customer);

                customers.add(createCustomerInstance(id, name, type, email, phone,
                        loyaltyPoints));
            }
        } catch (SQLException e) {
            System.err.println("Error finding all customers: " + e.getMessage());
        } finally {
            DbUtil.closeQuietly(conn);
        }
        return customers;
    }

    @Override // * Funcionando
    public boolean saveCustomer(Customer customer) {
        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            // Check if customer exists
            PreparedStatement checkStmt = conn.prepareStatement("SELECT COUNT(*) FROM Customers WHERE CustomerID = ?");
            checkStmt.setString(1, customer.getId());
            ResultSet rs = checkStmt.executeQuery();
            rs.next();
            int count = rs.getInt(1);

            PreparedStatement stmt;
            if (count > 0) {
                // Update existing customer
                stmt = conn.prepareStatement(
                        "UPDATE Customers SET CustomerName = ?, CustomerType = ?, Email = ?, PhoneNumber = ?, LoyaltyPoints = ? WHERE CustomerID = ?");
                stmt.setString(1, customer.getName());
                String type = customer.getId().charAt(0) == '1' ? "FIRST"
                        : customer.getId().charAt(0) == '2' ? "REGULAR" : "VIP";
                stmt.setString(2, type);
                stmt.setString(3, customer.getEmail());
                stmt.setString(4, customer.getPhone());
                stmt.setDouble(5, customer.getLoyaltyPoints());
                stmt.setString(6, customer.getId());
            } else {
                // Insert new customer
                stmt = conn.prepareStatement(
                        "INSERT INTO Customers (CustomerID, CustomerName, CustomerType, Email, PhoneNumber, LoyaltyPoints) VALUES (?, ?, ?, ?, ?, ?)");
                stmt.setString(1, customer.getId());
                stmt.setString(2, customer.getName());
                stmt.setString(3, customer.getType().toString());
                stmt.setString(4, customer.getEmail());
                stmt.setString(5, customer.getPhone());
                stmt.setDouble(6, customer.getLoyaltyPoints());
            }

            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            System.err.println("Error saving customer: " + e.getMessage());
            return false;
        } finally {
            DbUtil.closeQuietly(conn);
        }
    }

    @Override // * Funcionando
    public boolean deleteCustomer(String id) {
        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM Customers WHERE CustomerID = ?");
            stmt.setString(1, id);
            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting customer: " + e.getMessage());
            return false;
        } finally {
            DbUtil.closeQuietly(conn);
        }
    }

}
