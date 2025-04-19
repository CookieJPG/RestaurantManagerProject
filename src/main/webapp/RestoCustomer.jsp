<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer Management | Gourmet Delight</title>
    <style>
        /* Global Styles */
        :root {
            --primary-color: #5a3921;
            --secondary-color: #d4a762;
            --light-bg: #f8f4e8;
            --white: #ffffff;
            --text-color: #333;
            --shadow: 0 2px 4px rgba(0,0,0,0.1);
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
            background: linear-gradient(rgba(0,0,0,0.5), rgba(0,0,0,0.5)), url('https://images.unsplash.com/photo-1517248135467-4c7edcad34c4');
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

        /* Form Section */
        .form-section {
            padding: 2rem;
            max-width: 800px;
            margin: 0 auto;
        }

        .section-title {
            color: var(--primary-color);
            text-align: center;
            margin-bottom: 2rem;
            font-size: 2rem;
        }

        .customer-form {
            background: var(--white);
            padding: 2rem;
            border-radius: 8px;
            box-shadow: var(--shadow);
        }

        .form-group {
            margin-bottom: 1.5rem;
        }

        .form-group label {
            display: block;
            margin-bottom: 0.5rem;
            color: var(--primary-color);
            font-weight: 500;
        }

        .form-group input,
        .form-group select {
            width: 100%;
            padding: 0.75rem;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 1rem;
        }

        .form-submit {
            background: var(--secondary-color);
            color: white;
            border: none;
            padding: 0.75rem 1.5rem;
            border-radius: 4px;
            cursor: pointer;
            font-size: 1rem;
            font-weight: 500;
            transition: background 0.3s;
            display: block;
            width: 100%;
        }

        .form-submit:hover {
            background: #b38b4a;
        }

        /* Footer */
        .footer {
            background-color: var(--primary-color);
            color: var(--white);
            text-align: center;
            padding: 1.5rem;
            margin-top: 3rem;
        }

        /* Message */
        .message {
            color: green;
            text-align: center;
            margin-bottom: 1.5rem;
            padding: 0.75rem;
            background-color: #e8f5e9;
            border-radius: 4px;
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
    <h1>Customer Management</h1>
    <p>Register and manage your valued customers</p>
</section>

<!-- Form Section -->
<section class="form-section">
    <h1 class="section-title">Register Customer</h1>

    <form class="customer-form" method="post" action="CustomerCreate.jsp">
        <div class="form-group">
            <label for="customerID">Customer ID:</label>
            <input type="text" name="customerID" required maxlength="12" />
        </div>

        <div class="form-group">
            <label for="customerName">Customer Name:</label>
            <input type="text" name="customerName" required maxlength="30" />
        </div>

        <div class="form-group">
            <label for="customerType">Customer Type:</label>
            <select name="customerType" required>
                <option value="FIRST">First Time</option>
                <option value="REGULAR">Regular</option>
                <option value="VIP">VIP</option>
            </select>
        </div>

        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" name="email" required maxlength="100" />
        </div>

        <div class="form-group">
            <label for="phoneNumber">Phone Number:</label>
            <input type="text" name="phoneNumber" required maxlength="20" />
        </div>

        <div class="form-group">
            <label for="loyaltyPoints">Loyalty Points:</label>
            <input type="number" step="0.01" name="loyaltyPoints" required />
        </div>

        <button type="submit" class="form-submit">Save Customer</button>
    </form>
</section>

<!-- Footer -->
<footer class="footer">
    <p>Our kitchen is open from 11 AM to 10 PM everyday</p>
</footer>
</body>
</html>