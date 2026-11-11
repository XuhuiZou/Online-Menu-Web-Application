package com.onlinemenu.service;

import com.onlinemenu.entity.MenuItem;

import java.util.List;

public interface MenuService {

    List<MenuItem> findAll();

    MenuItem findById(int id);

    void save(MenuItem menuItem);

    void deleteById(int id);
}
