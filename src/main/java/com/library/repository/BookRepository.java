package com.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.library.Entity.Book;
import com.library.Entity.Author;
import com.library.Entity.Publisher;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleStartingWith(String letter);

    @Query("SELECT b FROM Book b WHERE b.publicationYear > 2023") // publicationYear özelliği Book entity'sine eklenmeli
    List<Book> findBooksPublishedAfter2023();

    @Query("SELECT b.bookID FROM Book b ORDER BY b.bookID ASC")
    List<Long> findAllIdsSorted();

    long countByAuthor(Author author);

    long countByPublisher(Publisher publisher);

}