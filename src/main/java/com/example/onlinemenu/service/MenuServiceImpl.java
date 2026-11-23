package com.example.onlinemenu.service;

import com.example.onlinemenu.entity.MenuItem;
import com.example.onlinemenu.exception.MenuItemNotFoundException;
import com.example.onlinemenu.repository.MenuItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    private final MenuItemRepository menuItemRepository;

    public MenuServiceImpl(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    @Override
    public List<MenuItem> findAll() {
        return menuItemRepository.findAllByOrderByCategoryAscNameAsc();
    }

    @Override
    public MenuItem findById(int id) {
        return menuItemRepository.findById(id)
                .orElseThrow(() -> new MenuItemNotFoundException(id));
    }

    @Override
    public void save(MenuItem menuItem) {
        menuItemRepository.save(menuItem);
    }

    @Override
    public void deleteById(int id) {
        menuItemRepository.deleteById(id);
    }
}
