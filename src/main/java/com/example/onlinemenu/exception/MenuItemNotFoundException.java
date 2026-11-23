package com.example.onlinemenu.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Thrown when no menu item exists for the id that was asked for.
// @ResponseStatus makes Spring answer with 404 Not Found instead of a 500 error.
@ResponseStatus(HttpStatus.NOT_FOUND)
public class MenuItemNotFoundException extends RuntimeException {

    public MenuItemNotFoundException(int id) {
        super("Menu item not found with id: " + id);
    }
}
