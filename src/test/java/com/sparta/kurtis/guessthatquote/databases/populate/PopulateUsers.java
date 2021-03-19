package com.sparta.kurtis.guessthatquote.databases.populate;

import com.sparta.kurtis.guessthatquote.entities.UsersEntity;
import com.sparta.kurtis.guessthatquote.repositories.UsersRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PopulateUsers {

    @Autowired
    UsersRepository usersRepo;

    @Order(Integer.MIN_VALUE)
    @ParameterizedTest(name = "add {2} {3} to users table")
    @CsvSource({
            "admin, admin, mister, administrator, ADMIN, 1",
            "bossman, bossman, chicken shop, bossman, ADMIN, 1",
            "peasant, peasant, lowly, peasant, USER, 1",
            "commoner, commoner, another, commoner, USER, 1",
            "mortal, mortal, mere, mortal, USER, 1",
            "gitgod, gitgod, god of, git, USER, 1",
            "dropout, dropout, high-school, dropout, USER, 1",
            "spartan, spartan, spartan, trainee, USER, 1",
            "graduate, graduate, university, graduate, USER, 1",
            "alumni, alumni, education, alumni, USER, 0",
            "criminal, criminal, full-time, criminal, USER, 0"
    })
    void addUsersToDatabase(String username, String password, String userForename, String userSurname, String role, byte enabled) {
        UsersEntity user = new UsersEntity();
        user.setUsername(username);
        user.setPassword(password);
        user.setUserForename(userForename);
        user.setUserSurname(userSurname);
        user.setRole(role);
        user.setEnabled(enabled);
        usersRepo.save(user);
    }

    @Order(Integer.MAX_VALUE - 1)
    @DisplayName("users ARE in table")
    @ParameterizedTest(name = "username {0} in users table")
    @CsvSource({
            "admin",
            "bossman",
            "peasant",
            "commoner",
            "mortal",
            "gitgod",
            "dropout",
            "spartan",
            "graduate",
            "alumni",
            "criminal"
    })
    void checkUsersExist(String username) {
        assertTrue(usersRepo.findByUsername(username).isPresent());
    }

    @Order(Integer.MAX_VALUE)
    @DisplayName("users NOT in table")
    @ParameterizedTest(name = "username {0} NOT in users table")
    @CsvSource({
            "ascavvzxz",
            "saasasas",
            "qwertyu",
            "coxzcxmoner",
            "zxzxzxzxz",
            "sonofgit",
            "notadropout",
            "persian",
            "failure",
            "student",
            "lawabidingcitizen"
    })
    void checkUsersDontExist(String username) {
        assertTrue(usersRepo.findByUsername(username).isEmpty());
    }
}
