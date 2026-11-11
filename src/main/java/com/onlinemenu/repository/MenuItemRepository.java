package com.onlinemenu.repository;

import com.onlinemenu.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuItemRepository extends JpaRepository<MenuItem, Integer> {

    // Returns all menu items sorted by category, then by item name
    List<MenuItem> findAllByOrderByCategoryAscNameAsc();
}
