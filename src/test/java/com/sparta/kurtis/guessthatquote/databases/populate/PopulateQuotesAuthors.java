package com.sparta.kurtis.guessthatquote.databases.populate;

import com.sparta.kurtis.guessthatquote.entities.AuthorsEntity;
import com.sparta.kurtis.guessthatquote.entities.QuotesEntity;
import com.sparta.kurtis.guessthatquote.repositories.AuthorsRepository;
import com.sparta.kurtis.guessthatquote.repositories.QuotesRepository;
import com.sparta.kurtis.guessthatquote.util.importdata.Injector;
import com.sparta.kurtis.guessthatquote.util.importdata.QuotablePOJOItem;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Iterator;
import java.util.List;

@SpringBootTest
public class PopulateQuotesAuthors {

    @Autowired
    private AuthorsRepository authorsRepo;

    @Autowired
    private QuotesRepository quotesRepo;

    private static List<QuotablePOJOItem> quotablePOJO;

    @Test
    @BeforeAll
    static void importPOJO() {
        Injector injector = new Injector();
        quotablePOJO = injector.jsonToPOJO();

        quotablePOJO.forEach(
                (item) -> System.out.println(item.getAuthor() + " - " + item.getContent())
        );
    }

    @Test
    void POJOToDatabase() {
        Iterator<QuotablePOJOItem> iter = quotablePOJO.iterator();
        AuthorsEntity author;
        QuotesEntity quote;

        while (iter.hasNext()) {
            QuotablePOJOItem POJOQuote = iter.next();
            String authorName = POJOQuote.getAuthor();
            String authorQuote = POJOQuote.getContent();

            if (authorsRepo.findByAuthorName(authorName).isEmpty()) {
                author = new AuthorsEntity();
                author.setAuthorName(authorName);
                authorsRepo.save(author);
            }

            if (quotesRepo.findByQuote(authorQuote).isEmpty()) {
                int authorId = authorsRepo.findByAuthorName(authorName).get().getAuthorId();

                quote = new QuotesEntity();
                quote.setQuote(authorQuote);
                quote.setAuthorId(authorId);
                quotesRepo.save(quote);
            }
        }

        System.out.println("success!");
    }

}
