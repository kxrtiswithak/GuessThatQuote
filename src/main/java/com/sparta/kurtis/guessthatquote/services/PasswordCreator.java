package com.sparta.kurtis.guessthatquote.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordCreator {
    private static PasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String encode(String password) {
        return encoder.encode(password);
    }

    public static void main(String[] args) {
        // System.out.println(encode("test"));
        System.out.println(encoder.matches("test", "$2a$10$63ywLsTAaN5cm.ONnZN6e.LfPn/oD28r3hcLcP88l049Wbs7f2Ssu"));
    }
}
