package com.sparta.kurtis.guessthatquote.controllers;

import com.sparta.kurtis.guessthatquote.entities.UsersEntity;
import com.sparta.kurtis.guessthatquote.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class UsersController {

    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/users")
    public String getAllUsers(ModelMap modelMap) {
        modelMap.addAttribute("users", usersService.getAllUsers());
        return "users";
    }

    @GetMapping("/users/{id}")
    public String getUserById(ModelMap modelMap, @PathVariable("id") int id) {
        modelMap.addAttribute("users/" + id, usersService.getUserById(id));
        return "users/" + id;
    }

    @DeleteMapping("/users/{id}")
    public void deleteUserById(@PathVariable("id") int id) {
        usersService.deleteUserById(id);
    }

    @PostMapping("/users")
    public int saveUser(@RequestBody UsersEntity user) {
        return usersService.saveUser(user);
    }
}
