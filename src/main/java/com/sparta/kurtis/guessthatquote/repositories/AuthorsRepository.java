package com.sparta.kurtis.guessthatquote.repositories;

import com.sparta.kurtis.guessthatquote.entities.AuthorsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorsRepository extends CrudRepository<AuthorsEntity, Integer> {

    Optional<AuthorsEntity> findByAuthorName(String authorName);
}
