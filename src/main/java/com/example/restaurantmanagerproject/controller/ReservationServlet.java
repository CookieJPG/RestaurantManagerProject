package com.example.restaurantmanagerproject.controller;

import com.example.restaurantmanagerproject.dao.DAOCRUDManager;
import com.example.restaurantmanagerproject.dao.DAOReservations;
import com.example.restaurantmanagerproject.dao.DAOCustomer;
import com.example.restaurantmanagerproject.model.Customer;
import com.example.restaurantmanagerproject.model.Reservation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet("/reservations")
public class ReservationServlet extends HttpServlet {
    private DAOReservations daoReservations = new DAOCRUDManager();
    private DAOCustomer daoCustomer = new DAOCRUDManager();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Reservation> reservations = daoReservations.getAllReservations();
        request.setAttribute("reservations", reservations);
        request.getRequestDispatcher("/RestoReserv.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        if (action == null) {
            response.sendRedirect(request.getContextPath() + "/reservations?error=missing_action");
            return;
        }

        switch (action) {
            case "create":
                createReservation(request, response);
                break;
            default:
                response.sendRedirect(request.getContextPath() + "/reservations?error=invalid_action");
        }
    }

    private void createReservation(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // 1. Obtener parámetros del formulario
            String customerId = request.getParameter("customerId");

            Customer customer = daoCustomer.findCustomerById(customerId);
            if (customer == null) {
                response.sendRedirect(request.getContextPath() + "/RestoReserv.jsp?error=customer_not_found");
                return;
            }

            // 3. Obtener parámetros de fecha/hora
            int year = Integer.parseInt(request.getParameter("reservationYear"));
            int month = Integer.parseInt(request.getParameter("reservationMonth"));
            int day = Integer.parseInt(request.getParameter("reservationDay"));
            int hour = Integer.parseInt(request.getParameter("reservationHour"));
            int minute = Integer.parseInt(request.getParameter("reservationMinute"));
            String period = request.getParameter("reservationPeriod");

            // Convertir a formato 24 horas
            if ("PM".equalsIgnoreCase(period) && hour < 12) {
                hour += 12;
            } else if ("AM".equalsIgnoreCase(period) && hour == 12) {
                hour = 0;
            }

            LocalDateTime reservationDate = LocalDateTime.of(year, month, day, hour, minute);
            int tableId = Integer.parseInt(request.getParameter("tableId"));
            int numberOfGuests = Integer.parseInt(request.getParameter("numberOfGuests"));

            // 4. Crear y guardar la reservación
            Reservation reservation = new Reservation(
                    0, // ID será generado
                    customerId,
                    tableId,
                    reservationDate,
                    numberOfGuests,
                    "Pending");

            daoReservations.SaveReservation(reservation);
            response.sendRedirect(request.getContextPath() + "/RestoReserv.jsp?success=true");

        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/RestoReserv.jsp?error=invalid_number");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/RestoReserv.jsp?error=server_error");
        }
    }
}