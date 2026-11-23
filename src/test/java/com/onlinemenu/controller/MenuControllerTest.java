package com.onlinemenu.controller;

import com.example.onlinemenu.controller.MenuController;
import com.example.onlinemenu.entity.MenuItem;
import com.example.onlinemenu.service.MenuService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(MenuController.class)
class MenuControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private MenuService menuService;

    private static MenuItem item(int id, String name) {
        MenuItem item = new MenuItem(name, "Appetizers", "Romaine, parmesan, croutons", new BigDecimal("8.50"));
        item.setId(id);
        return item;
    }

    @Test
    void listRendersMenuListWithItems() throws Exception {
        when(menuService.findAll()).thenReturn(List.of(item(1, "Caesar Salad"), item(2, "Bruschetta")));

        mockMvc.perform(get("/Menu/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("menu/list-menu"))
                .andExpect(model().attribute("menuItems", hasSize(2)));
    }

    @Test
    void viewAddFormOpensBlankForm() throws Exception {
        mockMvc.perform(get("/Menu/viewAddForm"))
                .andExpect(status().isOk())
                .andExpect(view().name("menu/menu-form"))
                .andExpect(model().attributeExists("menuItem"));
    }

    @Test
    void viewUpdateFormLoadsExistingItem() throws Exception {
        when(menuService.findById(1)).thenReturn(item(1, "Caesar Salad"));

        mockMvc.perform(get("/Menu/viewUpdateForm").param("itemId", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("menu/menu-form"))
                .andExpect(model().attributeExists("menuItem"));
    }

    @Test
    void saveRedirectsToList() throws Exception {
        mockMvc.perform(post("/Menu/save")
                        .param("name", "Garlic Bread")
                        .param("category", "Appetizers")
                        .param("description", "Toasted bread, garlic butter")
                        .param("price", "5.50"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/Menu/list"));

        verify(menuService).save(org.mockito.ArgumentMatchers.any(MenuItem.class));
    }

    @Test
    void deleteRedirectsToList() throws Exception {
        mockMvc.perform(get("/Menu/delete").param("itemId", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/Menu/list"));

        verify(menuService).deleteById(1);
    }
}
