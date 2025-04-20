<%@page import="com.example.restaurantmanagerproject.model.Order" %>
    <%@page import="java.util.List" %>
        <%@page import="com.example.restaurantmanagerproject.dao.DAOOrders" %>
            <%@page import="com.example.restaurantmanagerproject.dao.DAOCRUDManager" %>
                <%@page import="com.example.restaurantmanagerproject.model.ISellable" %>
                    <%@page import="java.util.Map" %>
                        <%@page import="java.util.HashMap" %>

                            <!DOCTYPE html>
                            <html lang="en">

                            <head>
                                <meta charset="UTF-8">
                                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                                <title>Summary - Gourmet Delight</title>
                                <link rel="stylesheet" href="css/styles.css">
                                <link rel="stylesheet" href="css/RestoOrders.css">
                                <style>
                                    /* Global Styles */
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

                                    /* Search Container */
                                    .search-container {
                                        display: flex;
                                        justify-content: flex-end;
                                        padding: 0 5%;
                                        margin: -1.5rem auto 1.5rem;
                                        max-width: 90%;
                                    }

                                    .search-box {
                                        position: relative;
                                        width: 300px;
                                    }

                                    .search-box input {
                                        width: 100%;
                                        padding: 0.75rem 1rem;
                                        border: 1px solid #ddd;
                                        border-radius: 4px;
                                        font-family: inherit;
                                        padding-right: 40px;
                                        box-shadow: var(--shadow);
                                    }

                                    .search-box button {
                                        position: absolute;
                                        right: 0;
                                        top: 0;
                                        height: 100%;
                                        background: var(--secondary-color);
                                        color: white;
                                        border: none;
                                        border-radius: 0 4px 4px 0;
                                        padding: 0 1rem;
                                        cursor: pointer;
                                        transition: background 0.3s;
                                    }

                                    .search-box button:hover {
                                        background: #b38b4a;
                                    }

                                    /* Order Management Section */
                                    .order-section {
                                        padding: 2rem;
                                    }

                                    .section-title {
                                        color: var(--primary-color);
                                        text-align: center;
                                        margin-bottom: 2rem;
                                        font-size: 2rem;
                                    }

                                    .orders-container {
                                        background: var(--white);
                                        border-radius: 8px;
                                        padding: 2rem;
                                        box-shadow: var(--shadow);
                                        width: 90%;
                                        margin: 0 auto;
                                    }

                                    .order-card {
                                        border: 1px solid #e9ecef;
                                        border-radius: 8px;
                                        padding: 1.5rem;
                                        margin-bottom: 1.5rem;
                                        transition: transform 0.3s;
                                    }

                                    .order-card:hover {
                                        transform: translateY(-3px);
                                        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                                    }

                                    .order-header {
                                        display: flex;
                                        justify-content: space-between;
                                        margin-bottom: 1rem;
                                        border-bottom: 1px solid #eee;
                                        padding-bottom: 0.5rem;
                                    }

                                    .order-id {
                                        font-weight: bold;
                                        color: var(--primary-color);
                                    }

                                    .order-date {
                                        color: #666;
                                    }

                                    .order-status {
                                        padding: 0.25rem 0.5rem;
                                        border-radius: 4px;
                                        font-weight: bold;
                                        font-size: 0.8rem;
                                    }

                                    .status-pending {
                                        background-color: #fff3cd;
                                        color: #856404;
                                    }

                                    .status-completed {
                                        background-color: #d4edda;
                                        color: #155724;
                                    }

                                    .status-cancelled {
                                        background-color: #f8d7da;
                                        color: #721c24;
                                    }

                                    .order-details {
                                        margin-bottom: 1rem;
                                    }

                                    .order-items {
                                        width: 100%;
                                        border-collapse: collapse;
                                        margin: 1rem 0;
                                    }

                                    .order-items th,
                                    .order-items td {
                                        padding: 0.75rem;
                                        text-align: left;
                                        border-bottom: 1px solid #e9ecef;
                                    }

                                    .order-items th {
                                        background-color: var(--light-bg);
                                        color: var(--primary-color);
                                    }

                                    .order-actions {
                                        display: flex;
                                        gap: 1rem;
                                        justify-content: flex-end;
                                    }

                                    .action-btn {
                                        padding: 0.5rem 1rem;
                                        border: none;
                                        border-radius: 4px;
                                        cursor: pointer;
                                        font-weight: bold;
                                        transition: background 0.3s;
                                    }

                                    .complete-btn {
                                        background: var(--secondary-color);
                                        color: white;
                                    }

                                    .complete-btn:hover {
                                        background: #b38b4a;
                                    }

                                    .cancel-btn {
                                        background: #f8d7da;
                                        color: #721c24;
                                    }

                                    .cancel-btn:hover {
                                        background: #f1b0b7;
                                    }

                                    /* Footer */
                                    .footer {
                                        background-color: var(--primary-color);
                                        color: var(--white);
                                        text-align: center;
                                        padding: 1.5rem;
                                        margin-top: 3rem;
                                    }

                                    /* Highlight animation */
                                    @keyframes highlight {
                                        0% {
                                            background-color: rgba(212, 167, 98, 0.3);
                                        }

                                        100% {
                                            background-color: white;
                                        }
                                    }

                                    .highlight {
                                        animation: highlight 1.5s;
                                    }

                                    /* Clear search button */
                                    .clear-search {
                                        display: none;
                                        margin-left: 1rem;
                                        padding: 0.5rem 1rem;
                                        background: #f8d7da;
                                        color: #721c24;
                                        border: none;
                                        border-radius: 4px;
                                        cursor: pointer;
                                        font-weight: bold;
                                        transition: background 0.3s;
                                    }

                                    .clear-search:hover {
                                        background: #f1b0b7;
                                    }

                                    .nav-links a.active {
                                        color: var(--secondary-color);
                                        border-bottom: 2px solid var(--secondary-color);
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
                                    <h1>&#127860 Order Management</h1>
                                    <p>View and manage all customer orders in real-time</p>
                                </section>

                                <!-- Search Box -->
                                <div class="search-container">
                                    <div class="search-box">
                                        <input type="text" id="orderSearch" placeholder="Search by order # (e.g. 1001)"
                                            aria-label="Search orders by order number">
                                        <button onclick="searchOrder()" aria-label="Search">
                                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                                fill="currentColor" viewBox="0 0 16 16">
                                                <path
                                                    d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z" />
                                            </svg>
                                        </button>
                                    </div>
                                    <button class="clear-search" onclick="clearSearch()">Clear Search</button>
                                </div>

                                <!-- Order Management Section -->
                                <section class="order-section">
                                    <h1 class="section-title">Current Orders</h1>

                                    <div class="orders-container">
                                        <% DAOOrders daoOrders=new DAOCRUDManager(); List<Order> orders =
                                            daoOrders.getAllOrders();

                                            if (orders != null && !orders.isEmpty()) {
                                            for (Order order : orders) {
                                            String statusClass = "";
                                            switch (order.getOrderStatus().toLowerCase()) {
                                            case "confirmed":
                                            statusClass = "status-completed";
                                            break;
                                            case "cancelled":
                                            statusClass = "status-cancelled";
                                            break;
                                            default:
                                            statusClass = "status-pending";
                                            }
                                            %>
                                            <div class="order-card" id="order-<%= order.getId() %>">
                                                <div class="order-header">
                                                    <div>
                                                        <span class="order-id">Order #<%= order.getId() %></span>
                                                        <span class="order-date"> - <%= order.getOrderDate() %></span>
                                                    </div>
                                                    <span class="order-status <%= statusClass %>">
                                                        <%= order.getOrderStatus() %>
                                                    </span>
                                                </div>

                                                <div class="order-details">
                                                    <p><strong>Customer:</strong>
                                                        <%= order.getCustomer() !=null ? order.getCustomer().getName()
                                                            : "Walk-in Customer" %>
                                                    </p>
                                                    <p><strong>Table:</strong>
                                                        <%= order.getTableId() %>
                                                    </p>
                                                </div>

                                                <% if (order.getOrderItems() !=null && !order.getOrderItems().isEmpty())
                                                    { Map<Integer, Integer> itemQuantities = new HashMap<>();
                                                        Map<Integer, ISellable> itemDetails = new HashMap<>();
                                                                double total = 0.0;

                                                                for (ISellable item : order.getOrderItems()) {
                                                                itemQuantities.merge(item.getId(), 1, Integer::sum);
                                                                itemDetails.putIfAbsent(item.getId(), item);
                                                                total += item.getPrice();
                                                                }
                                                                %>
                                                                <table class="order-items">
                                                                    <thead>
                                                                        <tr>
                                                                            <th>Item</th>
                                                                            <th>Quantity</th>
                                                                            <th>Price</th>
                                                                        </tr>
                                                                    </thead>
                                                                    <tbody>
                                                                        <% for (Map.Entry<Integer, Integer> entry :
                                                                            itemQuantities.entrySet()) {
                                                                            ISellable item =
                                                                            itemDetails.get(entry.getKey());
                                                                            %>
                                                                            <tr>
                                                                                <td>
                                                                                    <%= item.getName() %>
                                                                                </td>
                                                                                <td>
                                                                                    <%= entry.getValue() %>
                                                                                </td>
                                                                                <td>$<%= String.format("%.2f",
                                                                                        item.getPrice() *
                                                                                        entry.getValue()) %>
                                                                                </td>
                                                                            </tr>
                                                                            <% } %>
                                                                    </tbody>
                                                                </table>

                                                                <div class="order-total">
                                                                    <p><strong>Total:</strong> $<%=
                                                                            String.format("%.2f", total) %>
                                                                    </p>
                                                                </div>
                                                                <% } %>

                                                                    <div class="order-actions">
                                                                        <form action="orders" method="post"
                                                                            style="display: inline;">
                                                                            <input type="hidden" name="action"
                                                                                value="update">
                                                                            <input type="hidden" name="orderId"
                                                                                value="<%= order.getId() %>">
                                                                            <input type="hidden" name="orderStatus"
                                                                                value="Cancelled">
                                                                            <input type="hidden" name="redirect"
                                                                                value="RestoOrders.jsp">
                                                                            <button type="submit"
                                                                                class="action-btn cancel-btn"
                                                                                <%=order.getOrderStatus().equalsIgnoreCase("Cancelled")
                                                                                ? "disabled" : "" %>>Cancel
                                                                                Order</button>
                                                                        </form>

                                                                        <form action="orders" method="post"
                                                                            style="display: inline;">
                                                                            <input type="hidden" name="action"
                                                                                value="update">
                                                                            <input type="hidden" name="orderId"
                                                                                value="<%= order.getId() %>">
                                                                            <input type="hidden" name="orderStatus"
                                                                                value="Confirmed">
                                                                            <input type="hidden" name="redirect"
                                                                                value="RestoOrders.jsp">
                                                                            <button type="submit"
                                                                                class="action-btn complete-btn"
                                                                                <%=order.getOrderStatus().equalsIgnoreCase("Confirmed")
                                                                                ? "disabled" : "" %>>Mark as
                                                                                Completed</button>
                                                                        </form>
                                                                    </div>
                                            </div>
                                            <% } } else { %>
                                                <div class="no-orders">
                                                    <p>No orders found.</p>
                                                </div>
                                                <% } %>
                                    </div>
                                </section>

                                <!-- Footer -->
                                <footer class="footer">
                                    <p>Our kitchen is open from 11 AM to 10 PM everyday</p>
                                </footer>

                                <script>
                                    function searchOrder() {
                                        const searchTerm = document.getElementById('orderSearch').value.trim().toLowerCase();
                                        const orderCards = document.querySelectorAll('.order-card');
                                        const clearSearchBtn = document.querySelector('.clear-search');
                                        let found = false;

                                        if (!searchTerm) {
                                            clearSearch();
                                            return;
                                        }

                                        orderCards.forEach(card => {
                                            const orderId = card.querySelector('.order-id').textContent.toLowerCase();
                                            if (orderId.includes(searchTerm)) {
                                                card.style.display = 'block';
                                                card.classList.add('highlight');
                                                found = true;
                                                // Scroll to the found order
                                                setTimeout(() => {
                                                    card.scrollIntoView({ behavior: 'smooth', block: 'nearest' });
                                                }, 100);
                                            } else {
                                                card.style.display = 'none';
                                                card.classList.remove('highlight');
                                            }
                                        });

                                        clearSearchBtn.style.display = found ? 'block' : 'none';

                                        if (!found) {
                                            alert('No orders found with that number');
                                            clearSearch();
                                        }
                                    }

                                    function clearSearch() {
                                        document.getElementById('orderSearch').value = '';
                                        const orderCards = document.querySelectorAll('.order-card');
                                        orderCards.forEach(card => {
                                            card.style.display = 'block';
                                            card.classList.remove('highlight');
                                        });
                                        document.querySelector('.clear-search').style.display = 'none';
                                    }

                                    // Add event listener for Enter key
                                    document.getElementById('orderSearch').addEventListener('keypress', function (e) {
                                        if (e.key === 'Enter') {
                                            searchOrder();
                                        }
                                    });

                                    // Clear search when clicking outside
                                    document.addEventListener('click', function (e) {
                                        if (!e.target.closest('.search-container')) {
                                            const searchInput = document.getElementById('orderSearch');
                                            if (searchInput.value.trim() === '') {
                                                clearSearch();
                                            }
                                        }
                                    })
                                    // Mostrar mensajes de éxito/error
                                    const urlParams = new URLSearchParams(window.location.search);
                                    const successParam = urlParams.get('success');
                                    const errorParam = urlParams.get('error');

                                    if (successParam) {
                                        alert('Operation successful: ' + successParam);
                                    }

                                    if (errorParam) {
                                        alert('Error: ' + errorParam);
                                    };
                                </script>
                            </body>

                            </html>