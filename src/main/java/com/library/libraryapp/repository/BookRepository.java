package com.library.libraryapp.repository;

import com.library.libraryapp.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleStartingWith(String letter);

    @Query("SELECT b FROM Book b WHERE b.publicationYear > 2023")  // publicationYear özelliği Book entity'sine eklenmeli
    List<Book> findBooksPublishedAfter2023();
}