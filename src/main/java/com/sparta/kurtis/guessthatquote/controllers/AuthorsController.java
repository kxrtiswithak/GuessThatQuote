package com.sparta.kurtis.guessthatquote.controllers;

import com.sparta.kurtis.guessthatquote.entities.AuthorsEntity;
import com.sparta.kurtis.guessthatquote.services.AuthorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthorsController {

    private final AuthorsService authorsService;

    @Autowired
    public AuthorsController(AuthorsService authorsService) {
        this.authorsService = authorsService;
    }

    @GetMapping("/authors")
    public String getAllAuthors(ModelMap modelMap) {
        modelMap.addAttribute("authors", authorsService.getAllAuthors());
        return "authors";
    }

    @GetMapping("/authors/{id}")
    public String getAuthorById(ModelMap modelMap, @PathVariable("id") int id) {
        modelMap.addAttribute("authors/" + id, authorsService.getAuthorById(id));
        return "authors/" + id;
    }

    @DeleteMapping("/authors/{id}")
    public void deleteAuthorById(@PathVariable("id") int id) {
        authorsService.deleteAuthorById(id);
    }

    @PostMapping("/authors")
    public int saveAuthor(@RequestBody AuthorsEntity author) {
        return authorsService.saveAuthor(author);
    }
}
