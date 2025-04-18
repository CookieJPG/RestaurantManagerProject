<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gourmet Delight Restaurant</title>
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
        
        /* Menu Section */
        .menu-section {
            padding: 2rem;
        }
        
        .section-title {
            color: var(--primary-color);
            text-align: center;
            margin-bottom: 2rem;
            font-size: 2rem;
        }
        
        .menu-category {
            margin-bottom: 3rem;
        }
        
        .menu-category h2 {
            color: var(--primary-color);
            border-bottom: 2px solid var(--secondary-color);
            padding-bottom: 0.5rem;
            margin-bottom: 1.5rem;
        }
        
        .menu-grid {
            display: grid;
            grid-template-columns: repeat(4, 1fr);
            gap: 1.5rem;
            width: 90%;
            margin: 0 auto;
        }
        
        .menu-item {
            background: var(--white);
            border-radius: 8px;
            overflow: hidden;
            box-shadow: var(--shadow);
            transition: transform 0.3s;
        }
        
        .menu-item:hover {
            transform: translateY(-5px);
        }
        
        .menu-item-img {
            width: 100%;
            height: 200px;
            object-fit: cover;
        }
        
        .menu-item-content {
            padding: 1rem;
        }
        
        .menu-item-title {
            font-size: 1.2rem;
            color: var(--primary-color);
            margin-bottom: 0.5rem;
        }
        
        .menu-item-desc {
            color: var(--text-color);
            margin-bottom: 1rem;
            font-size: 0.9rem;
        }
        
        .menu-item-price {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-top: 1rem;
        }
        
        .price {
            font-weight: bold;
            color: var(--secondary-color);
        }
        
        .add-to-order {
            background: var(--secondary-color);
            color: white;
            border: none;
            padding: 0.5rem 1rem;
            border-radius: 4px;
            cursor: pointer;
            transition: background 0.3s;
        }
        
        .add-to-order:hover {
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
        
        /* Utility Classes */
        .container {
            padding: 0 2rem;
        }
    </style>
</head>
<body>
    <!-- Navigation Bar -->
    <nav class="navbar">
        <a href="#" class="navbar-brand">Gourmet Delight</a>
        <div class="nav-links">
            <a href="#orders">Orders</a>
            <a href="#stats">Statistics</a>
            <a href="#add-item">Add Item</a>
        </div>
    </nav>
    
    <!-- Hero Section -->
    <section class="hero">
        <h1>🍽️ Latin & Spanish Cuisine</h1>
        <p>Experience authentic flavors from Colombia, Peru, Venezuela, and Spain</p>
    </section>
    
    <!-- Menu Section -->
    <section class="menu-section">
        <h1 class="section-title">Our Menu 🍴</h1>
        
        <!-- Main Dishes -->
        <div class="menu-category">
            <h2>Main Courses</h2>
            <div class="menu-grid">
                <!-- Pabellón Criollo -->
                <div class="menu-item">
                    <img src="https://images.unsplash.com/photo-1601050690597-df0568f70950" alt="Pabellón Criollo" class="menu-item-img">
                    <div class="menu-item-content">
                        <h3 class="menu-item-title">Pabellón Criollo</h3>
                        <p class="menu-item-desc">Venezuela's national dish: shredded beef, black beans, rice, and fried plantains</p>
                        <div class="menu-item-price">
                            <span class="price">$18.99</span>
                        </div>
                    </div>
                </div>
                
                <!-- Bandeja Paisa -->
                <div class="menu-item">
                    <img src="https://images.unsplash.com/photo-1639664144075-9dafc7d0b9c5" alt="Bandeja Paisa" class="menu-item-img">
                    <div class="menu-item-content">
                        <h3 class="menu-item-title">Bandeja Paisa</h3>
                        <p class="menu-item-desc">Colombian platter with beans, rice, chorizo, chicharrón, avocado, plantain, and arepa</p>
                        <div class="menu-item-price">
                            <span class="price">$19.99</span>
                        </div>
                    </div>
                </div>
                
                <!-- Ceviche -->
                <div class="menu-item">
                    <img src="https://images.unsplash.com/photo-1611143669185-af224c5e0102" alt="Ceviche Peruano" class="menu-item-img">
                    <div class="menu-item-content">
                        <h3 class="menu-item-title">Ceviche Peruano</h3>
                        <p class="menu-item-desc">Peruvian classic: fresh fish marinated in lime juice with red onions, cilantro, and ají</p>
                        <div class="menu-item-price">
                            <span class="price">$16.99</span>
                        </div>
                    </div>
                </div>
                
                <!-- Paella -->
                <div class="menu-item">
                    <img src="https://images.unsplash.com/photo-1625938144745-fdc08a7b8b1b" alt="Paella Valenciana" class="menu-item-img">
                    <div class="menu-item-content">
                        <h3 class="menu-item-title">Paella Valenciana</h3>
                        <p class="menu-item-desc">Spain's iconic saffron rice dish with seafood, chicken, and vegetables</p>
                        <div class="menu-item-price">
                            <span class="price">$22.99</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
        <!-- Beverages -->
        <div class="menu-category">
            <h2>Beverages</h2>
            <div class="menu-grid">
                <!-- Aguapanela -->
                <div class="menu-item">
                    <img src="https://images.unsplash.com/photo-1513558161293-cdaf765ed2fd" alt="Aguapanela" class="menu-item-img">
                    <div class="menu-item-content">
                        <h3 class="menu-item-title">Aguapanela</h3>
                        <p class="menu-item-desc">Colombian comfort drink made from panela (unrefined sugar) with lime</p>
                        <div class="menu-item-price">
                            <span class="price">$4.99</span>
                        </div>
                    </div>
                </div>
                
                <!-- Pisco Sour -->
                <div class="menu-item">
                    <img src="https://images.unsplash.com/photo-1551751299-1b51cab2694c" alt="Pisco Sour" class="menu-item-img">
                    <div class="menu-item-content">
                        <h3 class="menu-item-title">Pisco Sour</h3>
                        <p class="menu-item-desc">Peru's national cocktail with pisco, lime juice, syrup, egg white, and bitters</p>
                        <div class="menu-item-price">
                            <span class="price">$10.99</span>
                        </div>
                    </div>
                </div>
                
                <!-- Cocada -->
                <div class="menu-item">
                    <img src="https://images.unsplash.com/photo-1601050690117-94f5f6fa1bd0" alt="Cocada" class="menu-item-img">
                    <div class="menu-item-content">
                        <h3 class="menu-item-title">Cocada</h3>
                        <p class="menu-item-desc">Venezuelan refreshing coconut milk drink, often served with condensed milk</p>
                        <div class="menu-item-price">
                            <span class="price">$5.99</span>
                        </div>
                    </div>
                </div>
                
                <!-- Tinto de Verano -->
                <div class="menu-item">
                    <img src="https://images.unsplash.com/photo-1470337458703-46ad1756a187" alt="Tinto de Verano" class="menu-item-img">
                    <div class="menu-item-content">
                        <h3 class="menu-item-title">Tinto de Verano</h3>
                        <p class="menu-item-desc">Spanish summer drink: red wine mixed with lemon soda over ice</p>
                        <div class="menu-item-price">
                            <span class="price">$7.99</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
        <!-- Desserts -->
        <div class="menu-category">
            <h2>Desserts</h2>
            <div class="menu-grid">
                <!-- Alfajores -->
                <div class="menu-item">
                    <img src="https://images.unsplash.com/photo-1616683690536-578b5f5ee5a8" alt="Alfajores" class="menu-item-img">
                    <div class="menu-item-content">
                        <h3 class="menu-item-title">Alfajores</h3>
                        <p class="menu-item-desc">Peruvian delicate shortbread cookies filled with dulce de leche</p>
                        <div class="menu-item-price">
                            <span class="price">$6.99</span>
                        </div>
                    </div>
                </div>
                
                <!-- Tres Leches -->
                <div class="menu-item">
                    <img src="https://images.unsplash.com/photo-1563805042-7684c019e1cb" alt="Tres Leches" class="menu-item-img">
                    <div class="menu-item-content">
                        <h3 class="menu-item-title">Tres Leches</h3>
                        <p class="menu-item-desc">Venezuelan moist cake soaked in three kinds of milk</p>
                        <div class="menu-item-price">
                            <span class="price">$7.99</span>
                        </div>
                    </div>
                </div>
                
                <!-- Arroz con Leche -->
                <div class="menu-item">
                    <img src="https://images.unsplash.com/photo-1601050690117-94f5f6fa1bd0" alt="Arroz con Leche" class="menu-item-img">
                    <div class="menu-item-content">
                        <h3 class="menu-item-title">Arroz con Leche</h3>
                        <p class="menu-item-desc">Colombian creamy rice pudding with cinnamon and raisins</p>
                        <div class="menu-item-price">
                            <span class="price">$5.99</span>
                        </div>
                    </div>
                </div>
                
                <!-- Crema Catalana -->
                <div class="menu-item">
                    <img src="https://images.unsplash.com/photo-1558312651-5b0c0c4b58b2" alt="Crema Catalana" class="menu-item-img">
                    <div class="menu-item-content">
                        <h3 class="menu-item-title">Crema Catalana</h3>
                        <p class="menu-item-desc">Spain's version of crème brûlée with lemon and cinnamon flavors</p>
                        <div class="menu-item-price">
                            <span class="price">$8.99</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    
    <!-- Footer -->
    <footer class="footer">
        <p>Our kitchen is open from 11 AM to 10 PM everyday</p>
    </footer>
</body>
</html>