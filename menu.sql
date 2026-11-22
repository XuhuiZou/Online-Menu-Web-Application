CREATE DATABASE IF NOT EXISTS menu;
USE menu;

CREATE TABLE IF NOT EXISTS menu_item (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  category VARCHAR(50) NOT NULL,
  description VARCHAR(255),
  price DECIMAL(8,2) NOT NULL
);

INSERT INTO menu_item (name, category, description, price) VALUES
('Chicken Alfredo', 'Entrées', 'Creamy pasta with grilled chicken', 14.99),
('Caesar Salad', 'Appetizers', 'Romaine, parmesan, croutons', 8.50),
('Margherita Pizza', 'Entrées', 'Tomato, mozzarella, basil', 12.00),
('Chocolate Cake', 'Desserts', 'Layered cake with ganache', 6.75);
