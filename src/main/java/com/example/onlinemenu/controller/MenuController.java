package com.example.onlinemenu.controller;

import com.example.onlinemenu.entity.MenuItem;
import com.example.onlinemenu.service.MenuService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/Menu")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    // READ: show all menu items
    @GetMapping("/list")
    public String listMenuItems(Model model) {
        List<MenuItem> menuItems = menuService.findAll();
        model.addAttribute("menuItems", menuItems);
        return "list-menu";
    }

    // CREATE: open a blank form
    @GetMapping("/viewAddForm")
    public String viewAddForm(Model model) {
        model.addAttribute("menuItem", new MenuItem());
        return "menu-form";
    }

    // UPDATE: open the form pre-filled with an existing item
    @GetMapping("/viewUpdateForm")
    public String viewUpdateForm(@RequestParam("itemId") int id, Model model) {
        model.addAttribute("menuItem", menuService.findById(id));
        return "menu-form";
    }

    // CREATE / UPDATE: save a new or edited item
    @PostMapping("/save")
    public String saveMenuItem(@Valid @ModelAttribute("menuItem") MenuItem menuItem,
                               BindingResult bindingResult) {
        // if the form has errors, show the form again with the error messages
        if (bindingResult.hasErrors()) {
            return "menu-form";
        }
        menuService.save(menuItem);
        return "redirect:/Menu/list";
    }

    // DELETE: remove an item, then return to the list
    @PostMapping("/delete")
    public String delete(@RequestParam("itemId") int id) {
        menuService.deleteById(id);
        return "redirect:/Menu/list";
    }
}
