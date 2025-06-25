package com.library.libraryapp.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authorID;
    private String authorNameSurname;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
}
