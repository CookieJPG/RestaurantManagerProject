<%--
  Created by IntelliJ IDEA.
  User: CookieJPG
  Date: 25/03/2025
  Time: 09:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gourmet Delight Restaurant</title>
    <style>
        body {
            font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen, Ubuntu, Cantarell, sans-serif;
            line-height: 1.6;
            color: #333;
            max-width: 1200px;
            margin: 0 auto;
            padding: 1rem;
            background-color: #fff9f2;
        }
        h1, h2, h3 {
            color: #5a3921;
            margin-top: 2rem;
        }
        .container {
            display: flex;
            flex-wrap: wrap;
            gap: 2rem;
        }
        .card {
            background: #ffffff;
            border-radius: 8px;
            padding: 1.5rem;
            border: 1px solid #e9ecef;
            flex: 1;
            min-width: 300px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
        .form-group {
            margin-bottom: 1rem;
        }
        label {
            display: block;
            margin-bottom: 0.5rem;
            font-weight: bold;
            color: #5a3921;
        }
        input, select, textarea {
            width: 100%;
            padding: 0.5rem;
            border: 1px solid #ced4da;
            border-radius: 4px;
            font-size: 16px;
        }
        button {
            background: #d4a762;
            color: white;
            border: none;
            padding: 0.5rem 1rem;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            transition: background 0.3s;
        }
        button:hover {
            background: #b38b4a;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 1rem;
        }
        th, td {
            padding: 0.75rem;
            text-align: left;
            border-bottom: 1px solid #e9ecef;
        }
        th {
            background-color: #f8f4e8;
            color: #5a3921;
        }
        .menu-item {
            display: flex;
            margin-bottom: 1.5rem;
            border-bottom: 1px solid #eee;
            padding-bottom: 1rem;
        }
        .menu-item-img {
            width: 120px;
            height: 120px;
            object-fit: cover;
            border-radius: 8px;
            margin-right: 1rem;
        }
        .menu-item-details {
            flex: 1;
        }
        .menu-item-title {
            font-weight: bold;
            font-size: 1.2rem;
            color: #5a3921;
            margin-bottom: 0.5rem;
        }
        .menu-item-price {
            font-weight: bold;
            color: #d4a762;
        }
        .order-summary {
            background: #f8f4e8;
            padding: 1.5rem;
            border-radius: 8px;
            margin-top: 2rem;
        }
        .status-pending {
            color: #e67e22;
        }
        .status-completed {
            color: #27ae60;
        }
        .status-cancelled {
            color: #e74c3c;
        }
        .hero {
            background: linear-gradient(rgba(0,0,0,0.5), rgba(0,0,0,0.5)), url('https://images.unsplash.com/photo-1517248135467-4c7edcad34c4');
            background-size: cover;
            color: white;
            padding: 4rem 2rem;
            text-align: center;
            border-radius: 8px;
            margin-bottom: 2rem;
        }
        .hero h1 {
            color: white;
            font-size: 2.5rem;
        }
    </style>
</head>
<body>
<div class="hero">
    <h1>🍽️ Gourmet Delight Restaurant</h1>
    <p>Fine dining experience with exquisite flavors</p>
</div>

<!-- Display Menu Items -->
<h2>Our Menu 🍴</h2>
<c:choose>
    <c:when test="${empty restaurantManager.menuItems}">
        <p>Our menu is currently being updated. Please check back later.</p>
    </c:when>
    <c:otherwise>
        <div class="menu-container">
            <c:forEach var="item" items="${restaurantManager.menuItems}">
                <div class="menu-item">
                    <img src="${item.imageUrl}" alt="${item.name}" class="menu-item-img" onerror="this.src='https://via.placeholder.com/120?text=Food'">
                    <div class="menu-item-details">
                        <div class="menu-item-title">${item.name}</div>
                        <div class="menu-item-description">${item.description}</div>
                        <div class="menu-item-price">$${String.format("%.2f", item.price)}</div>
                        <div>
                            <form action="${pageContext.request.contextPath}/restaurant" method="post" style="display: inline;">
                                <input type="hidden" name="action" value="addToOrder">
                                <input type="hidden" name="itemId" value="${item.id}">
                                <button type="submit">Add to Order</button>
                            </form>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </c:otherwise>
</c:choose>

<!-- Current Order Summary -->
<c:if test="${not empty restaurantManager.currentOrder}">
    <div class="order-summary">
        <h2>Your Order 🛒</h2>
        <table>
            <thead>
            <tr>
                <th>Item</th>
                <th>Quantity</th>
                <th>Price</th>
                <th>Subtotal</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="orderItem" items="${restaurantManager.currentOrder.items}">
                <tr>
                    <td>${orderItem.menuItem.name}</td>
                    <td>${orderItem.quantity}</td>
                    <td>$${String.format("%.2f", orderItem.menuItem.price)}</td>
                    <td>$${String.format("%.2f", orderItem.menuItem.price * orderItem.quantity)}</td>
                    <td>
                        <form action="${pageContext.request.contextPath}/restaurant" method="post" style="display: inline;">
                            <input type="hidden" name="action" value="removeFromOrder">
                            <input type="hidden" name="itemId" value="${orderItem.menuItem.id}">
                            <button type="submit" style="background: #e74c3c;">Remove</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
            <tfoot>
            <tr>
                <td colspan="3" style="text-align: right;"><strong>Total:</strong></td>
                <td><strong>$${String.format("%.2f", restaurantManager.currentOrder.total)}</strong></td>
                <td></td>
            </tr>
            </tfoot>
        </table>

        <form action="${pageContext.request.contextPath}/restaurant" method="post">
            <input type="hidden" name="action" value="placeOrder">
            <div class="form-group">
                <label for="customerName">Your Name:</label>
                <input type="text" id="customerName" name="customerName" required>
            </div>
            <div class="form-group">
                <label for="customerPhone">Phone Number:</label>
                <input type="tel" id="customerPhone" name="customerPhone" required>
            </div>
            <div class="form-group">
                <label for="deliveryAddress">Delivery Address (if delivery):</label>
                <input type="text" id="deliveryAddress" name="deliveryAddress">
            </div>
            <div class="form-group">
                <label for="orderType">Order Type:</label>
                <select id="orderType" name="orderType" required>
                    <option value="DINE_IN">Dine In</option>
                    <option value="TAKEOUT">Takeout</option>
                    <option value="DELIVERY">Delivery</option>
                </select>
            </div>
            <div class="form-group">
                <label for="specialInstructions">Special Instructions:</label>
                <textarea id="specialInstructions" name="specialInstructions" rows="3"></textarea>
            </div>
            <button type="submit" style="background: #27ae60;">Place Order</button>
            <button type="submit" formaction="${pageContext.request.contextPath}/restaurant?action=clearOrder" style="background: #e74c3c; margin-left: 1rem;">Clear Order</button>
        </form>
    </div>
</c:if>

<!-- Display Orders -->
<h2>Order Management 📋</h2>
<c:choose>
    <c:when test="${empty restaurantManager.allOrders}">
        <p>No orders placed yet.</p>
    </c:when>
    <c:otherwise>
        <table>
            <thead>
            <tr>
                <th>Order ID</th>
                <th>Customer</th>
                <th>Items</th>
                <th>Total</th>
                <th>Type</th>
                <th>Status</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="order" items="${restaurantManager.allOrders}">
                <tr>
                    <td>#${order.id}</td>
                    <td>${order.customerName}</td>
                    <td>
                        <c:forEach var="item" items="${order.items}" varStatus="status">
                            ${item.menuItem.name} (x${item.quantity})${!status.last ? ', ' : ''}
                        </c:forEach>
                    </td>
                    <td>$${String.format("%.2f", order.total)}</td>
                    <td>${order.orderType}</td>
                    <td class="status-${order.status.toLowerCase()}">${order.status}</td>
                    <td>
                        <c:if test="${order.status == 'PENDING'}">
                            <form action="${pageContext.request.contextPath}/restaurant" method="post" style="display: inline;">
                                <input type="hidden" name="action" value="updateOrderStatus">
                                <input type="hidden" name="orderId" value="${order.id}">
                                <input type="hidden" name="status" value="COMPLETED">
                                <button type="submit" style="background: #27ae60; padding: 0.3rem 0.6rem; font-size: 0.9rem;">Complete</button>
                            </form>
                            <form action="${pageContext.request.contextPath}/restaurant" method="post" style="display: inline;">
                                <input type="hidden" name="action" value="updateOrderStatus">
                                <input type="hidden" name="orderId" value="${order.id}">
                                <input type="hidden" name="status" value="CANCELLED">
                                <button type="submit" style="background: #e74c3c; padding: 0.3rem 0.6rem; font-size: 0.9rem;">Cancel</button>
                            </form>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:otherwise>
</c:choose>

<div class="container">
    <!-- Add Menu Item Form -->
    <div class="card">
        <h2>Add Menu Item 🍲</h2>
        <form action="${pageContext.request.contextPath}/restaurant" method="post" enctype="multipart/form-data">
            <input type="hidden" name="action" value="addMenuItem">

            <div class="form-group">
                <label for="itemName">Item Name:</label>
                <input type="text" id="itemName" name="itemName" required>
            </div>

            <div class="form-group">
                <label for="itemDescription">Description:</label>
                <textarea id="itemDescription" name="itemDescription" rows="3" required></textarea>
            </div>

            <div class="form-group">
                <label for="itemPrice">Price:</label>
                <input type="number" id="itemPrice" name="itemPrice" min="0" step="0.01" required>
            </div>

            <div class="form-group">
                <label for="itemCategory">Category:</label>
                <select id="itemCategory" name="itemCategory" required>
                    <option value="APPETIZER">Appetizer</option>
                    <option value="MAIN_COURSE">Main Course</option>
                    <option value="DESSERT">Dessert</option>
                    <option value="BEVERAGE">Beverage</option>
                </select>
            </div>

            <div class="form-group">
                <label for="itemImage">Image:</label>
                <input type="file" id="itemImage" name="itemImage" accept="image/*">
            </div>

            <button type="submit">Add Menu Item</button>
        </form>
    </div>

    <!-- Restaurant Statistics -->
    <div class="card">
        <h2>Restaurant Stats 📊</h2>
        <div style="margin-bottom: 1rem;">
            <strong>Total Orders:</strong> ${restaurantManager.totalOrders}
        </div>
        <div style="margin-bottom: 1rem;">
            <strong>Revenue Today:</strong> $${String.format("%.2f", restaurantManager.revenueToday)}
        </div>
        <div style="margin-bottom: 1rem;">
            <strong>Most Popular Item:</strong>
            <c:choose>
                <c:when test="${not empty restaurantManager.mostPopularItem}">
                    ${restaurantManager.mostPopularItem.name} (${restaurantManager.mostPopularItemCount} orders)
                </c:when>
                <c:otherwise>
                    N/A
                </c:otherwise>
            </c:choose>
        </div>
        <div>
            <strong>Pending Orders:</strong> ${restaurantManager.pendingOrdersCount}
        </div>
    </div>
</div>

<div class="tip" style="background: #f8f4e8; padding: 1rem; border-radius: 4px; margin: 1rem 0;">
    <strong>Tip:</strong> Our kitchen is open from 11:00 AM to 10:00 PM daily. Delivery available within 5 miles!
</div>
</body>
</html>