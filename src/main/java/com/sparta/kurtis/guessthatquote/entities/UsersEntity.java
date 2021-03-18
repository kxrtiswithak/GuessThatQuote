package com.sparta.kurtis.guessthatquote.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users", schema = "guess_that_quote", catalog = "")
public class UsersEntity {
    private int userId;
    private String username;
    private String password;
    private String userForename;
    private String userSurname;
    private String role;
    private byte enabled;

    @Id
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "user_forename")
    public String getUserForename() {
        return userForename;
    }

    public void setUserForename(String userForename) {
        this.userForename = userForename;
    }

    @Basic
    @Column(name = "user_surname")
    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    @Basic
    @Column(name = "role")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Basic
    @Column(name = "enabled")
    public byte getEnabled() {
        return enabled;
    }

    public void setEnabled(byte enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersEntity that = (UsersEntity) o;
        return userId == that.userId && enabled == that.enabled && Objects.equals(username, that.username) && Objects.equals(password, that.password) && Objects.equals(userForename, that.userForename) && Objects.equals(userSurname, that.userSurname) && Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, password, userForename, userSurname, role, enabled);
    }
}
