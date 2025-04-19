<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Payment - Gourmet Delight</title>
    <link rel="stylesheet" href="css/styles.css">
    <link rel="stylesheet" href="css/RestoPay.css">
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
        
        /* Payment Management Section */
        .payment-section {
            padding: 2rem;
        }
        
        .section-title {
            color: var(--primary-color);
            text-align: center;
            margin-bottom: 2rem;
            font-size: 2rem;
        }
        
        .payment-container {
            background: var(--white);
            border-radius: 8px;
            padding: 2rem;
            box-shadow: var(--shadow);
            width: 90%;
            margin: 0 auto;
        }
        
        /* Payment Form */
        .payment-form {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 1.5rem;
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
        
        /* Payment Records Table */
        .payment-records {
            margin-top: 3rem;
        }
        
        .payment-records h2 {
            color: var(--primary-color);
            margin-bottom: 1.5rem;
            font-size: 1.5rem;
            border-bottom: 1px solid #eee;
            padding-bottom: 0.5rem;
        }
        
        .records-table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 1rem;
        }
        
        .records-table th, .records-table td {
            padding: 1rem;
            text-align: left;
            border-bottom: 1px solid #e9ecef;
        }
        
        .records-table th {
            background-color: var(--light-bg);
            color: var(--primary-color);
            font-weight: 600;
        }
        
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
        
        .status-completed {
            background-color: #d4edda;
            color: #155724;
        }
        
        .status-failed {
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
        </div>
    </nav>
    
    <!-- Hero Section -->
    <section class="hero">
        <h1>&#127860 Payment Management</h1>
        <p>Register and track all customer payments in one place</p>
    </section>
    
    <!-- Payment Management Section -->
    <section class="payment-section">
        <h1 class="section-title">Register Payment</h1>
        
        <div class="payment-container">
            <form id="paymentForm" class="payment-form">
                <div class="form-group">
                    <label for="orderId">Order ID</label>
                    <input type="number" id="orderId" class="form-control" required>
                </div>
                
                <div class="form-group">
                    <label for="amount">Amount ($)</label>
                    <input type="number" id="amount" class="form-control" step="0.01" min="0" required>
                </div>
                
                <div class="form-group">
                    <label for="paymentMethod">Payment Method</label>
                    <select id="paymentMethod" class="form-control" required>
                        <option value="">Select payment method</option>
                        <option value="Cash">Cash</option>
                        <option value="Credit Card">Credit Card</option>
                        <option value="Debit Card">Debit Card</option>
                        <option value="Mobile Payment">Mobile Payment</option>
                        <option value="Bank Transfer">Bank Transfer</option>
                    </select>
                </div>
                
                <div class="form-group">
                    <label for="transactionId">Transaction ID (optional)</label>
                    <input type="text" id="transactionId" class="form-control">
                </div>
                
                <div class="form-group">
                    <label for="status">Payment Status</label>
                    <select id="status" class="form-control" required>
                        <option value="Pending" selected>Pending</option>
                        <option value="Completed">Completed</option>
                        <option value="Failed">Failed</option>
                    </select>
                </div>
                
                <div class="form-group full-width">
                    <button type="submit" class="btn btn-primary">Register Payment</button>
                </div>
            </form>
            
            <!-- Payment Records Table -->
            <div class="payment-records">
                <h2>Recent Payments</h2>
                <table class="records-table">
                    <thead>
                        <tr>
                            <th>Payment ID</th>
                            <th>Order ID</th>
                            <th>Amount</th>
                            <th>Method</th>
                            <th>Transaction ID</th>
                            <th>Status</th>
                            <th>Time</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>#5001</td>
                            <td>#1002</td>
                            <td>$34.97</td>
                            <td>Credit Card</td>
                            <td>TRX-789456123</td>
                            <td><span class="status-badge status-completed">Completed</span></td>
                            <td>Oct 15, 2023 1:30 PM</td>
                        </tr>
                        <tr>
                            <td>#5002</td>
                            <td>#1001</td>
                            <td>$28.97</td>
                            <td>Cash</td>
                            <td>-</td>
                            <td><span class="status-badge status-pending">Pending</span></td>
                            <td>Oct 15, 2023 1:45 PM</td>
                        </tr>
                        <tr>
                            <td>#5003</td>
                            <td>#1003</td>
                            <td>$38.97</td>
                            <td>Debit Card</td>
                            <td>TRX-321654987</td>
                            <td><span class="status-badge status-failed">Failed</span></td>
                            <td>Oct 15, 2023 3:15 PM</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </section>
    
    <!-- Footer -->
    <footer class="footer">
        <p>Our kitchen is open from 11 AM to 10 PM everyday</p>
    </footer>

    <script>
        document.getElementById('paymentForm').addEventListener('submit', function(e) {
            e.preventDefault();
            
            // Get form values
            const orderId = document.getElementById('orderId').value;
            const amount = document.getElementById('amount').value;
            const paymentMethod = document.getElementById('paymentMethod').value;
            const transactionId = document.getElementById('transactionId').value;
            const status = document.getElementById('status').value;
            const paymentTime = new Date().toLocaleString();
            
            // In a real application, you would send this data to your server
            console.log('Payment Data:', {
                orderId,
                amount,
                paymentMethod,
                transactionId,
                status,
                paymentTime
            });
            
            // Show success message
            alert('Payment registered successfully!');
            
            // Reset form
            this.reset();
            
            // In a real app, you would refresh the payment records table here
        });
    </script>
</body>
</html>