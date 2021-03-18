package com.sparta.kurtis.guessthatquote.services;

import com.sparta.kurtis.guessthatquote.entities.UsersEntity;
import com.sparta.kurtis.guessthatquote.repositories.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public Iterable<UsersEntity> getAllUsers() {
        return usersRepository.findAll();
    }

    public Optional<UsersEntity> getUserById(int id) {
        return usersRepository.findById(id);
    }

    public int saveUser(UsersEntity user) {
        return usersRepository.save(user).getUserId();
    }

    public void deleteUserById(int id) {
        usersRepository.deleteById(id);
    }
}
