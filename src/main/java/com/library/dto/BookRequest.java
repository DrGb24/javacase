package com.library.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookRequest {
    private String title;
    private Double price;
    private String ISBN13;
    private String publisherName;
    private String authorNameSurname;
    private Integer publicationYear;
}