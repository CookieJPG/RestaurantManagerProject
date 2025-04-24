<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reservations - Gourmet Delight</title>
    <style>
        :root {
            --primary-color: #5a3921;
            --secondary-color: #d4a762;
            --light-bg: #f8f4e8;
            --white: #ffffff;
            --text-color: #333;
            --shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        body {
            font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen, Ubuntu, Cantarell, sans-serif;
            line-height: 1.6;
            color: var(--text-color);
            max-width: 1200px;
            margin: 0 auto;
            padding: 0;
            background-color: #fff9f2;
        }

        /* Navigation Bar */
        .navbar {
            background-color: var(--primary-color);
            padding: 1rem 2rem;
            display: flex;
            justify-content: space-between;
            align-items: center;
            box-shadow: var(--shadow);
        }

        .navbar-brand {
            color: var(--white);
            font-size: 1.5rem;
            font-weight: bold;
            text-decoration: none;
        }

        .nav-links {
            display: flex;
            gap: 1.5rem;
        }

        .nav-links a {
            color: var(--white);
            text-decoration: none;
            font-weight: 500;
            transition: color 0.3s;
        }

        .nav-links a:hover {
            color: var(--secondary-color);
        }

        /* Hero Section */
        .hero {
            background: linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)), url('https://images.unsplash.com/photo-1517248135467-4c7edcad34c4');
            background-size: cover;
            background-position: center;
            color: white;
            padding: 5rem 2rem;
            text-align: center;
            margin-bottom: 2rem;
        }

        .hero h1 {
            color: white;
            font-size: 3rem;
            margin-bottom: 1rem;
        }

        .hero p {
            font-size: 1.2rem;
            max-width: 700px;
            margin: 0 auto;
        }

        /* Reservation Section */
        .reservation-section {
            padding: 2rem;
        }

        .section-title {
            color: var(--primary-color);
            text-align: center;
            margin-bottom: 2rem;
            font-size: 2rem;
        }

        .reservation-container {
            background: var(--white);
            border-radius: 8px;
            padding: 2rem;
            box-shadow: var(--shadow);
            width: 90%;
            margin: 0 auto;
        }

        /* Reservation Form */
        .reservation-form {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 1.5rem;
            max-width: 800px;
            margin: 0 auto;
        }

        .form-group {
            margin-bottom: 1.5rem;
        }

        .form-group.full-width {
            grid-column: span 2;
        }

        .form-group label {
            display: block;
            margin-bottom: 0.5rem;
            font-weight: 500;
            color: var(--primary-color);
        }

        .form-control {
            width: 100%;
            padding: 0.75rem;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-family: inherit;
            transition: border 0.3s;
        }

        .form-control:focus {
            outline: none;
            border-color: var(--secondary-color);
            box-shadow: 0 0 0 2px rgba(212, 167, 98, 0.2);
        }

        select.form-control {
            appearance: none;
            background-image: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='16' height='16' fill='%235a3921' viewBox='0 0 16 16'%3E%3Cpath d='M7.247 11.14 2.451 5.658C1.885 5.013 2.345 4 3.204 4h9.592a1 1 0 0 1 .753 1.659l-4.796 5.48a1 1 0 0 1-1.506 0z'/%3E%3C/svg%3E");
            background-repeat: no-repeat;
            background-position: right 0.75rem center;
            background-size: 16px 12px;
        }

        .date-time-group {
            display: grid;
            grid-template-columns: 1fr 1fr 1fr;
            gap: 1rem;
            grid-column: span 2;
        }

        .time-group {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 1rem;
            grid-column: span 2;
        }

        .btn {
            padding: 0.75rem 1.5rem;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-weight: bold;
            transition: background 0.3s;
        }

        .btn-primary {
            background: var(--secondary-color);
            color: white;
        }

        .btn-primary:hover {
            background: #b38b4a;
        }

        /* Status Badges */
        .status-badge {
            padding: 0.25rem 0.5rem;
            border-radius: 4px;
            font-weight: bold;
            font-size: 0.8rem;
            display: inline-block;
        }

        .status-pending {
            background-color: #fff3cd;
            color: #856404;
        }

        .status-confirmed {
            background-color: #d4edda;
            color: #155724;
        }

        .status-cancelled {
            background-color: #f8d7da;
            color: #721c24;
        }

        /* Footer */
        .footer {
            background-color: var(--primary-color);
            color: var(--white);
            text-align: center;
            padding: 1.5rem;
            margin-top: 3rem;
        }

        .date-time-group {
            display: grid;
            grid-template-columns: 1fr 1fr 1fr;
            gap: 1rem;
            grid-column: span 2;
        }

        .time-group {
            display: grid;
            grid-template-columns: 1fr 1fr 1fr;
            gap: 1rem;
            grid-column: span 2;
        }

        .nav-links a.active {
            color: var(--secondary-color);
            border-bottom: 2px solid var(--secondary-color);
        }

        /* Styles for reservation actions */
        .reservation-actions {
            display: flex;
            gap: 1rem;
            justify-content: flex-end;
            margin-top: 1rem;
        }

        .action-btn {
            padding: 0.5rem 1rem;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-weight: bold;
            transition: background 0.3s;
        }

        .confirm-btn {
            background: var(--secondary-color);
            color: white;
        }

        .confirm-btn:hover {
            background: #b38b4a;
        }

        .cancel-btn {
            background: #f8d7da;
            color: #721c24;
        }

        .cancel-btn:hover {
            background: #f1b0b7;
        }

        .records-table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 1rem;
        }

        .records-table th,
        .records-table td {
            padding: 1rem;
            text-align: left;
            border-bottom: 1px solid #e9ecef;
        }

        .records-table th {
            background-color: var(--light-bg);
            color: var(--primary-color);
            font-weight: 600;
        }

        .records-table tr:hover {
            background-color: #f9f9f9;
        }

        .action-cell {
            white-space: nowrap;
        }
    </style>
</head>

<body>
    <!-- Navigation Bar -->
    <nav class="navbar">
        <a href="index.jsp" class="navbar-brand">Gourmet Delight</a>
        <div class="nav-links">
            <a href="index.jsp">MENU</a>
            <a href="RestoCreateOrder.jsp">ORDERS</a>
            <a href="RestoOrders.jsp">SUMMARY</a>
            <a href="RestoPay.jsp">PAYMENTS</a>
            <a href="RestoReserv.jsp">RESERVATIONS</a>
            <a href="RestoCustomer.jsp">CUSTOMER</a>
        </div>
    </nav>

    <!-- Hero Section -->
    <section class="hero">
        <h1>&#127860 Table Reservations</h1>
        <p>Book your table for an unforgettable dining experience</p>
    </section>

    <!-- Reservation Section -->
    <section class="reservation-section">
        <h1 class="section-title">Make a Reservation</h1>

        <div class="reservation-container">
            <form id="reservationForm" class="reservation-form" action="reservations" method="POST">
                <input type="hidden" name="action" value="create">

                <!-- Customer Information -->
                <div class="form-group full-width">
                    <h2 style="color: var(--primary-color); margin-bottom: 1rem;">Your Information</h2>
                </div>

                <div class="form-group">
                    <label for="customerName">Full Name</label>
                    <input type="text" id="customerName" name="customerName" class="form-control" required>
                </div>

                <div class="form-group">
                    <label for="customerPhone">Phone Number</label>
                    <input type="tel" id="customerPhone" name="customerPhone" class="form-control" required>
                </div>

                <div class="form-group">
                    <label for="customerEmail">Email Address</label>
                    <input type="email" id="customerEmail" name="customerEmail" class="form-control" required>
                </div>

                <div class="form-group">
                    <label for="customerId">Customer ID (if known)</label>
                    <input type="text" id="customerId" name="customerId" class="form-control" placeholder="Optional"
                        maxlength="12">
                </div>

                <!-- Reservation Details -->
                <div class="form-group full-width">
                    <h2 style="color: var(--primary-color); margin: 1.5rem 0 1rem;">Reservation Details</h2>
                </div>

                <!-- Date Input -->
                <div class="date-time-group">
                    <div class="form-group">
                        <label for="reservationYear">Year</label>
                        <select id="reservationYear" name="reservationYear" class="form-control" required>
                            <option value="">Year</option>
                            <option value="2023">2023</option>
                            <option value="2024">2024</option>
                            <option value="2025">2025</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="reservationMonth">Month</label>
                        <select id="reservationMonth" name="reservationMonth" class="form-control" required>
                            <option value="">Month</option>
                            <option value="1">January</option>
                            <option value="2">February</option>
                            <option value="3">March</option>
                            <option value="4">April</option>
                            <option value="5">May</option>
                            <option value="6">June</option>
                            <option value="7">July</option>
                            <option value="8">August</option>
                            <option value="9">September</option>
                            <option value="10">October</option>
                            <option value="11">November</option>
                            <option value="12">December</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="reservationDay">Day</label>
                        <input type="number" id="reservationDay" name="reservationDay" class="form-control" min="1"
                            max="31" placeholder="Day" required>
                    </div>
                </div>

                <!-- Time Input -->
                <div class="time-group">
                    <div class="form-group">
                        <label for="reservationHour">Hour</label>
                        <select id="reservationHour" name="reservationHour" class="form-control" required>
                            <option value="">Hour</option>
                            <option value="11">11</option>
                            <option value="12">12</option>
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                            <option value="5">5</option>
                            <option value="6">6</option>
                            <option value="7">7</option>
                            <option value="8">8</option>
                            <option value="9">9</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="reservationMinute">Minute</label>
                        <select id="reservationMinute" name="reservationMinute" class="form-control" required>
                            <option value="">Minute</option>
                            <option value="0">00</option>
                            <option value="15">15</option>
                            <option value="30">30</option>
                            <option value="45">45</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="reservationPeriod">AM/PM</label>
                        <select id="reservationPeriod" name="reservationPeriod" class="form-control" required>
                            <option value="">AM/PM</option>
                            <option value="AM">AM</option>
                            <option value="PM">PM</option>
                        </select>
                    </div>
                </div>

                <!-- Table and Guest Selection -->
                <div class="form-group">
                    <label for="tableId">Table Preference</label>
                    <select id="tableId" name="tableId" class="form-control" required>
                        <option value="">Select table</option>
                        <option value="1">Table 1 (2 seats)</option>
                        <option value="2">Table 2 (2 seats)</option>
                        <option value="3">Table 3 (4 seats)</option>
                        <option value="4">Table 4 (4 seats)</option>
                        <option value="5">Table 5 (6 seats)</option>
                        <option value="6">Table 6 (6 seats)</option>
                        <option value="7">Table 7 (8 seats)</option>
                        <option value="8">Table 8 (Private Room)</option>
                    </select>
                </div>

                <div class="form-group">
                    <label for="numberOfGuests">Number of Guests</label>
                    <input type="number" id="numberOfGuests" name="numberOfGuests" class="form-control" min="1" max="12"
                        required>
                </div>

                <div class="form-group full-width">
                    <button type="submit" class="btn btn-primary">Book Reservation</button>
                </div>
            </form>

            <!-- Reservations Table -->
            <div class="reservation-records" style="margin-top: 3rem;">
                <h2
                    style="color: var(--primary-color); margin-bottom: 1.5rem; font-size: 1.5rem; border-bottom: 1px solid #eee; padding-bottom: 0.5rem;">
                    Current Reservations</h2>
                <table class="records-table">
                    <thead>
                        <tr>
                            <th>Reservation ID</th>
                            <th>Customer</th>
                            <th>Table</th>
                            <th>Date & Time</th>
                            <th>Guests</th>
                            <th>Status</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%@ page import="com.example.restaurantmanagerproject.dao.DAOCRUDManager" %>
                            <%@ page import="com.example.restaurantmanagerproject.model.Reservation" %>
                                <%@ page import="java.util.List" %>
                                    <%@ page import="java.time.format.DateTimeFormatter" %>
                                        <% DAOCRUDManager dao=new DAOCRUDManager(); List<Reservation> reservations =
                                            dao.getAllReservations();
                                            String pattern = "MMM dd, yyyy hh:mm a";
                                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

                                            for (Reservation reservation : reservations) {
                                            %>
                                            <tr>
                                                <td>#<%= reservation.getReservationId() %>
                                                </td>
                                                <td>
                                                    <%= reservation.getCustomerID() %>
                                                </td>
                                                <td>Table <%= reservation.getTableID() %>
                                                </td>
                                                <td>
                                                    <%= reservation.getReservationDate().format(formatter) %>
                                                </td>
                                                <td>
                                                    <%= reservation.getNumberOfGuests() %>
                                                </td>
                                                <td>
                                                    <span class="status-badge 
                                        <%= reservation.getStatus().equalsIgnoreCase(" Confirmed") ? "status-confirmed"
                                                        : reservation.getStatus().equalsIgnoreCase("Cancelled")
                                                        ? "status-cancelled" : "status-pending" %>">
                                                        <%= reservation.getStatus() %>
                                                    </span>
                                                </td>
                                                <td class="action-cell">
                                                    <form action="reservations" method="post" style="display: inline;">
                                                        <input type="hidden" name="action" value="update">
                                                        <input type="hidden" name="reservationId"
                                                            value="<%= reservation.getReservationId() %>">
                                                        <input type="hidden" name="status" value="Confirmed">
                                                        <button type="submit" class="action-btn confirm-btn"
                                                            <%=reservation.getStatus().equalsIgnoreCase("Confirmed")
                                                            ? "disabled" : "" %>>
                                                            Confirm
                                                        </button>
                                                    </form>
                                                    <form action="reservations" method="post" style="display: inline;">
                                                        <input type="hidden" name="action" value="update">
                                                        <input type="hidden" name="reservationId"
                                                            value="<%= reservation.getReservationId() %>">
                                                        <input type="hidden" name="status" value="Cancelled">
                                                        <button type="submit" class="action-btn cancel-btn"
                                                            <%=reservation.getStatus().equalsIgnoreCase("Cancelled")
                                                            ? "disabled" : "" %>>
                                                            Cancel
                                                        </button>
                                                    </form>
                                                </td>
                                            </tr>
                                            <% } %>
                    </tbody>
                </table>
            </div>
        </div>
    </section>

    <!-- Footer -->
    <footer class="footer">
        <p>Our restaurant is open from 11 AM to 10 PM everyday</p>
        <p>For large parties or special events, please call (555) 123-4567</p>
    </footer>

    <script>
        // Add event listeners for month/year changes
        document.getElementById('reservationYear').addEventListener('change', updateDays);
        document.getElementById('reservationMonth').addEventListener('change', updateDays);

        // Form submission
        document.getElementById('reservationForm').addEventListener('submit', function (e) {
            e.preventDefault();

            // Validate form fields
            const customerId = document.getElementById('customerId').value;
            const year = document.getElementById('reservationYear').value;
            const month = document.getElementById('reservationMonth').value;
            const day = document.getElementById('reservationDay').value;
            const hour = document.getElementById('reservationHour').value;
            const minute = document.getElementById('reservationMinute').value;
            const period = document.getElementById('reservationPeriod').value;
            const tableId = document.getElementById('tableId').value;
            const numberOfGuests = document.getElementById('numberOfGuests').value;

            // Basic validation
            if (!customerName || !customerPhone || !customerEmail || !customerId ||
                !year || !month || !day || !hour || !minute || !period ||
                !tableId || !numberOfGuests) {
                alert('Please fill in all required fields');
                return;
            }

            // If all validations pass, submit the form
            this.submit();
        });

        // Function to update days based on month/year selection
        function updateDays() {
            const year = document.getElementById('reservationYear').value;
            const month = document.getElementById('reservationMonth').value;
            const dayInput = document.getElementById('reservationDay');

            if (!year || !month) return;

            const daysInMonth = new Date(year, month, 0).getDate();
            const currentDay = dayInput.value;

            dayInput.max = daysInMonth;

            if (currentDay > daysInMonth) {
                dayInput.value = daysInMonth;
            }
        }

        // Verificar si hay un parámetro de éxito en la URL
        const urlParams = new URLSearchParams(window.location.search);
        const successParam = urlParams.get('success');

        if (successParam === 'true') {
            alert('¡Reservación guardada correctamente!');
            // Opcional: limpiar el parámetro de la URL
            window.history.replaceState({}, document.title, window.location.pathname);
        }

        // También puedes verificar errores si quieres
        const errorParam = urlParams.get('error');
        if (errorParam) {
            alert('Error al guardar la reservación: ' + errorParam);
            window.history.replaceState({}, document.title, window.location.pathname);
        }

        // Mostrar mensajes de éxito/error para actualizaciones de estado
        const updateSuccess = urlParams.get('updateSuccess');
        const updateError = urlParams.get('updateError');

        if (updateSuccess) {
            alert('Reservation status updated successfully: ' + updateSuccess);
            window.history.replaceState({}, document.title, window.location.pathname);
        }

        if (updateError) {
            alert('Error updating reservation status: ' + updateError);
            window.history.replaceState({}, document.title, window.location.pathname);
        }
    </script>
</body>

</html>