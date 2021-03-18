package com.sparta.kurtis.guessthatquote.databases;

import com.sparta.kurtis.guessthatquote.entities.UsersEntity;
import com.sparta.kurtis.guessthatquote.repositories.UsersRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PasswordEncryptionTests {

    @Autowired
    private UsersRepository repo;
    private static UsersEntity createdUser;
    private static final String username = "user";
    private static final String rawPassword = "account";
    private static String firstEncodedPassword;
    private static String encodedPassword;

    @Test
    @Order(Integer.MIN_VALUE)
    @DisplayName("was test user added")
    void addTestUser() {
        UsersEntity user = new UsersEntity();
        user.setUserForename(username);
        user.setUserSurname(rawPassword);
        user.setUsername(username);
        user.setPassword(rawPassword);
        user.setEnabled((byte) 1);
        user.setRole("USER");

        firstEncodedPassword = user.getPassword();

        repo.save(user);
        createdUser = repo.findByUsername(username).get();
        encodedPassword = createdUser.getPassword();;

        assertNotEquals(firstEncodedPassword, encodedPassword);
    }

    @Test
    @Order(Integer.MAX_VALUE)
    @DisplayName("was test user removed")
    void removeTestUser() {
        repo.deleteById(createdUser.getUserId());
    }

    @Test
    @DisplayName("was repo context loaded")
    void contextLoads() {
        assertNotNull(repo);
    }

    @Test
    @DisplayName("is password encrypted")
    void passwordIsEncrypted() {
        System.out.println("raw password: " + rawPassword +
                "\nencoded password: " + encodedPassword);
        assertNotEquals(encodedPassword, rawPassword);
    }

    @Test
    @DisplayName("does raw match when encoded")
    public void passwordMatches() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = new String(rawPassword);
        assertFalse(encoder.matches(rawPassword, encodedPassword));

    }
}
