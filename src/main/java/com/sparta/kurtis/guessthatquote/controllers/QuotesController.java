package com.sparta.kurtis.guessthatquote.controllers;

import com.sparta.kurtis.guessthatquote.entities.QuotesEntity;
import com.sparta.kurtis.guessthatquote.services.QuotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class QuotesController {

    private final QuotesService quotesService;

    @Autowired
    public QuotesController(QuotesService quotesService) {
        this.quotesService = quotesService;
    }

    @GetMapping("/quotes")
    public String getAllQuotes(ModelMap modelMap) {
        modelMap.addAttribute("quotes", quotesService.getAllQuotes());
        return "quotes";
    }

    @GetMapping("/quotes/{id}")
    public String getQuoteById(ModelMap modelMap, @PathVariable("id") int id) {
        modelMap.addAttribute("quotes/" + id, quotesService.getQuoteById(id));
        return "quotes/" + id;
    }

    @DeleteMapping("/quotes/{id}")
    public void deleteQuoteById(@PathVariable("id") int id) {
        quotesService.deleteQuoteById(id);
    }

    @PostMapping("/quotes")
    public int saveQuote(@RequestBody QuotesEntity quote) {
        return quotesService.saveQuote(quote);
    }
}
