package com.library.controller;

import com.library.client.GoogleBooksClient;
import com.library.dto.GoogleBooksResponse;
import com.library.dto.BookRequest;
import com.library.Entity.Book;
import com.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final GoogleBooksClient googleBooksClient;

    @PostMapping("/add")
    public Book addBook(@RequestBody BookRequest request) {
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

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody BookRequest request) {
        return bookService.updateBook(id, request);
    }

    @GetMapping("/search-google")
    public List<BookRequest> searchBooksOnGoogle(@RequestParam String title) {
        GoogleBooksResponse response = googleBooksClient.searchBooks(title);
        List<BookRequest> result = new ArrayList<>();
        if (response.getItems() != null) {
            for (GoogleBooksResponse.Item item : response.getItems()) {
                BookRequest book = new BookRequest();
                book.setTitle(item.getVolumeInfo().getTitle());
                if (item.getVolumeInfo().getAuthors() != null && !item.getVolumeInfo().getAuthors().isEmpty()) {
                    book.setAuthorNameSurname(item.getVolumeInfo().getAuthors().get(0));
                }
                book.setPublisherName(item.getVolumeInfo().getPublisher());
                // ISBN13 bul
                if (item.getVolumeInfo().getIndustryIdentifiers() != null) {
                    for (GoogleBooksResponse.IndustryIdentifier id : item.getVolumeInfo().getIndustryIdentifiers()) {
                        if ("ISBN_13".equals(id.getType())) {
                            book.setISBN13(id.getIdentifier());
                            break;
                        }
                    }
                }
                // Fiyat
                if (item.getSaleInfo() != null && item.getSaleInfo().getRetailPrice() != null) {
                    book.setPrice(item.getSaleInfo().getRetailPrice().getAmount());
                }
                result.add(book);
            }
        }
        return result;
    }
}
