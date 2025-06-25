package com.library.libraryapp.controller;

import com.library.libraryapp.Entity.Book;
import com.library.libraryapp.dto.BookRequest;
import com.library.libraryapp.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService = null;

    @PostMapping
    public Book createBook(@RequestBody BookRequest request) {
        return bookService.createBook(request);
    }

    // Tüm kitapları listele
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    // "A" ile başlayan kitapları getir
    @GetMapping("/starts-with-a")
    public List<Book> getBooksStartingWithA() {
        return bookService.getBooksStartingWithA();
    }

    // 2023 sonrası basılan kitapları getir
    @GetMapping("/published-after-2023")
    public List<Book> getBooksAfter2023() {
        return bookService.getBooksAfter2023();
    }

    // Kitap sil
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

    // Tek kitap getir
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }
}
