package com.library.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Book {
    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY) // Bunu kaldırın!
    private Long bookID;
    private String title;
    private Double price;
    private String ISBN13;
    private Integer publicationYear;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    // getter ve setter ekleyin
    public Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}