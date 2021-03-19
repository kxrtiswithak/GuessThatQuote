package com.sparta.kurtis.guessthatquote.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "quotes", schema = "guess_that_quote", catalog = "")
public class QuotesEntity {
    private int quoteId;
    private String quote;
    private int authorId;
    private AuthorsEntity authorsByAuthorId;

    @Id
    @Column(name = "quote_id", insertable = false, updatable = false)
    public int getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(int quoteId) {
        this.quoteId = quoteId;
    }

    @Basic
    @Column(name = "quote")
    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    @Basic
    @Column(name = "author_id")
    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuotesEntity that = (QuotesEntity) o;
        return quoteId == that.quoteId && authorId == that.authorId && Objects.equals(quote, that.quote);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quoteId, quote, authorId);
    }

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "author_id", nullable = false, insertable = false, updatable = false)
    public AuthorsEntity getAuthorsByAuthorId() {
        return authorsByAuthorId;
    }

    public void setAuthorsByAuthorId(AuthorsEntity authorsByAuthorId) {
        this.authorsByAuthorId = authorsByAuthorId;
    }
}
