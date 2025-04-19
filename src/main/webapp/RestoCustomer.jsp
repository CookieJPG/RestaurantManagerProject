<!DOCTYPE html>
<html>

<head>
    <title>Crear Cliente</title>
    <link rel="stylesheet" type="text/css" href="styles.css"> <!-- Usa el mismo CSS -->
</head>

<body>
    <div class="container">
        <h1>Registrar Cliente</h1>

        <% if (!message.isEmpty()) { %>
            <p style="color: green;">
                <%= message %>
            </p>
            <% } %>

                <form method="post" action="CustomerCreate.jsp">
                    <label for="customerID">ID del Cliente:</label>
                    <input type="text" name="customerID" required maxlength="12" />

                    <label for="customerName">Nombre:</label>
                    <input type="text" name="customerName" required maxlength="30" />

                    <label for="customerType">Tipo de Cliente:</label>
                    <select name="customerType" required>
                        <option value="FIRST">First Time</option>
                        <option value="REGULAR">Regular</option>
                        <option value="VIP">VIP</option>
                    </select>

                    <label for="email">Correo Electrónico:</label>
                    <input type="email" name="email" required maxlength="100" />

                    <label for="phoneNumber">Número Telefónico:</label>
                    <input type="text" name="phoneNumber" required maxlength="20" />

                    <label for="loyaltyPoints">Puntos de Fidelidad:</label>
                    <input type="number" step="0.01" name="loyaltyPoints" required />

                    <button type="submit">Guardar Cliente</button>
                </form>
    </div>
</body>

</html>