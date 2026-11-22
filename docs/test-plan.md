# Test Plan — CRUD Testing

Owner: Fakhruddin Shaik (Tester) · Course: IT4045C – Final Project

## Automated tests (no MySQL needed)

Run from the project root:

```bash
./mvnw test
```

The tests use an in-memory H2 database
(`src/test/resources/application.properties`), so they pass on any machine
without MySQL running. They do **not** prove the MySQL setup works — the
manual checks below do that.

| Test class | What it covers |
| --- | --- |
| `OnlineMenuWebApplicationTests` | The Spring context loads |
| `MenuItemRepositoryTest` | Save, find by id, find-all sorted by category then name, update, delete, and missing-id behaviour at the database layer |
| `MenuServiceTest` | The service delegates to the repository and throws `IllegalArgumentException` for an unknown id |
| `MenuControllerTest` | `GET /Menu/list` renders the list, `GET /Menu/viewAddForm` opens a blank form, `GET /Menu/viewUpdateForm` loads an item, `POST /Menu/save` redirects to the list, `GET /Menu/delete` redirects to the list |

If `./mvnw test` fails, fix the code first — do not skip to manual testing
and call the feature done.

## Manual CRUD checks (live MySQL, real app)

Prerequisites: database set up per `docs/database-setup.md`, seed data
imported (16 rows), app running with `./mvnw spring-boot:run`.

| # | Check | Steps | Expected result |
| --- | --- | --- | --- |
| TC-01 | Read — list | Open <http://localhost:8080/Menu/list> | 16 items, ordered by category (Appetizers, Desserts, Drinks, Entrées) then name; count in the header says "16 menu items"; prices show with 2 decimals |
| TC-02 | Create | Click **+ Add New Menu Item**, enter name `Mozzarella Sticks`, category `Appetizers`, description `Breaded mozzarella, marinara sauce`, price `6.95`, click **Save** | Redirected to the list; Mozzarella Sticks appears under Appetizers; 17 items total |
| TC-03 | Update | On Mozzarella Sticks, click **Update**, change the price to `7.25`, click **Save** | List shows `7.25`; no duplicate row is created; the item keeps the same id |
| TC-04 | Delete | On Mozzarella Sticks, click **Delete**, click **OK** in the browser confirmation | Row disappears; 16 items total; refresh the page and it stays deleted |
| TC-05 | Delete — cancel | On any item, click **Delete**, click **Cancel** | Item is still in the list |
| TC-06 | Validation | Try to save a form with the name blank, or a negative price | The browser blocks the submit (required / min="0" on the form); nothing is saved |
| TC-07 | Persistence | After TC-02–TC-04, stop the app (`Ctrl+C`) and start it again, then open the list | All saved changes are still there — they live in MySQL, not in memory |
| TC-08 | Category spelling | Add an item and pick the category from the radio buttons only | It lands in one of the four real categories; no free-text "Entrees" (no accent) category appears |

Record each check as Pass / Fail with the date and who ran it. A fail on
TC-01 to TC-04 blocks the demo — those are the four CRUD operations the
project is graded on.

## Known limits of this test plan

- There is no login, so anyone who can open the app can edit the menu.
  That is out of scope for this project, not a bug to fix here.
- Delete is a `GET` request (`/Menu/delete?itemId=...`), so a bookmarked
  or reloaded delete link would delete again. The browser confirmation is
  the only guard. Do not share delete links.
- Prices are stored as `DECIMAL(8,2)`, so the largest price the table
  accepts is `999999.99`.
