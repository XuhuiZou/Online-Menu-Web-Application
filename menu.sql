-- Online Menu Web Application - database setup
-- Re-running this script resets the menu_item table to the sample data.

CREATE DATABASE IF NOT EXISTS menu
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE menu;
SET NAMES utf8mb4;

DROP TABLE IF EXISTS menu_item;

CREATE TABLE menu_item (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  category VARCHAR(50) NOT NULL,
  description VARCHAR(255) NOT NULL,
  price DECIMAL(8,2) NOT NULL
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Category values must match the form's radio buttons exactly:
-- Appetizers, Entrées, Desserts, Drinks
INSERT INTO menu_item (name, category, description, price) VALUES
('Chicken Alfredo', 'Entrées', 'Creamy pasta with grilled chicken', 14.99),
('Caesar Salad', 'Appetizers', 'Romaine, parmesan, croutons', 8.50),
('Margherita Pizza', 'Entrées', 'Tomato, mozzarella, basil', 12.00),
('Chocolate Cake', 'Desserts', 'Layered cake with ganache', 6.75),
('Iced Lemon Tea', 'Drinks', 'Fresh brewed tea with lemon', 3.25);
