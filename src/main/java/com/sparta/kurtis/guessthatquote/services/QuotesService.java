package com.sparta.kurtis.guessthatquote.services;

import com.sparta.kurtis.guessthatquote.entities.QuotesEntity;
import com.sparta.kurtis.guessthatquote.repositories.QuotesRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuotesService {

    private final QuotesRepository quotesRepository;

    public QuotesService(QuotesRepository quotesRepository) {
        this.quotesRepository = quotesRepository;
    }

    public Iterable<QuotesEntity> getAllQuotes() {
        return quotesRepository.findAll();
    }

    public Optional<QuotesEntity> getQuoteById(int id) {
        return quotesRepository.findById(id);
    }

    public int saveQuote(QuotesEntity quote) {
        return quotesRepository.save(quote).getQuoteId();
    }

    public void deleteQuoteById(int id) {
        quotesRepository.deleteById(id);
    }

    public long getCount() {
        return quotesRepository.count();
    }

    // public boolean doesQuoteExist(String quote) {
    //     quotesRepository.findByQuote(String quote);
    // }

}