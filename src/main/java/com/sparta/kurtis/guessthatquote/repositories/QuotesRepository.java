package com.sparta.kurtis.guessthatquote.repositories;

import com.sparta.kurtis.guessthatquote.entities.QuotesEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuotesRepository extends CrudRepository<QuotesEntity, Integer> {

    Optional<QuotesEntity> findByQuote(String quote);
}
