package com.example.restaurantmanagerproject.controller;

import com.example.restaurantmanagerproject.dao.*;
import com.example.restaurantmanagerproject.model.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/orders")
public class OrderServlet extends HttpServlet {
    private DAOOrders daoOrders = new DAOCRUDManager();
    private DAOCustomer daoCustomer = new DAOCRUDManager();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String orderIdParam = request.getParameter("orderId");
        if (orderIdParam != null) {
            try {
                int orderId = Integer.parseInt(orderIdParam);
                Order order = daoOrders.getOrderById(orderId);
                if (order != null) {
                    request.setAttribute("order", order);
                    request.getRequestDispatcher("/RestoOrders.jsp").forward(request, response);
                } else {
                    response.sendRedirect(request.getContextPath() + "/orders?error=not_found");
                }
            } catch (NumberFormatException e) {
                response.sendRedirect(request.getContextPath() + "/orders?error=invalid_id");
            }
        } else {
            List<Order> orders = daoOrders.getAllOrders();
            request.setAttribute("orders", orders);
            request.getRequestDispatcher("/RestoOrders.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {
            throw new ServletException("Action parameter is missing");
        }

        switch (action) {
            case "create":
                createOrder(request, response);
                break;
            case "update":
                updateOrder(request, response);
                break;
            case "delete":
                deleteOrder(request, response);
                break;
            default:
                throw new ServletException("Invalid Action: " + action);
        }
    }

    private void createOrder(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // 1. Obtener parámetros básicos
            int tableId = Integer.parseInt(request.getParameter("tableId"));
            String customerId = request.getParameter("customerId");

            System.out.println("Intentando crear orden para mesa: " + tableId + ", cliente: " + customerId);

            // 2. Buscar cliente existente
            Customer customer = daoCustomer.findCustomerById(customerId);
            if (customer == null) {
                System.out.println("Cliente no encontrado: " + customerId);
                response.sendRedirect(request.getContextPath() + "/orders?error=customer_not_found");
                return;
            } else {
                System.out.println("Cliente encontrado: " + customer.getName());
            }

            // 3. Procesar items seleccionados
            String[] itemIds = request.getParameterValues("itemId");
            String[] quantities = request.getParameterValues("quantity");
            ArrayList<ISellable> orderItems = new ArrayList<>();

            if (itemIds != null && quantities != null && itemIds.length == quantities.length) {
                for (int i = 0; i < itemIds.length; i++) {
                    int quantity = Integer.parseInt(quantities[i]);
                    if (quantity > 0) {
                        int itemId = Integer.parseInt(itemIds[i]);
                        ISellable item = createItemFromId(itemId);
                        if (item != null) {
                            for (int j = 0; j < quantity; j++) {
                                orderItems.add(item);
                            }
                        }
                    }
                }
            }

            if (orderItems.isEmpty()) {
                response.sendRedirect(request.getContextPath() + "/orders?error=no_items_selected");
                return;
            }

            // 4. Crear y guardar la orden
            Order order = new Order(
                    0, // ID será generado
                    tableId,
                    customer,
                    orderItems,
                    "Pending",
                    LocalDateTime.now());

            if (daoOrders.SaveOrder(order)) {
                response.sendRedirect(
                        request.getContextPath() + "/orders?success=order_created&orderId=" + order.getId());
            } else {
                response.sendRedirect(request.getContextPath() + "/orders?error=order_creation_failed");
            }

        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/orders?error=invalid_input");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/orders?error=server_error");
        }
    }

    // Método auxiliar para crear items basados en IDs conocidos
    private ISellable createItemFromId(int itemId) {
        // Basado en los datos de RestaurantDB.sql
        switch (itemId) {
            case 1:
                return new Dish(1, "Pabellón Criollo", "Regular", 18.99);
            case 2:
                return new Dish(2, "Bandeja Paisa", "Regular", 19.99);
            case 3:
                return new Dish(3, "Ceviche Peruano", "Regular", 16.99);
            case 4:
                return new Dish(4, "Paella Valenciana", "Regular", 22.99);
            case 5:
                return new Beverage(5, "Aguapanela", "Medium", 4.99);
            case 6:
                return new Beverage(6, "Pisco Sour", "Medium", 10.99);
            case 7:
                return new Beverage(7, "Cocada", "Medium", 5.99);
            case 8:
                return new Beverage(8, "Tinto de Verano", "Medium", 7.99);
            case 9:
                return new Dessert(9, "Alfajores", "Standard", 6.99);
            case 10:
                return new Dessert(10, "Tres Leches", "Standard", 7.99);
            case 11:
                return new Dessert(11, "Arroz con Leche", "Standard", 5.99);
            case 12:
                return new Dessert(12, "Crema Catalana", "Standard", 8.99);
            default:
                return null;
        }
    }

    private void updateOrder(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int orderId = Integer.parseInt(request.getParameter("orderId"));
            Order order = daoOrders.getOrderById(orderId);
            String redirect = request.getParameter("redirect");

            if (order == null) {
                response.sendRedirect(request.getContextPath() + "/orders?error=not_found");
                return;
            }

            // Update fields from request parameters
            if (request.getParameter("tableId") != null) {
                order.setTableId(Integer.parseInt(request.getParameter("tableId")));
            }
            if (request.getParameter("orderStatus") != null) {
                order.setOrderStatus(request.getParameter("orderStatus"));
            }

            daoOrders.SaveOrder(order);

            // Redirigir a la página especificada o a la vista de detalles
            if (redirect != null && !redirect.isEmpty()) {
                response.sendRedirect(redirect + "?success=updated");
            } else {
                response.sendRedirect(request.getContextPath() + "/orders?orderId=" +
                        orderId + "&success=updated");
            }
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/orders?error=invalid_number");
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/orders?error=server_error");
        }
    }

    private void deleteOrder(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            int orderId = Integer.parseInt(request.getParameter("orderId"));
            Order order = daoOrders.getOrderById(orderId);

            if (order != null) {
                daoOrders.RemoveOrder(order);
                response.sendRedirect(request.getContextPath() + "/orders?success=deleted");
            } else {
                response.sendRedirect(request.getContextPath() + "/orders?error=not_found");
            }
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/orders?error=invalid_id");
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/orders?error=delete_error");
        }
    }
}