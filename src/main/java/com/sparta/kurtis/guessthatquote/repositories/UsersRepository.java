package com.sparta.kurtis.guessthatquote.repositories;

import com.sparta.kurtis.guessthatquote.entities.UsersEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends CrudRepository<UsersEntity, Integer> {

    Optional<UsersEntity> findByUsername(String username);
}
