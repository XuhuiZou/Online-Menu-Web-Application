-- ============================================================================
-- Online Menu Web Application — Seed Data
-- Owner: Fakhruddin Shaik (Database Developer)
-- Course: IT4045C – Final Project
--
-- Inserts 16 sample menu items (4 per category) so the list page, sorting,
-- and every CRUD operation can be tested immediately after setup.
-- Prices and descriptions for the first four items match the wireframes in
-- the README.
--
-- Run AFTER db/schema.sql, and only once on a fresh (empty) table:
--   phpMyAdmin -> select the `menu` database -> Import -> this file -> Go
--   mysql -u root -p menu < db/seed-data.sql
--
-- Re-running this file will insert the same items a second time (new ids).
-- To start over, empty the table first in phpMyAdmin (menu_item -> Empty),
-- or run:  TRUNCATE TABLE `menu_item`;
-- ============================================================================

USE `menu`;

INSERT INTO `menu_item` (`name`, `category`, `description`, `price`) VALUES
    ('Caesar Salad',     'Appetizers', 'Romaine, parmesan, croutons',          '8.50'),
    ('Bruschetta',       'Appetizers', 'Grilled bread, tomato, basil, garlic', '7.25'),
    ('Garlic Bread',     'Appetizers', 'Toasted bread, garlic butter',         '5.50'),
    ('Tomato Soup',      'Appetizers', 'Creamy tomato soup, basil',            '6.25'),
    ('Chicken Alfredo',  'Entrées',    'Creamy pasta, grilled chicken',         '14.99'),
    ('Margherita Pizza', 'Entrées',    'Tomato, mozzarella, basil',             '12.00'),
    ('Grilled Salmon',   'Entrées',    'Grilled salmon, lemon butter sauce',    '18.95'),
    ('Beef Lasagna',     'Entrées',    'Layered pasta, beef ragu, bechamel',    '15.50'),
    ('Chocolate Cake',   'Desserts',   'Layered cake with ganache',              '6.75'),
    ('Tiramisu',         'Desserts',   'Coffee-soaked ladyfingers, mascarpone', '7.50'),
    ('Cheesecake',       'Desserts',   'New York cheesecake, berry compote',    '6.95'),
    ('Vanilla Ice Cream','Desserts',   'Vanilla bean ice cream, wafer',         '4.50'),
    ('Lemonade',         'Drinks',     'Freshly squeezed lemons, lightly sweet', '3.50'),
    ('Iced Tea',         'Drinks',     'Brewed black tea served over ice',      '2.95'),
    ('Espresso',         'Drinks',     'Double shot espresso',                   '2.75'),
    ('Orange Juice',     'Drinks',     'Freshly squeezed orange juice',          '3.25');

-- Verify (expected: 16 rows, ordered by category, then name):
-- SELECT `id`, `name`, `category`, `price`
--   FROM `menu_item`
--  ORDER BY `category` ASC, `name` ASC;
