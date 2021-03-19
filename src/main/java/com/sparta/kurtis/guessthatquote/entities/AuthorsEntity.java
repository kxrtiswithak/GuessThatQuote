package com.sparta.kurtis.guessthatquote.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "authors", schema = "guess_that_quote", catalog = "")
public class AuthorsEntity {
    private int authorId;
    private String authorName;

    @Id
    @Column(name = "author_id", insertable = false, updatable = false)
    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    @Basic
    @Column(name = "author_name", unique = true)
    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorsEntity that = (AuthorsEntity) o;
        return authorId == that.authorId && Objects.equals(authorName, that.authorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorId, authorName);
    }
}
