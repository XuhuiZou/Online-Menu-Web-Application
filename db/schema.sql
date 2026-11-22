-- ============================================================================
-- Online Menu Web Application — Database Schema
-- Owner: Fakhruddin Shaik (Database Developer)
-- Course: IT4045C – Final Project
--
-- Creates the `menu` database and the `menu_item` table used by the
-- Spring Boot application. Hibernate automatic schema creation is DISABLED
-- (spring.jpa.hibernate.ddl-auto=none), so this file MUST be run before the
-- application is started for the first time.
--
-- How to run (phpMyAdmin): open http://localhost/phpmyadmin
--   -> Import -> Choose File -> select this file -> Go.
-- How to run (MySQL command line):
--   mysql -u root -p < db/schema.sql
--
-- Column types match the JPA entity
-- (src/main/java/com/onlinemenu/entity/MenuItem.java) and the README:
--   id          INT, auto-increment primary key
--   name        VARCHAR(255), required
--   category    VARCHAR(100), required — Appetizers, Entrées, Desserts, Drinks
--   description VARCHAR(500), required
--   price       DECIMAL(8,2), required, never negative
-- ============================================================================

CREATE DATABASE IF NOT EXISTS `menu`
    DEFAULT CHARACTER SET utf8mb4
    DEFAULT COLLATE utf8mb4_unicode_ci;

USE `menu`;

CREATE TABLE IF NOT EXISTS `menu_item` (
    `id`          INT          NOT NULL AUTO_INCREMENT,
    `name`        VARCHAR(255) NOT NULL,
    `category`    VARCHAR(100) NOT NULL,
    `description` VARCHAR(500) NOT NULL,
    `price`       DECIMAL(8,2) NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `chk_menu_item_price_non_negative` CHECK (`price` >= 0)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

-- Verify the table was created (expected: 5 columns, as above).
-- SHOW COLUMNS FROM `menu_item`;
-- SELECT COUNT(*) AS `menu_item_rows` FROM `menu_item`;
