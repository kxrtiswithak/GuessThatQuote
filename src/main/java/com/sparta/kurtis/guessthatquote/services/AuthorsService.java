package com.sparta.kurtis.guessthatquote.services;

import com.sparta.kurtis.guessthatquote.entities.AuthorsEntity;
import com.sparta.kurtis.guessthatquote.repositories.AuthorsRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorsService {

    private final AuthorsRepository authorsRepository;

    public AuthorsService(AuthorsRepository authorsRepository) {
        this.authorsRepository = authorsRepository;
    }

    public Iterable<AuthorsEntity> getAllAuthors() {
        return authorsRepository.findAll();
    }

    public Optional<AuthorsEntity> getAuthorById(int id) {
        return authorsRepository.findById(id);
    }

    public int saveAuthor(AuthorsEntity author) {
        return authorsRepository.save(author).getAuthorId();
    }

    public void deleteAuthorById(int id) {
        authorsRepository.deleteById(id);
    }

}