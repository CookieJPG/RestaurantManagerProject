package com.example.restaurantmanagerproject.model;

import com.example.restaurantmanagerproject.dao.DAOCRUDManager;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        // Crear el manager de DAOs
        DAOCRUDManager daoManager = new DAOCRUDManager();

        // Probar cada DAO
        testCustomerDAO(daoManager);
        testTableDAO(daoManager);
        testReservationDAO(daoManager);
        testOrderDAO(daoManager);
        testPaymentDAO(daoManager);
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
        Dish mainDish = new Dish(1, "Pasta Carbonara", "Regular", 12.99); // ID=1 debe existir en DB
        Beverage drink = new Beverage(5, "Vino Tinto", "Glass", 8.50); // ID=5 (bebida en tu DB)
        Dessert dessert = new Dessert(9, "Tiramisú", "Standard", 6.75); // ID=9 (postre en tu DB)
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

        // Obtener orden por ID
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

        // 1. Usar una orden existente específica (cambia el 5 por un ID real de tu BD)
        Order existingOrder = daoManager.getOrderById(2);
        if (existingOrder == null) {
            System.out.println("Error: No existe una orden con ID 5");
            return;
        }
        int existingOrderId = existingOrder.getId();
        System.out.println("\n--- Buscando orden existente ID: " + existingOrderId + " ---");

        // Verify customer exists
        Customer customer = daoManager.findCustomerById("3CUST003");
        if (customer == null) {
            System.out.println("Error: Customer with ID 3CUST003 not found");
            return;
        }

        Order order = daoManager.getOrderById(existingOrderId);
        if (order == null) {
            System.out.println("Error: No existe una orden con ID " + existingOrderId);
            return;
        }

        // 2. Verificar items y calcular total
        System.out.println("\nDetalles de la orden:");
        System.out.println("- Mesa: " + order.getTableId());
        System.out.println("- Cliente: " + order.getCustomer().getName());
        System.out.println("- Estado: " + order.getOrderStatus());

        System.out.println("\nItems en la orden:");
        if (order.getOrderItems().isEmpty()) {
            System.out.println("ADVERTENCIA: La orden no tiene items asociados");
        } else {
            for (ISellable item : order.getOrderItems()) {
                System.out.println("- " + item.getName() + ": $" + item.getPrice());
            }
        }

        // Mostrar subtotal y descuento
        double subtotal = order.calculateSubtotal();
        double discount = order.getDiscountAmount();
        double orderTotal = order.calculateTotal();

        System.out.println("\n--- Resumen de pago ---");
        System.out.printf("Subtotal: $%.2f%n", subtotal);

        if (discount > 0) {
            System.out.printf("Descuento (%s - %.1f%%): -$%.2f%n",
                    order.getCustomer().getType().toString(),
                    order.getCustomer().DiscountRate(),
                    discount);
        } else {
            System.out.println("Descuento: No aplica");
        }

        System.out.printf("Total a pagar: $%.2f%n", orderTotal);

        // 3. Generar el pago
        System.out.println("\n--- Generando pago ---");
        Payment payment = new Payment(
                0, // ID temporal
                order,
                orderTotal,
                "Credit Card",
                "TXN-" + System.currentTimeMillis(),
                Status.CONFIRMED,
                LocalDateTime.now());

        // Capture initial points before payment
        double initialPoints = customer.getLoyaltyPoints();
        System.out.printf("- Initial loyalty points: %.1f\n", initialPoints);

        // Crear el pago
        Payment createdPayment = daoManager.createPayment(payment);
        if (createdPayment == null) {
            System.out.println("Error al crear el pago");
            return;
        } else {
            System.out.println("\nPayment created successfully with ID: " + createdPayment.getPaymentId());
            System.out.println("Payment details:");
            System.out.println("- Order ID: " + createdPayment.getOrder().getId());
            System.out.println("- Method: " + createdPayment.getPaymentMethod());
            System.out.printf("- Amount: $%.2f\n", createdPayment.getAmount());
            System.out.println("- Status: " + createdPayment.getStatus());
            System.out.println("- Points Gained: " + createdPayment.getPointsGained());
        }

        // Actualizar puntos (debería hacerse automáticamente en createPayment)
        Customer updatedCustomer = daoManager.findCustomerById(customer.getId());
        System.out.println("- Puntos actuales: " +
                (updatedCustomer != null ? updatedCustomer.getLoyaltyPoints() : "Error al consultar"));

        // 4. Test Payment Reading
        System.out.println("\n--- Testing payment retrieval ---");
        Payment retrievedPayment = daoManager.readPayment(createdPayment.getPaymentId());
        if (retrievedPayment != null) {
            System.out.println("Payment retrieved successfully:");
            System.out.println("- ID: " + retrievedPayment.getPaymentId());
            System.out.println("- Method: " + retrievedPayment.getPaymentMethod());
            System.out.printf("- Amount: $%.2f\n", retrievedPayment.getAmount());
            System.out.println("- Status: " + retrievedPayment.getStatus());
            System.out.println("- Date: " + retrievedPayment.getPaymentDate());

            if (retrievedPayment.getOrder() != null) {
                System.out.println("- Associated Order ID: " + retrievedPayment.getOrder().getId());
            } else {
                System.out.println("Warning: No associated order found");
            }
        } else {
            System.out.println("Error retrieving payment");
        }

        // 5. Test Payment Deletion
        // System.out.println("\n--- Probando eliminación de pago ---");
        // daoManager.deletePayment(createdPayment.getPaymentId());
        // System.out.println("Pago eliminado, verificando...");

        // Payment deletedPayment =
        // daoManager.readPayment(createdPayment.getPaymentId());
        // if (deletedPayment == null) {
        // System.out.println("Pago eliminado exitosamente");
        // } else {
        // System.out.println("Error: El pago aún existe después de eliminarlo");
        // }

        // 6. Cleanup (optional)
        // System.out.println("\n--- Limpieza de datos de prueba ---");
        // daoManager.RemoveOrder(order);
        // System.out.println("Datos de prueba eliminados");
    }
}