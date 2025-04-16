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
        // testTableDAO(daoManager);
        // testReservationDAO(daoManager);
        // testOrderDAO(daoManager);
        // testPaymentDAO(daoManager);
    }

    private static void testCustomerDAO(DAOCRUDManager daoManager) {
        System.out.println("\n===== Probando Customer DAO =====");

        // Crear clientes de prueba
        CTFirstTime customer1 = new CTFirstTime("1CUST001", "Juan Pérez", "juan@email.com", "555-1234", 0);
        CTRegular customer2 = new CTRegular("2CUST002", "María García", "maria@email.com", "555-5678", 50);
        CTVIP customer3 = new CTVIP("3CUST003", "Carlos López", "carlos@email.com", "555-9012", 200);

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

    // private static void testTableDAO(DAOCRUDManager daoManager) {
    // System.out.println("\n===== Probando Table DAO =====");

    // // Crear mesas de prueba
    // Table table1 = new Table(1, true);
    // Table table2 = new Table(2, true);
    // Table table3 = new Table(3, false);

    // // Agregar mesas
    // daoManager.addTable(table1);
    // daoManager.addTable(table2);
    // daoManager.addTable(table3);
    // System.out.println("Mesas agregadas exitosamente");

    // // Cambiar estado de una mesa
    // table2.Reserve();
    // daoManager.updateTableStatus(table2);
    // System.out.println("Estado de la mesa 2 actualizado a ocupado");

    // table2.UnReserve();
    // daoManager.updateTableStatus(table2);
    // System.out.println("Estado de la mesa 2 actualizado a disponible");
    // }

    // private static void testReservationDAO(DAOCRUDManager daoManager) {
    // System.out.println("\n===== Probando Reservation DAO =====");

    // // Crear reservaciones
    // LocalDateTime now = LocalDateTime.now();
    // Reservation res1 = new Reservation(0, "2CUST002", 1, now.plusDays(1), 4,
    // "Confirmed");
    // Reservation res2 = new Reservation(0, "3CUST003", 2, now.plusDays(2), 6,
    // "Pending");

    // // Guardar reservaciones
    // daoManager.SaveReservation(res1);
    // daoManager.SaveReservation(res2);
    // System.out.println("Reservaciones guardadas exitosamente");

    // // Listar todas las reservaciones
    // List<Reservation> allReservations = daoManager.getAllReservations();
    // System.out.println("\nTodas las reservaciones (" + allReservations.size() +
    // "):");
    // for (Reservation r : allReservations) {
    // System.out.println(" - ID: " + r.getReservationId() +
    // ", Mesa: " + r.getTableID() +
    // ", Cliente: " + r.getCustomerID() +
    // ", Fecha: " + r.getReservationDate());
    // }

    // // Eliminar una reservación
    // daoManager.RemoveReservation(res1);
    // System.out.println("\nReservación eliminada");
    // }

    // private static void testOrderDAO(DAOCRUDManager daoManager) {
    // System.out.println("\n===== Probando Order DAO =====");

    // // Crear items del menú
    // Dish mainDish = new Dish("Pasta Carbonara", "Regular", 12.99);
    // Beverage drink = new Beverage("Vino Tinto", "Glass", 8.50);
    // Dessert dessert = new Dessert("Tiramisú", "Standard", 6.75);

    // // Crear lista de items para la orden
    // ArrayList<ISellable> orderItems = new ArrayList<>();
    // orderItems.add(mainDish);
    // orderItems.add(drink);
    // orderItems.add(drink); // Dos bebidas
    // orderItems.add(dessert);

    // // Crear orden
    // Customer customer = daoManager.findCustomerById("3CUST003");
    // Order order = new Order(0, 3, customer, orderItems, "In Progress",
    // LocalDateTime.now());

    // // Guardar orden
    // daoManager.SaveOrder(order);
    // System.out.println("Orden guardada exitosamente");

    // // Obtener orden por ID
    // Order retrievedOrder = daoManager.getOrderById(order.getId());
    // System.out.println("\nOrden recuperada: " +
    // (retrievedOrder != null ? "ID " + retrievedOrder.getId() : "No encontrada"));

    // if (retrievedOrder != null) {
    // System.out.println("Items en la orden (" +
    // retrievedOrder.getOrderItems().size() + "):");
    // for (ISellable item : retrievedOrder.getOrderItems()) {
    // System.out.println(" - " + item.getName() + ": $" + item.getPrice());
    // }
    // }

    // // Listar todas las órdenes
    // List<Order> allOrders = daoManager.getAllOrders();
    // System.out.println("\nTodas las órdenes (" + allOrders.size() + "):");
    // for (Order o : allOrders) {
    // System.out.println(" - ID: " + o.getId() +
    // ", Mesa: " + o.getTableId() +
    // ", Estado: " + o.getOrderStatus());
    // }
    // }

    // private static void testPaymentDAO(DAOCRUDManager daoManager) {
    // System.out.println("\n===== Probando Payment DAO =====");

    // // Obtener una orden existente para asociar el pago
    // List<Order> orders = daoManager.getAllOrders();
    // if (orders.isEmpty()) {
    // System.out.println("No hay órdenes para procesar pago");
    // return;
    // }

    // Order order = orders.get(0);

    // // Crear pago
    // Payment payment = new Payment(0, order, 45.23, "Credit Card", "TXN12345",
    // Status.CONFIRMED,
    // LocalDateTime.now());

    // // Crear pago en la base de datos
    // Payment createdPayment = daoManager.createPayment(payment);
    // System.out.println("Pago creado con ID: " + createdPayment.getPaymentId());

    // // Leer pago
    // Payment readPayment = daoManager.readPayment(createdPayment.getPaymentId());
    // System.out.println("\nPago leído: " +
    // (readPayment != null ? "Método " + readPayment.getPaymentMethod() : "No
    // encontrado"));

    // if (readPayment != null) {
    // System.out.println("Detalles: $" + readPayment.getAmount() +
    // ", Estado: " + readPayment.getStatus());
    // }

    // // Eliminar pago
    // daoManager.deletePayment(readPayment.getPaymentId());
    // System.out.println("\nPago eliminado");

    // // Verificar puntos de lealtad del cliente
    // Customer customer = order.getCustomer();
    // System.out.println("Puntos de lealtad del cliente después del pago: " +
    // customer.getLoyaltyPoints());
    // }
}