package com.example.onlinemenu.service;

import com.example.onlinemenu.entity.MenuItem;
import com.example.onlinemenu.repository.MenuItemRepository;
import com.example.onlinemenu.service.MenuServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MenuServiceTest {

    @Mock
    private MenuItemRepository repository;

    @InjectMocks
    private MenuServiceImpl service;

    private static MenuItem item(int id, String name) {
        MenuItem item = new MenuItem(name, "Appetizers", "Test item", new BigDecimal("8.50"));
        item.setId(id);
        return item;
    }

    @Test
    void findAllReturnsRepositoryResultsInOrder() {
        List<MenuItem> expected = List.of(item(1, "Bruschetta"), item(2, "Caesar Salad"));
        when(repository.findAllByOrderByCategoryAscNameAsc()).thenReturn(expected);

        assertThat(service.findAll()).isEqualTo(expected);
        verify(repository).findAllByOrderByCategoryAscNameAsc();
    }

    @Test
    void findByIdReturnsTheItemWhenItExists() {
        when(repository.findById(1)).thenReturn(Optional.of(item(1, "Caesar Salad")));

        assertThat(service.findById(1).getName()).isEqualTo("Caesar Salad");
    }

    @Test
    void findByIdThrowsForUnknownId() {
        when(repository.findById(99)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.findById(99))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("99");
    }

    @Test
    void saveAndDeleteDelegateToRepository() {
        MenuItem item = item(0, "Garlic Bread");

        service.save(item);
        service.deleteById(1);

        verify(repository).save(item);
        verify(repository).deleteById(1);
    }
}
