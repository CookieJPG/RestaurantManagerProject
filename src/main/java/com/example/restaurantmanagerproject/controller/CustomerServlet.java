package com.example.restaurantmanagerproject.controller;

import com.example.restaurantmanagerproject.dao.DAOCRUDManager;
import com.example.restaurantmanagerproject.dao.DAOCustomer;
import com.example.restaurantmanagerproject.model.Customer;
import com.example.restaurantmanagerproject.model.CTFirstTime;
import com.example.restaurantmanagerproject.model.CTRegular;
import com.example.restaurantmanagerproject.model.CTVIP;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/customers")
public class CustomerServlet extends HttpServlet {

    private DAOCustomer daoCustomer = new DAOCRUDManager();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String customerId = request.getParameter("customerId");
        if (customerId != null) {
            Customer customer = daoCustomer.findCustomerById(customerId);
            if (customer != null) {
                request.setAttribute("customer", customer);
                request.getRequestDispatcher("/customerDetails.jsp").forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/customers?error=not_found");
            }
        } else {
            request.getRequestDispatcher("/RestoCustomer.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        if (action == null) {
            response.sendRedirect(request.getContextPath() + "/customers?error=missing_action");
            return;
        }

        switch (action) {
            case "create":
                createCustomer(request, response);
                break;
            case "update":
                updateCustomer(request, response);
                break;
            case "delete":
                deleteCustomer(request, response);
                break;
            default:
                response.sendRedirect(request.getContextPath() + "/customers?error=invalid_action");
        }
    }

    private void createCustomer(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String customerId = request.getParameter("customerID");
            String name = request.getParameter("customerName");
            String type = request.getParameter("customerType");
            String email = request.getParameter("email");
            String phone = request.getParameter("phoneNumber");
            double loyaltyPoints = Double.parseDouble(request.getParameter("loyaltyPoints"));

            Customer customer;
            switch (type) {
                case "FIRST":
                    customer = new CTFirstTime(customerId, name, email, phone, loyaltyPoints);
                    break;
                case "REGULAR":
                    customer = new CTRegular(customerId, name, email, phone, loyaltyPoints);
                    break;
                case "VIP":
                    customer = new CTVIP(customerId, name, email, phone, loyaltyPoints);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid customer type");
            }

            boolean success = daoCustomer.saveCustomer(customer);
            if (success) {
                response.sendRedirect(request.getContextPath() + "/customers?success=created");
            } else {
                response.sendRedirect(request.getContextPath() + "/customers?error=creation_failed");
            }
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/customers?error=invalid_number");
        } catch (IllegalArgumentException e) {
            response.sendRedirect(request.getContextPath() + "/customers?error=invalid_type");
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/customers?error=server_error");
        }
    }

    private void updateCustomer(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String customerId = request.getParameter("customerID");
            Customer existingCustomer = daoCustomer.findCustomerById(customerId);

            if (existingCustomer == null) {
                response.sendRedirect(request.getContextPath() + "/customers?error=not_found");
                return;
            }

            if (request.getParameter("customerName") != null) {
                existingCustomer.setName(request.getParameter("customerName"));
            }
            if (request.getParameter("email") != null) {
                existingCustomer.setEmail(request.getParameter("email"));
            }
            if (request.getParameter("phoneNumber") != null) {
                existingCustomer.setPhone(request.getParameter("phoneNumber"));
            }
            if (request.getParameter("loyaltyPoints") != null) {
                existingCustomer.setLoyaltyPoints(Double.parseDouble(request.getParameter("loyaltyPoints")));
            }

            boolean success = daoCustomer.saveCustomer(existingCustomer);
            if (success) {
                response.sendRedirect(request.getContextPath() + "/customers?success=updated");
            } else {
                response.sendRedirect(request.getContextPath() + "/customers?error=update_failed");
            }
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/customers?error=invalid_number");
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/customers?error=server_error");
        }
    }

    private void deleteCustomer(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            String customerId = request.getParameter("customerId");
            boolean success = daoCustomer.deleteCustomer(customerId);
            if (success) {
                response.sendRedirect(request.getContextPath() + "/customers?success=deleted");
            } else {
                response.sendRedirect(request.getContextPath() + "/customers?error=delete_failed");
            }
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/customers?error=server_error");
        }
    }
}