package com.sparta.kurtis.guessthatquote.controllers;

import com.sparta.kurtis.guessthatquote.entities.AuthorsEntity;
import com.sparta.kurtis.guessthatquote.entities.QuotesEntity;
import com.sparta.kurtis.guessthatquote.entities.UsersEntity;
import com.sparta.kurtis.guessthatquote.services.AuthorsService;
import com.sparta.kurtis.guessthatquote.services.QuotesService;
import com.sparta.kurtis.guessthatquote.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Controller
public class WelcomeController {

    private final UsersService usersService;
    private final QuotesService quotesService;
    private final AuthorsService authorsService;

    @Autowired
    public WelcomeController(UsersService usersService, QuotesService quotesService, AuthorsService authorsService) {
        this.usersService = usersService;
        this.quotesService = quotesService;
        this.authorsService = authorsService;
    }

    @GetMapping("/")
    public String welcome(ModelMap modelMap, HttpServletRequest request) {
        String username = request.getRemoteUser();
        UsersEntity currentUser = usersService.findByUsername(username).get();

        List<QuotesEntity> allQuotes = (List<QuotesEntity>) quotesService.getAllQuotes();
        QuotesEntity randomQuote = allQuotes.get(getRandomIndex(allQuotes.size()));

        List<AuthorsEntity> authors = new ArrayList<>();
        authors.add(authorsService.getAuthorById(randomQuote.getAuthorId()).get());
        List<AuthorsEntity> allAuthors = (List<AuthorsEntity>) authorsService.getAllAuthors();

        while (authors.size() < 4) {
            AuthorsEntity randomAuthor = allAuthors.get(getRandomIndex(allAuthors.size()));
            if (!authors.contains(randomAuthor)) {
                authors.add(randomAuthor);
            }
        }
        Collections.shuffle(authors);

        String[] authorIds = new String[4];
        for (int i = 0; i < authors.size(); i++) {
            authorIds[i] = authors.get(i).getAuthorId() + "/" + randomQuote.getAuthorId();
        }

        modelMap.addAttribute("authors", authors);
        modelMap.addAttribute("authorIds", authorIds);
        modelMap.addAttribute("quote", randomQuote);
        modelMap.addAttribute("user", currentUser);
        return "index";
    }

    @GetMapping("/author/{guessid}/{correctid}")
    public String authorSelected(HttpServletRequest request, ModelMap modelMap, @PathVariable("guessid") int guessedAuthorId, @PathVariable("correctid") int correctAuthorId) {
        boolean success = guessedAuthorId == correctAuthorId;
        String username = request.getRemoteUser();

        modelMap.addAttribute("user", usersService.findByUsername(username).get());
        modelMap.addAttribute("guessedAuthor", authorsService.getAuthorById(guessedAuthorId).get());
        modelMap.addAttribute("correctAuthor", authorsService.getAuthorById(correctAuthorId).get());
        modelMap.addAttribute("success", success);

        return "guess";
    }

    public int getRandomIndex(int size) {
        Random rand = new Random();
        return rand.nextInt(size);
    }

}




























