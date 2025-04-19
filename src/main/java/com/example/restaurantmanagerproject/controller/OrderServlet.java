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

            int tableId = Integer.parseInt(request.getParameter("tableId"));

            //Get fields
            String customerId = request.getParameter("customerId");
            customerId = (customerId != null && !customerId.trim().isEmpty()) ? customerId.trim() : null;

            String customerName = request.getParameter("customerName");
            customerName = (customerName != null && !customerName.trim().isEmpty()) ? customerName.trim() : null;

            String email = request.getParameter("email");
            email = (email != null && !email.trim().isEmpty()) ? email.trim() : null;

            String phone = request.getParameter("phone");
            phone = (phone != null && !phone.trim().isEmpty()) ? phone.trim() : null;

            String status = request.getParameter("orderStatus");
            status = (status != null && !status.trim().isEmpty()) ? status.trim() : null;

            String customerType = request.getParameter("customerType");
            customerType = (customerType != null && !customerType.trim().isEmpty()) ? customerType.trim() : null;

            // Try to find a matching customer
            List<Customer> allCustomers = daoCustomer.findAllCustomers();
            Customer matchedCustomer = null;

            for (Customer c : allCustomers) {
                if ((customerId != null && c.getId().equals(customerId)) ||
                        (email != null && email.equalsIgnoreCase(c.getEmail())) ||
                        (phone != null && phone.equals(c.getPhone()))) {
                    matchedCustomer = c;
                    break;
                }
            }

            //Create new customer if ID is not found
            if (matchedCustomer == null) {
                if (customerName == null) {
                    response.sendRedirect(request.getContextPath() + "/orders?error=missing_name");
                    return;
                }

                switch (customerType != null ? customerType : "Regular") {
                    case "FirstTime":
                        matchedCustomer = new CTFirstTime(customerName, email, phone);
                        break;
                    case "Regular":
                        matchedCustomer = new CTRegular(customerName, email, phone);
                        break;
                    case "VIP":
                        matchedCustomer = new CTVIP(customerName, email, phone);
                        break;
                    default:
                        matchedCustomer = new CTRegular(customerName, email, phone);
                        break;
                }

                daoCustomer.saveCustomer(matchedCustomer);
            }

            //Once we get the customer, prepare the order
            ArrayList<ISellable> orderItems = new ArrayList<>();
            Order order = new Order(
                    0,
                    tableId,
                    matchedCustomer,
                    orderItems,
                    status,
                    LocalDateTime.now()
            );

            // Save and redirect
            if (daoOrders.SaveOrder(order)) {
                response.sendRedirect(request.getContextPath() + "/orders?orderId=" +
                        order.getId() + "&success=created");
            } else {
                response.sendRedirect(request.getContextPath() + "/orders?error=creation_failed");
            }

        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/orders?error=invalid_number");
        } catch (Exception e) {
            e.printStackTrace();
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