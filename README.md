# Online Menu Web Application

IT4045C Final Project – Online Menu CRUD Application

A Spring Boot web application that lets a restaurant view and manage its menu online. Staff can add, update, and delete menu items, and customers can browse the current menu. Menu data is stored in a MySQL database and shown through Thymeleaf pages styled with Bootstrap.

## Purpose of the Application

Many small restaurants still rely on printed or hand-edited menus. When a price changes, a dish is added, or an item is discontinued, the menu becomes outdated and has to be reprinted or rewritten by hand. This costs time and money and can confuse customers.

This application gives restaurant staff one simple place to keep the menu accurate, and gives customers an up-to-date menu they can view online.

## Target Users

| User | Needs |
|---|---|
| Restaurant Owner / Manager | Add new dishes, update prices and descriptions, remove discontinued items, and confirm deletions to avoid mistakes. |
| Customer | View a clear, current, and organized menu with item descriptions and prices. |

## Planned Features

The application supports the full CRUD (Create, Read, Update, Delete) workflow for menu items.

- **View menu (Read):** Display all menu items in a table sorted by category and item name.
- **Add menu item (Create):** Enter the item name, category, description, and price, then save the new record.
- **Update menu item (Update):** Open a pre-populated form, edit the item, and save the changes.
- **Delete menu item (Delete):** Remove an item after a browser confirmation prompt.

### Endpoints

| CRUD | Endpoint | HTTP Method | Purpose |
|---|---|---|---|
| Read | `/Menu/list` | GET | Retrieve and display all menu items. |
| Create | `/Menu/viewAddForm` | GET | Open a blank menu item form. |
| Create / Update | `/Menu/save` | POST | Save a new or edited menu item. |
| Update | `/Menu/viewUpdateForm?itemId=...` | GET | Load an existing item into the form. |
| Delete | `/Menu/delete?itemId=...` | GET | Delete the selected item after UI confirmation. |

## Technology Stack

| Component | Technology | Purpose |
|---|---|---|
| Development IDE | IntelliJ IDEA | Develop, run, and maintain the project. |
| Backend | Java + Spring Boot | Web application, MVC controller, service layer, and configuration. |
| Persistence | Spring Data JPA | Maps the `MenuItem` entity to the `menu_item` table and performs database operations. |
| Frontend | Thymeleaf + Bootstrap 4 | Renders dynamic HTML pages and provides responsive styling. |
| Database | MySQL | Stores persistent menu data in the local `menu` database. |
| Local Server / DB Environment | WAMPServer | Local environment used with MySQL. |
| Database Administration | phpMyAdmin | Create, inspect, and manage the database and records. |
| Version Control | Git + GitHub | Source code storage and team collaboration. |

## Application Design

### Architecture

Requests flow through separate layers so the user interface stays independent of the database:

```
Web Browser (Thymeleaf + Bootstrap)
        |
MenuController  (Spring MVC request handling)
        |
MenuService / MenuServiceImpl  (business-service layer)
        |
MenuRepository  (Spring Data JPA)
        |
MySQL database "menu", table "menu_item"
```

### Database

The application connects to a local MySQL database named `menu` at `localhost:3306`. Hibernate automatic schema creation is disabled, so the table must exist before the application starts.

| Column | Type | Role |
|---|---|---|
| id | Integer | Primary key, identity generated |
| name | VARCHAR | Menu item name |
| category | VARCHAR | Menu category (Appetizers, Entrées, Desserts, Drinks) |
| description | VARCHAR | Brief description of the item |
| price | DECIMAL(8,2) | Item price |

### Wireframes

**Menu List (Read)**

```
localhost:8080/Menu/list

Online Menu Manager
[ Add New Menu Item ]

Item Name         Category    Description                   Price   Actions
Chicken Alfredo   Entrees     Creamy pasta, grilled chicken $14.99  [Update] [Delete]
Caesar Salad      Appetizers  Romaine, parmesan, croutons   $8.50   [Update] [Delete]
Margherita Pizza  Entrees     Tomato, mozzarella, basil     $12.00  [Update] [Delete]
Chocolate Cake    Desserts    Layered cake with ganache     $6.75   [Update] [Delete]
```

**Add / Update Menu Item (Create / Update)**

```
localhost:8080/Menu/viewAddForm

Online Menu Manager
[ Menu List ]

Add Menu Item
Item name:    ______________
Category:     ______________
Description:  ______________
Price:        ______________
[ Save ]
```

The Update page uses the same form, pre-filled with the selected item's values.

**Delete Confirmation**

```
localhost:8080 says
To confirm deletion, please click OK; otherwise, click Cancel
[ OK ]  [ Cancel ]
```

> Screenshots of the finished pages can be added here as the project is built.

## Team Members and Responsibilities

Course: IT4045C – Final Project

| Team Member | Role | Responsibilities |
|---|---|---|
| Xuhui Zou | GitHub Repository Owner / Backend Developer | Owns the public GitHub repository, manages access for teammates, and prioritizes features. Builds the `MenuItem` entity, repository, service, and controller classes. |
| Bunlong Pheak | UI / Frontend Developer | Coordinates the team and builds the Thymeleaf templates and Bootstrap interface. |
| Fakhruddin Shaik | Database Developer / Tester / Documentation | Creates and manages the MySQL `menu` database and `menu_item` table in phpMyAdmin, tests all CRUD operations, and maintains the design document. |

## Repository

Public GitHub repository: <https://github.com/XuhuiZou/Online-Menu-Web-Application>
