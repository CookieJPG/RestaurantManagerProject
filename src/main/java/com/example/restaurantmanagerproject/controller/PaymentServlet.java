package com.example.restaurantmanagerproject.controller;

import com.example.restaurantmanagerproject.dao.DAOCRUDManager;
import com.example.restaurantmanagerproject.dao.DAOPayments;
import com.example.restaurantmanagerproject.model.Order;
import com.example.restaurantmanagerproject.model.Payment;
import com.example.restaurantmanagerproject.model.Status;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.UUID;

@WebServlet("/payments")
public class PaymentServlet extends HttpServlet {

    private DAOPayments daoPayments = new DAOCRUDManager();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String paymentIdParam = request.getParameter("paymentId");
        if (paymentIdParam != null) {
            try {
                int paymentId = Integer.parseInt(paymentIdParam);
                Payment payment = daoPayments.readPayment(paymentId);
                if (payment != null) {
                    request.setAttribute("payment", payment);
                    request.getRequestDispatcher("/paymentDetails.jsp").forward(request, response);
                } else {
                    response.sendRedirect(request.getContextPath() + "/payments?error=not_found");
                }
            } catch (NumberFormatException e) {
                response.sendRedirect(request.getContextPath() + "/payments?error=invalid_id");
            }
        } else {
            request.getRequestDispatcher("/payments.jsp").forward(request, response);
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
                createPayment(request, response);
                break;
            case "update":
                updatePayment(request, response);
                break;
            case "delete":
                deletePayment(request, response);
                break;
            default:
                throw new ServletException("Invalid Action: " + action);
        }
    }

    private void createPayment(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int orderId = Integer.parseInt(request.getParameter("orderId"));
            Order order = new Order(orderId, 0, null, null, null, null);

            double amount = Double.parseDouble(request.getParameter("amount"));
            String paymentMethod = request.getParameter("paymentMethod");
            Status status = Status.valueOf(request.getParameter("status"));

            LocalDateTime paymentDate = request.getParameter("paymentDate") != null && !request.getParameter("paymentDate").isEmpty()
                    ? LocalDateTime.parse(request.getParameter("paymentDate"))
                    : LocalDateTime.now();

            String transactionId = request.getParameter("transactionId") != null
                    ? request.getParameter("transactionId")
                    : UUID.randomUUID().toString();

            Payment payment = new Payment(
                    0,
                    order,
                    amount,
                    paymentMethod,
                    transactionId,
                    status,
                    paymentDate
            );

            Payment createdPayment = daoPayments.createPayment(payment);
            response.sendRedirect(request.getContextPath() + "/payments?paymentId=" +
                    createdPayment.getPaymentId() + "&success=created");

        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/payments?error=invalid_number");
        } catch (IllegalArgumentException e) {
            response.sendRedirect(request.getContextPath() + "/payments?error=invalid_status");
        } catch (DateTimeParseException e) {
            response.sendRedirect(request.getContextPath() + "/payments?error=invalid_date");
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/payments?error=server_error");
        }
    }

    private void updatePayment(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int paymentId = Integer.parseInt(request.getParameter("paymentId"));
            Payment payment = daoPayments.readPayment(paymentId);

            if (payment == null) {
                response.sendRedirect(request.getContextPath() + "/payments?error=not_found");
                return;
            }

            if (request.getParameter("amount") != null) {
                payment.setAmount(Double.parseDouble(request.getParameter("amount")));
            }
            if (request.getParameter("paymentMethod") != null) {
                payment.setPaymentMethod(request.getParameter("paymentMethod"));
            }
            if (request.getParameter("status") != null) {
                payment.setStatus(Status.valueOf(request.getParameter("status")));
            }
            if (request.getParameter("transactionId") != null) {
                payment.setTransactionId(request.getParameter("transactionId"));
            }
            if (request.getParameter("paymentDate") != null && !request.getParameter("paymentDate").isEmpty()) {
                payment.setPaymentTime(LocalDateTime.parse(request.getParameter("paymentDate")));
            }

            daoPayments.createPayment(payment);
            response.sendRedirect(request.getContextPath() + "/payments?paymentId=" +
                    paymentId + "&success=updated");

        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/payments?error=invalid_number");
        } catch (IllegalArgumentException e) {
            response.sendRedirect(request.getContextPath() + "/payments?error=invalid_status");
        } catch (DateTimeParseException e) {
            response.sendRedirect(request.getContextPath() + "/payments?error=invalid_date");
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/payments?error=server_error");
        }
    }

    private void deletePayment(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            int paymentId = Integer.parseInt(request.getParameter("paymentId"));
            daoPayments.deletePayment(paymentId);
            response.sendRedirect(request.getContextPath() + "/payments?success=deleted");
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/payments?error=invalid_id");
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/payments?error=delete_error");
        }
    }
}