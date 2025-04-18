package com.example.restaurantmanagerproject.model;

import com.example.restaurantmanagerproject.dao.DAOCRUDManager;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.example.restaurantmanagerproject.model.IRewardable.Type;

public class Main {

    public static void main(String[] args) {

        // Crear el manager de DAOs
        DAOCRUDManager daoManager = new DAOCRUDManager();

        // Probar cada DAO
        // testCustomerDAO(daoManager);
        // testTableDAO(daoManager);
        // testReservationDAO(daoManager);
        testOrderDAO(daoManager);
        // testPaymentDAO(daoManager);
    }

    private static void testCustomerDAO(DAOCRUDManager daoManager) {
        System.out.println("\n===== Probando Customer DAO =====");

        // Crear clientes de prueba
        CTFirstTime customer1 = new CTFirstTime("1CUST001", "Juan Pérez",
                "juan@email.com", "555-1234", 0);
        CTRegular customer2 = new CTRegular("2CUST002", "María García",
                "maria@email.com", "555-5678", 50.0);
        CTVIP customer3 = new CTVIP("3CUST003", "Carlos López", "carlos@email.com",
                "555-9012", 200.0);

        // Guardar clientes
        daoManager.saveCustomer(customer1);
        daoManager.saveCustomer(customer2);
        daoManager.saveCustomer(customer3);
        System.out.println("Clientes guardados exitosamente");

        // Buscar cliente por ID
        Customer foundCustomer = daoManager.findCustomerById("2CUST002");
        System.out
                .println("Cliente encontrado: " + (foundCustomer != null ? foundCustomer.getName() : "No encontrado"));

        // Listar todos los clientes
        List<Customer> allCustomers = daoManager.findAllCustomers();
        System.out.println("\nTodos los clientes (" + allCustomers.size() + "):");
        for (Customer c : allCustomers) {
            System.out.println(" - " + c.getId() + ": " + c.getName() +
                    " (" + c.getType() + "), Puntos: " + c.getLoyaltyPoints());
        }

        // Eliminar un cliente
        boolean deleted = daoManager.deleteCustomer("1CUST001");
        System.out.println("\nCliente eliminado: " + (deleted ? "Éxito" : "Fallo"));
    }

    private static void testTableDAO(DAOCRUDManager daoManager) {
        System.out.println("\n===== Probando Table DAO =====");

        // Crear mesas de prueba
        Table table1 = new Table(true);
        Table table2 = new Table(true);
        Table table3 = new Table(false);

        // Agregar mesas
        daoManager.addTable(table1);
        daoManager.addTable(table2);
        daoManager.addTable(table3);
        System.out.println("Mesas agregadas exitosamente");

        System.out.println("Mesas agregadas exitosamente con IDs:");
        System.out.println("Mesa 1: " + table1.getId());
        System.out.println("Mesa 2: " + table2.getId());
        System.out.println("Mesa 3: " + table3.getId());

        // Cambiar estado de una mesa
        table2.Reserve();
        daoManager.updateTableStatus(table2);
        System.out.println("Estado de la mesa 2 actualizado a ocupado");

        table2.UnReserve();
        daoManager.updateTableStatus(table2);
        System.out.println("Estado de la mesa 2 actualizado a disponible");
    }

    private static void testReservationDAO(DAOCRUDManager daoManager) {
        System.out.println("\n===== Probando Reservation DAO =====");

        // Crear reservaciones
        LocalDateTime now = LocalDateTime.now();
        Reservation res1 = new Reservation(0, "2CUST002", 1, now.plusDays(1), 4,
                "Confirmed");
        Reservation res2 = new Reservation(0, "3CUST003", 2, now.plusDays(2), 6,
                "Pending");

        // Guardar reservaciones
        daoManager.SaveReservation(res1);
        daoManager.SaveReservation(res2);
        System.out.println("Reservaciones guardadas exitosamente");

        // Listar todas las reservaciones
        List<Reservation> allReservations = daoManager.getAllReservations();
        System.out.println("\nTodas las reservaciones (" + allReservations.size() +
                "):");
        for (Reservation r : allReservations) {
            System.out.println(" - ID: " + r.getReservationId() +
                    ", Mesa: " + r.getTableID() +
                    ", Cliente: " + r.getCustomerID() +
                    ", Fecha: " + r.getReservationDate());
        }

        // Eliminar una reservación
        daoManager.RemoveReservation(res1);
        System.out.println("\nReservación eliminada");
    }

    private static void testOrderDAO(DAOCRUDManager daoManager) {
        System.out.println("\n===== Probando Order DAO =====");

        // Crear items del menú
        Dish mainDish = new Dish(1, "Pasta Carbonara", "Regular", 12.99);
        Beverage drink = new Beverage(1, "Vino Tinto", "Glass", 8.50);
        Dessert dessert = new Dessert(1, "Tiramisú", "Standard", 6.75);
        // Crear lista de items para la orden
        ArrayList<ISellable> orderItems = new ArrayList<>();
        orderItems.add(mainDish);
        orderItems.add(drink);
        orderItems.add(drink); // Dos bebidas
        orderItems.add(dessert);

        // Crear orden
        Customer customer = daoManager.findCustomerById("3CUST003");
        Order order = new Order(0, 3, customer, orderItems, "Pending",
                LocalDateTime.now());

        // Guardar orden
        daoManager.SaveOrder(order);
        System.out.println("Orden guardada exitosamente");

        // Obtener orden por ID // ! SOLO FALTA PODER GUARDAR LOS ITEMS EN LA ORDEN EN
        // ! LA DB
        Order retrievedOrder = daoManager.getOrderById(order.getId());
        System.out.println("\nOrden recuperada: " +
                (retrievedOrder != null ? "ID " + retrievedOrder.getId() : "No encontrada"));

        if (retrievedOrder != null) {
            System.out.println("Items en la orden (" +
                    retrievedOrder.getOrderItems().size() + "):");
            for (ISellable item : retrievedOrder.getOrderItems()) {
                System.out.println(" - " + item.getName() + ": $" + item.getPrice());
            }
        }

        // Listar todas las órdenes
        List<Order> allOrders = daoManager.getAllOrders();
        System.out.println("\nTodas las órdenes (" + allOrders.size() + "):");
        for (Order o : allOrders) {
            System.out.println(" - ID: " + o.getId() +
                    ", Mesa: " + o.getTableId() +
                    ", Cliente: " + o.getCustomer().getName() +
                    ", Estado: " + o.getOrderStatus() +
                    ", Fecha: " + o.getOrderDate());
        }
    }

    private static void testPaymentDAO(DAOCRUDManager daoManager) {
        System.out.println("\n===== Probando Payment DAO =====");

        // 1. First ensure we have a valid order to associate with payment
        System.out.println("\n--- Preparando datos de prueba ---");

        // Create test customer if doesn't exist
        Customer customer = daoManager.findCustomerById("3CUST003");
        if (customer == null) {
            customer = new CTVIP("3CUST003", "Carlos López", "carlos@email.com", "555-9012", 200.0);
            daoManager.saveCustomer(customer);
        }

        // Create test table if doesn't exist
        Table table = new Table(true);
        daoManager.addTable(table);

        // Create test order with items
        ArrayList<ISellable> orderItems = new ArrayList<>();
        orderItems.add(new Dish(1, "Pasta Carbonara", "Regular", 12.99));
        orderItems.add(new Beverage(1, "Vino Tinto", "Glass", 8.50));
        orderItems.add(new Dessert(1, "Tiramisú", "Standard", 6.75));

        Order order = new Order(0, 3, customer, orderItems, "Pending", LocalDateTime.now());
        daoManager.SaveOrder(order);
        System.out.println("Orden de prueba creada con ID: " + order.getId());

        // 2. Test Payment Creation
        System.out.println("\n--- Probando creación de pago ---");
        Payment payment = new Payment(
                0, // paymentId (0 for new payment)
                order, // associated order
                45.23, // amount
                "Credit Card", // payment method
                "TXN12345", // transaction ID
                Status.CONFIRMED, // status
                LocalDateTime.now() // payment date
        );

        // !Set points used (simulate customer using loyalty points)
        // ! payment.getPointsUsed(10);

        Payment createdPayment = daoManager.createPayment(payment);
        if (createdPayment != null) {
            System.out.println("Pago creado exitosamente con ID: " + createdPayment.getPaymentId());
            System.out.println("Detalles del pago:");
            System.out.println("- Orden ID: " + createdPayment.getOrder().getId());
            System.out.println("- Método: " + createdPayment.getPaymentMethod());
            System.out.println("- Monto: $" + createdPayment.getAmount());
            System.out.println("- Estado: " + createdPayment.getStatus());
            System.out.println("- Puntos Ganados: " + payment.getPointsGained());
        } else {
            System.out.println("Error al crear el pago");
            return;
        }

        // 3. Test Reading Payment
        System.out.println("\n--- Probando lectura de pago ---");
        Payment retrievedPayment = daoManager.readPayment(createdPayment.getPaymentId());
        if (retrievedPayment != null) {
            System.out.println("Pago recuperado exitosamente:");
            System.out.println("- ID: " + retrievedPayment.getPaymentId());
            System.out.println("- Método: " + retrievedPayment.getPaymentMethod());
            System.out.println("- Monto: $" + retrievedPayment.getAmount());
            System.out.println("- Estado: " + retrievedPayment.getStatus());
            System.out.println("- Fecha: " + retrievedPayment.getPaymentDate());

            // Verify associated order
            if (retrievedPayment.getOrder() != null) {
                System.out.println("- Orden asociada ID: " + retrievedPayment.getOrder().getId());
            } else {
                System.out.println("Advertencia: No se encontró orden asociada");
            }
        } else {
            System.out.println("Error al recuperar el pago");
        }

        // 4. Test Loyalty Points Update
        System.out.println("\n--- Probando actualización de puntos de lealtad ---");
        Customer updatedCustomer = daoManager.findCustomerById(customer.getId());
        if (updatedCustomer != null) {
            System.out.println("Puntos de lealtad actualizados:");
            System.out.println("- Puntos antes del pago: " + customer.getLoyaltyPoints());
            System.out.println("- Puntos después del pago: " + updatedCustomer.getLoyaltyPoints());

            // Expected points:
            // Original: 200
            // Used: -10 (set in payment)
            // Earned: +4 (45.23 / 10 = 4.523 → truncated to 4)
            // Expected total: 194
        } else {
            System.out.println("Error al verificar puntos de lealtad");
        }

        // 5. Test Payment Deletion
        System.out.println("\n--- Probando eliminación de pago ---");
        daoManager.deletePayment(createdPayment.getPaymentId());
        System.out.println("Pago eliminado, verificando...");

        Payment deletedPayment = daoManager.readPayment(createdPayment.getPaymentId());
        if (deletedPayment == null) {
            System.out.println("Pago eliminado exitosamente");
        } else {
            System.out.println("Error: El pago aún existe después de eliminarlo");
        }

        // 6. Cleanup (optional)
        System.out.println("\n--- Limpieza de datos de prueba ---");
        daoManager.RemoveOrder(order);
        System.out.println("Datos de prueba eliminados");
    }
}