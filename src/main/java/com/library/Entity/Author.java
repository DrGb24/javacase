package com.library.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authorID;
    private String authorNameSurname;

    @OneToMany(mappedBy = "author")
    private List<Book> books;

    public void setAuthorNameSurname(String authorNameSurname) {
        this.authorNameSurname = authorNameSurname;
    }

    public String getAuthorNameSurname() {
        return authorNameSurname;
    }
}
