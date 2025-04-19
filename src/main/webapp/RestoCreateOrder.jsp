<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Orders - Gourmet Delight</title>
	<link rel="stylesheet" href="css/styles.css">
	<link rel="stylesheet" href="css/RestoCreateOrder.css">
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
        
        /* Create Order Section */
        .create-order-section {
            padding: 2rem;
        }
        
        .section-title {
            color: var(--primary-color);
            text-align: center;
            margin-bottom: 2rem;
            font-size: 2rem;
        }
        
        .form-container {
            background: var(--white);
            border-radius: 8px;
            padding: 2rem;
            box-shadow: var(--shadow);
            width: 90%;
            max-width: 600px;
            margin: 0 auto;
        }
        
        .form-group {
            margin-bottom: 1.5rem;
        }
        
        .form-group label {
            display: block;
            margin-bottom: 0.5rem;
            font-weight: bold;
            color: var(--primary-color);
        }
        
        .form-group input,
        .form-group select {
            width: 100%;
            padding: 0.75rem;
            border: 1px solid #ced4da;
            border-radius: 4px;
            font-size: 1rem;
            transition: border-color 0.3s;
        }
        
        .form-group input:focus,
        .form-group select:focus {
            border-color: var(--secondary-color);
            outline: none;
        }
        
        .menu-items {
            margin-top: 2rem;
        }
        
        .menu-item {
            display: flex;
            align-items: center;
            margin-bottom: 1rem;
            padding: 1rem;
            background: var(--light-bg);
            border-radius: 4px;
        }
        
        .menu-item-info {
            flex: 1;
        }
        
        .menu-item-name {
            font-weight: bold;
            color: var(--primary-color);
        }
        
        .menu-item-price {
            color: var(--secondary-color);
            font-weight: bold;
        }
        
        .quantity-control {
            display: flex;
            align-items: center;
        }
        
        .quantity-control button {
            background: var(--secondary-color);
            color: white;
            border: none;
            width: 30px;
            height: 30px;
            border-radius: 50%;
            cursor: pointer;
            font-size: 1rem;
            display: flex;
            align-items: center;
            justify-content: center;
        }
        
        .quantity-control input {
            width: 50px;
            text-align: center;
            margin: 0 0.5rem;
            padding: 0.25rem;
        }
        
        .submit-btn {
            background: var(--secondary-color);
            color: white;
            border: none;
            padding: 0.75rem 1.5rem;
            border-radius: 4px;
            cursor: pointer;
            font-size: 1rem;
            font-weight: bold;
            transition: background 0.3s;
            width: 100%;
            margin-top: 1.5rem;
        }
        
        .submit-btn:hover {
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
        <h1>&#127860 Create New Order</h1>
        <p>Start a new dining experience for your customers</p>
    </section>
    
    <!-- Create Order Section -->
    <section class="create-order-section">
        <h1 class="section-title">New Order Details</h1>
        
        <div class="form-container">
            <form id="createOrderForm">
                <div class="form-group">
                    <label for="tableNumber">Table Number:</label>
                    <select id="tableNumber" name="tableNumber" required>
                        <option value="">Select a table</option>
                        <option value="1">Table 1</option>
                        <option value="2">Table 2</option>
                        <option value="3">Table 3</option>
                        <option value="4">Table 4</option>
                        <option value="5">Table 5</option>
                        <option value="6">Table 6</option>
                        <option value="7">Table 7</option>
                        <option value="8">Table 8</option>
                    </select>
                </div>
                
                <div class="form-group">
                    <label for="customerId">Customer ID:</label>
                    <input type="text" id="customerId" name="customerId" pattern="[A-Za-z0-9]{12}" title="12-character customer ID" required>
                </div>
                
                <div class="menu-items">
                    <h3>Menu Items</h3>
                    
                    <!-- Sample menu items - would be loaded dynamically in a real app -->
                    <div class="menu-item">
                        <div class="menu-item-info">
                            <div class="menu-item-name">Pabellón Criollo</div>
                            <div class="menu-item-price">$18.99</div>
                        </div>
                        <div class="quantity-control">
                            <button type="button" class="decrement">-</button>
                            <input type="number" value="0" min="0" class="quantity">
                            <button type="button" class="increment">+</button>
                        </div>
                    </div>
                    
                    <div class="menu-item">
                        <div class="menu-item-info">
                            <div class="menu-item-name">Ceviche Peruano</div>
                            <div class="menu-item-price">$16.99</div>
                        </div>
                        <div class="quantity-control">
                            <button type="button" class="decrement">-</button>
                            <input type="number" value="0" min="0" class="quantity">
                            <button type="button" class="increment">+</button>
                        </div>
                    </div>
                    
                    <div class="menu-item">
                        <div class="menu-item-info">
                            <div class="menu-item-name">Paella Valenciana</div>
                            <div class="menu-item-price">$22.99</div>
                        </div>
                        <div class="quantity-control">
                            <button type="button" class="decrement">-</button>
                            <input type="number" value="0" min="0" class="quantity">
                            <button type="button" class="increment">+</button>
                        </div>
                    </div>
					
					<div class="menu-item">
						<div class="menu-item-info">
							<div class="menu-item-name">Bandeja Paisa</div>
							<div class="menu-item-price">$19.99</div>
						</div>
						<div class="quantity-control">
							<button type="button" class="decrement">-</button>
							<input type="number" value="0" min="0" class="quantity">
							<button type="button" class="increment">+</button>
						</div>
					</div>
					
					<div class="menu-item">
						<div class="menu-item-info">
							<div class="menu-item-name">Aguapanela</div>
							<div class="menu-item-price">$4.99</div>
						</div>
						<div class="quantity-control">
							<button type="button" class="decrement">-</button>
							<input type="number" value="0" min="0" class="quantity">
							<button type="button" class="increment">+</button>
						</div>
					</div>
					
					
					<div class="menu-item">
						<div class="menu-item-info">
							<div class="menu-item-name">Pisco Sour</div>
							<div class="menu-item-price">$10.99</div>
						</div>
						<div class="quantity-control">
							<button type="button" class="decrement">-</button>
							<input type="number" value="0" min="0" class="quantity">
							<button type="button" class="increment">+</button>
						</div>
					</div>


					<div class="menu-item">
						<div class="menu-item-info">
							<div class="menu-item-name">Cocada</div>
							<div class="menu-item-price">$5.99</div>
						</div>
						<div class="quantity-control">
							<button type="button" class="decrement">-</button>
							<input type="number" value="0" min="0" class="quantity">
							<button type="button" class="increment">+</button>
						</div>
					</div>


					<div class="menu-item">
						<div class="menu-item-info">
							<div class="menu-item-name">Tinto de Verano</div>
							<div class="menu-item-price">$7.99</div>
						</div>
						<div class="quantity-control">
							<button type="button" class="decrement">-</button>
							<input type="number" value="0" min="0" class="quantity">
							<button type="button" class="increment">+</button>
						</div>
					</div>


					<div class="menu-item">
						<div class="menu-item-info">
							<div class="menu-item-name">Alfajores</div>
							<div class="menu-item-price">$6.99</div>
						</div>
						<div class="quantity-control">
							<button type="button" class="decrement">-</button>
							<input type="number" value="0" min="0" class="quantity">
							<button type="button" class="increment">+</button>
						</div>
					</div>


					<div class="menu-item">
						<div class="menu-item-info">
							<div class="menu-item-name">Tres Leches</div>
							<div class="menu-item-price">$7.99</div>
						</div>
						<div class="quantity-control">
							<button type="button" class="decrement">-</button>
							<input type="number" value="0" min="0" class="quantity">
							<button type="button" class="increment">+</button>
						</div>
					</div>


					<div class="menu-item">
						<div class="menu-item-info">
							<div class="menu-item-name">Arroz con Leche</div>
							<div class="menu-item-price">$5.99</div>
						</div>
						<div class="quantity-control">
							<button type="button" class="decrement">-</button>
							<input type="number" value="0" min="0" class="quantity">
							<button type="button" class="increment">+</button>
						</div>
					</div>


					<div class="menu-item">
						<div class="menu-item-info">
							<div class="menu-item-name">Crema Catalana</div>
							<div class="menu-item-price">$8.99</div>
						</div>
						<div class="quantity-control">
							<button type="button" class="decrement">-</button>
							<input type="number" value="0" min="0" class="quantity">
							<button type="button" class="increment">+</button>
						</div>
					</div>

                </div>
                
                <button type="submit" class="submit-btn">Create Order</button>
            </form>
        </div>
    </section>
    
    <!-- Footer -->
    <footer class="footer">
        <p>Our kitchen is open from 11 AM to 10 PM everyday</p>
    </footer>

    <script>
        // Quantity control functionality
        document.querySelectorAll('.increment').forEach(button => {
            button.addEventListener('click', function() {
                const input = this.previousElementSibling;
                input.value = parseInt(input.value) + 1;
            });
        });
        
        document.querySelectorAll('.decrement').forEach(button => {
            button.addEventListener('click', function() {
                const input = this.nextElementSibling;
                if (parseInt(input.value) > 0) {
                    input.value = parseInt(input.value) - 1;
                }
            });
        });
        
        // Form submission
        document.getElementById('createOrderForm').addEventListener('submit', function(e) {
            e.preventDefault();
            
            // Get form data
            const tableNumber = document.getElementById('tableNumber').value;
            const customerId = document.getElementById('customerId').value;
            
            // Get selected items
            const items = [];
            document.querySelectorAll('.menu-item').forEach(item => {
                const quantity = parseInt(item.querySelector('.quantity').value);
                if (quantity > 0) {
                    const name = item.querySelector('.menu-item-name').textContent;
                    const price = parseFloat(item.querySelector('.menu-item-price').textContent.replace('$', ''));
                    items.push({
                        name: name,
                        price: price,
                        quantity: quantity
                    });
                }
            });
            
            if (items.length === 0) {
                alert('Please select at least one menu item');
                return;
            }
            
            // In a real app, this would send the data to the server
            console.log('Creating order:', {
                tableNumber: tableNumber,
                customerId: customerId,
                items: items,
                status: 'Pending',
                orderDate: new Date()
            });
            
            alert('Order created successfully!');
            this.reset();
            document.querySelectorAll('.quantity').forEach(input => {
                input.value = 0;
            });
        });
    </script>
</body>
</html>