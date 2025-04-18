package com.example.restaurantmanagerproject.controller;

import com.example.restaurantmanagerproject.dao.DAOCRUDManager;
import com.example.restaurantmanagerproject.dao.DAOReservations;
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

    private DAOReservations daoReservations = new DAOCRUDManager(); // assuming DAO implementation

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String reservationIdParam = request.getParameter("reservationId");
        if (reservationIdParam != null) {
            try {
                int reservationId = Integer.parseInt(reservationIdParam);
                Reservation reservation = daoReservations.getAllReservations()  // Modify as needed
                        .stream()
                        .filter(r -> r.getReservationId() == reservationId)
                        .findFirst()
                        .orElse(null);

                if (reservation != null) {
                    request.setAttribute("reservation", reservation);
                    request.getRequestDispatcher("/reservationDetails.jsp").forward(request, response);
                } else {
                    response.sendRedirect(request.getContextPath() + "/reservations?error=not_found");
                }
            } catch (NumberFormatException e) {
                response.sendRedirect(request.getContextPath() + "/reservations?error=invalid_id");
            }
        } else {
            List<Reservation> reservations = daoReservations.getAllReservations();
            request.setAttribute("reservations", reservations);
            request.getRequestDispatcher("/reservations.jsp").forward(request, response);
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
                createReservation(request, response);
                break;
            case "update":
                updateReservation(request, response);
                break;
            case "delete":
                deleteReservation(request, response);
                break;
            default:
                throw new ServletException("Invalid Action: " + action);
        }
    }

    private void createReservation(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            //Get reservation
            int tableId = Integer.parseInt(request.getParameter("tableId"));
            String customerId = request.getParameter("customerId");
            int numberOfGuests = Integer.parseInt(request.getParameter("numberOfGuests"));
            String status = request.getParameter("status");

            //Parse reservation date only if it is provided, or just take current time
            LocalDateTime reservationDate = request.getParameter("reservationDate") != null ?
                    LocalDateTime.parse(request.getParameter("reservationDate")) :
                    LocalDateTime.now();

            //Create new reservation
            Reservation reservation = new Reservation(
                    0,
                    customerId,
                    tableId,
                    reservationDate,
                    numberOfGuests,
                    status
            );

            daoReservations.SaveReservation(reservation);
            response.sendRedirect(request.getContextPath() + "/reservations?reservationId=" +
                    reservation.getReservationId() + "&success=created");
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/reservations?error=invalid_number");
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/reservations?error=server_error");
        }
    }

    private void updateReservation(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int reservationId = Integer.parseInt(request.getParameter("reservationId"));
            Reservation reservation = daoReservations.getAllReservations()  // Modify as needed
                    .stream()
                    .filter(r -> r.getReservationId() == reservationId)
                    .findFirst()
                    .orElse(null);

            if (reservation == null) {
                response.sendRedirect(request.getContextPath() + "/reservations?error=not_found");
                return;
            }

            //Update fields from request parameters
            if (request.getParameter("tableId") != null) {
                reservation.setTableID(Integer.parseInt(request.getParameter("tableId")));
            }
            if (request.getParameter("customerId") != null) {
                reservation.setCustomerID(request.getParameter("customerId"));
            }
            if (request.getParameter("numberOfGuests") != null) {
                reservation.setNumberOfGuests(Integer.parseInt(request.getParameter("numberOfGuests")));
            }
            if (request.getParameter("status") != null) {
                reservation.setStatus(request.getParameter("status"));
            }
            if (request.getParameter("reservationDate") != null) {
                reservation.setReservationDate(LocalDateTime.parse(request.getParameter("reservationDate")));
            }

            daoReservations.SaveReservation(reservation);
            response.sendRedirect(request.getContextPath() + "/reservations?reservationId=" +
                    reservationId + "&success=updated");
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/reservations?error=invalid_number");
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/reservations?error=server_error");
        }
    }

    private void deleteReservation(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            int reservationId = Integer.parseInt(request.getParameter("reservationId"));
            Reservation reservation = daoReservations.getAllReservations()
                    .stream()
                    .filter(r -> r.getReservationId() == reservationId)
                    .findFirst()
                    .orElse(null);

            if (reservation != null) {
                daoReservations.RemoveReservation(reservation);
                response.sendRedirect(request.getContextPath() + "/reservations?success=deleted");
            } else {
                response.sendRedirect(request.getContextPath() + "/reservations?error=not_found");
            }
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/reservations?error=invalid_id");
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/reservations?error=delete_error");
        }
    }
}