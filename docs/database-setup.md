# Database Setup — MySQL / phpMyAdmin

Owner: Fakhruddin Shaik (Database Developer) · Course: IT4045C – Final Project

The application does **not** create its own tables. Hibernate automatic
schema creation is disabled (`spring.jpa.hibernate.ddl-auto=none` in
`src/main/resources/application.properties`), so the database and table must
exist before the app starts. This guide is the only setup step the database
needs.

## What gets created

| Object | Definition | Source of truth |
| --- | --- | --- |
| Database `menu` | `utf8mb4`, `utf8mb4_unicode_ci` | `db/schema.sql` |
| Table `menu_item` | `id` INT auto-increment PK, `name` VARCHAR(255) NOT NULL, `category` VARCHAR(100) NOT NULL, `description` VARCHAR(500) NOT NULL, `price` DECIMAL(8,2) NOT NULL, `CHECK (price >= 0)` | `db/schema.sql`, matches `MenuItem.java` |
| 16 sample rows | 4 per category: Appetizers, Entrées, Desserts, Drinks | `db/seed-data.sql` |

Category spelling matters: the form and the seed data use **Entrées** (with
the accent). Do not enter "Entrees" as a separate category — it would sort
and group as a different value.

## Prerequisites

- WAMPServer installed and running (green icon), MySQL on port `3306`.
- phpMyAdmin at <http://localhost/phpmyadmin>, signed in as `root` with no
  password (the WAMP default — this is what `application.properties` uses).
- On macOS there is no WAMP: install MySQL with Homebrew instead
  (`brew install mysql`, then `brew services start mysql`). Homebrew's
  MySQL also starts with `root` and no password, so `application.properties`
  needs no change.

## Setup steps (phpMyAdmin)

1. Start WAMPServer and wait for the icon to turn green. If MySQL will not
   start, check that nothing else is using port 3306.
2. Open <http://localhost/phpmyadmin>.
3. Click **Import** in the top bar, choose `db/schema.sql`, click **Go**.
   You should see "Import has been successfully finished".
4. Click the `menu` database in the left sidebar, then **Import** again,
   choose `db/seed-data.sql`, click **Go**.
5. Click the `menu_item` table, then **Browse**. You should see 16 rows.
6. Run the app from the project root:
   ```bash
   ./mvnw spring-boot:run
   ```
   (on Windows: `mvnw.cmd spring-boot:run`)
7. Open <http://localhost:8080/Menu/list>. You should see the 16 items,
   ordered by category, then by item name.

## Setup steps (macOS with Homebrew, alternative to WAMP)

```bash
brew install mysql
brew services start mysql
mysql -u root -e "SELECT VERSION();"  # should print a MySQL version
```

Then import the two files from the project root (no `-p`: the root
password is empty):

```bash
mysql -u root < db/schema.sql
mysql -u root < db/seed-data.sql
mysql -u root -e "SELECT COUNT(*) FROM menu.menu_item;"  -- expect 16
```

If MySQL is already installed but stopped, only the `brew services start
mysql` line is needed. If port 3306 is taken by another server, stop that
server first — the app always connects to port 3306.

## Setup steps (MySQL command line, alternative)

```bash
mysql -u root -p < db/schema.sql
mysql -u root -p menu < db/seed-data.sql
mysql -u root -p -e "SELECT id, name, category, price FROM menu.menu_item ORDER BY category, name;"
```

## Verify with SQL

```sql
USE menu;
SHOW COLUMNS FROM menu_item;   -- expect 5 columns, types as in the table above
SELECT COUNT(*) FROM menu_item; -- expect 16 after seeding, 0 before
SELECT name, category, price FROM menu_item ORDER BY category ASC, name ASC;
```

## If the app will not start

| Symptom | Likely cause | Fix |
| --- | --- | --- |
| `Unknown database 'menu'` | `schema.sql` was never imported | Run step 3 above, then restart the app |
| `Table 'menu.menu_item' doesn't exist` | Schema imported into the wrong database, or only the seed file was run | Import `db/schema.sql` into MySQL (it creates and selects `menu` itself) |
| `Access denied for user 'root'@'localhost'` | MySQL root has a password, or WAMP MySQL is not the server on port 3306 | Set the real password in `application.properties` (`spring.datasource.password`), or start WAMP's MySQL |
| `Communications link failure` / connection refused | MySQL is not running | Start WAMPServer; confirm port 3306 is listening |
| App starts but the list is empty | Seed data was never imported, or the app is pointed at a different database | Import `db/seed-data.sql` and check `spring.datasource.url` is `jdbc:mysql://localhost:3306/menu` |

## Resetting the data

- **Empty the table, keep the structure:** in phpMyAdmin, open
  `menu_item` → **Operations** → **Empty the table**, or run
  `TRUNCATE TABLE menu.menu_item;` — then re-import `db/seed-data.sql`
  if you want the samples back.
- **Delete everything and start over:** drop the database
  (`DROP DATABASE menu;`) and re-import `db/schema.sql`, then
  `db/seed-data.sql`.
- Never run the seed file twice on the same table without emptying it
  first — it inserts duplicate rows with new ids.

## Notes for the team

- The app connects as `root` with an empty password only because that is
  the local WAMP default for this class project. Do not reuse those
  credentials anywhere else, and do not commit a real password to the repo.
- The automated tests (`./mvnw test`) do **not** need MySQL — they run
  against an in-memory H2 database. See `docs/test-plan.md`. Only running
  the real app needs the MySQL setup on this page.
