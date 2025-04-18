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
                    request.getRequestDispatcher("/orderDetails.jsp").forward(request, response);
                } else {
                    response.sendRedirect(request.getContextPath() + "/orders?error=not_found");
                }
            } catch (NumberFormatException e) {
                response.sendRedirect(request.getContextPath() + "/orders?error=invalid_id");
            }
        } else {
            List<Order> orders = daoOrders.getAllOrders();
            request.setAttribute("orders", orders);
            request.getRequestDispatcher("/orders.jsp").forward(request, response);
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
            // Get order fields from the request
            int tableId = Integer.parseInt(request.getParameter("tableId"));
            String customerId = request.getParameter("customerId");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String status = request.getParameter("orderStatus");
            String customerType = request.getParameter("customerType"); // Added customer type parameter

            // Create the customer based on the type
            Customer customer;
            switch (customerType) {
                case "FirstTime":
                    customer = new CTFirstTime("John Doe", email, phone);
                    break;
                case "Regular":
                    customer = new CTRegular("John Doe", email, phone);
                    break;
                case "VIP":
                    customer = new CTVIP("John Doe", email, phone);
                    break;
                default:
                    customer = new CTRegular("John Doe", email, phone); //default
                    break;
            }

            ArrayList<ISellable> orderItems = new ArrayList<>();

            //Create a new order
            Order order = new Order(
                    0,
                    tableId,
                    customer,
                    orderItems,
                    status,
                    LocalDateTime.now()
            );

            // Save the order
            if (daoOrders.SaveOrder(order)) {
                response.sendRedirect(request.getContextPath() + "/orders?orderId=" +
                        order.getId() + "&success=created");
            } else {
                response.sendRedirect(request.getContextPath() + "/orders?error=creation_failed");
            }
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/orders?error=invalid_number");
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/orders?error=server_error");
        }
    }

    private void updateOrder(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int orderId = Integer.parseInt(request.getParameter("orderId"));
            Order order = daoOrders.getOrderById(orderId);

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
            response.sendRedirect(request.getContextPath() + "/orders?orderId=" +
                    orderId + "&success=updated");
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