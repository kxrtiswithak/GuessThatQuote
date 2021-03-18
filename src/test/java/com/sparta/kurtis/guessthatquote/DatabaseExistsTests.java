package com.sparta.kurtis.guessthatquote;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseExistsTests {

    private static Connection connection = null;
    private static final String URL = "jdbc:mysql://" + System.getenv("JDBC_DB");

    @BeforeAll
    public static void connect() {
        try {
            connection = DriverManager.getConnection(URL, System.getenv("JDBC_USERNAME"), System.getenv("JDBC_PASSWORD"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @DisplayName("check all tables in database exist")
    @ParameterizedTest(name = "check if {0} exists")
    @CsvSource({"quotes", "authors", "users", "guesses"})
    public void checkTableExists(String table) {
        try {
            DatabaseMetaData dbm = connection.getMetaData();
            ResultSet rs = dbm.getTables(null, null, table, null);
            assertTrue(rs.next());
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
