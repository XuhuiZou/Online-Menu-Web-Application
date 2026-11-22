package com.onlinemenu.repository;

import com.onlinemenu.entity.MenuItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class MenuItemRepositoryTest {

    @Autowired
    private MenuItemRepository repository;

    private static MenuItem item(String name, String category, String description, String price) {
        return new MenuItem(name, category, description, new BigDecimal(price));
    }

    @Test
    void saveAndFindById() {
        MenuItem saved = repository.save(item("Caesar Salad", "Appetizers", "Romaine, parmesan, croutons", "8.50"));

        assertThat(saved.getId()).isGreaterThan(0);

        MenuItem found = repository.findById(saved.getId()).orElseThrow();
        assertThat(found.getName()).isEqualTo("Caesar Salad");
        assertThat(found.getCategory()).isEqualTo("Appetizers");
        assertThat(found.getPrice()).isEqualByComparingTo("8.50");
    }

    @Test
    void findAllIsOrderedByCategoryThenName() {
        repository.save(item("Margherita Pizza", "Entrées", "Tomato, mozzarella, basil", "12.00"));
        repository.save(item("Caesar Salad", "Appetizers", "Romaine, parmesan, croutons", "8.50"));
        repository.save(item("Chocolate Cake", "Desserts", "Layered cake with ganache", "6.75"));
        repository.save(item("Bruschetta", "Appetizers", "Grilled bread, tomato, basil", "7.25"));

        List<String> names = repository.findAllByOrderByCategoryAscNameAsc()
                .stream().map(MenuItem::getName).toList();

        assertThat(names).containsExactly(
                "Bruschetta", "Caesar Salad", "Chocolate Cake", "Margherita Pizza");
    }

    @Test
    void updateChangesTheStoredRow() {
        MenuItem saved = repository.save(item("Lemonade", "Drinks", "Fresh lemons", "3.50"));
        saved.setPrice(new BigDecimal("3.95"));
        repository.save(saved);

        assertThat(repository.findById(saved.getId()).orElseThrow().getPrice())
                .isEqualByComparingTo("3.95");
        assertThat(repository.count()).isEqualTo(1);
    }

    @Test
    void deleteRemovesTheRow() {
        MenuItem saved = repository.save(item("Tiramisu", "Desserts", "Coffee, mascarpone", "7.50"));

        repository.deleteById(saved.getId());

        assertThat(repository.findById(saved.getId())).isEmpty();
        assertThat(repository.count()).isZero();
    }

    @Test
    void findByIdReturnsEmptyForUnknownId() {
        assertThat(repository.findById(9999)).isEmpty();
    }
}
