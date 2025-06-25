package com.library.libraryapp.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookID;
    private String title;
    private Double price;
    private String ISBN13;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;
}