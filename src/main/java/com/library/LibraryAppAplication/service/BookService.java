package com.library.libraryapp.service;

import com.library.libraryapp.dto.BookRequest;
import com.library.libraryapp.Entity.Author;
import com.library.libraryapp.Entity.Book;
import com.library.libraryapp.Entity.Publisher;
import com.library.libraryapp.repository.AuthorRepository;
import com.library.libraryapp.repository.BookRepository;
import com.library.libraryapp.repository.PublisherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;

    public BookService() {
        this.bookRepository = null;
        this.authorRepository = null;
        this.publisherRepository = null;
    }

    public Book createBook(BookRequest request) {
        Publisher publisher = new Publisher();
        publisher.setPublisherName(request.getPublisherName());
        publisher = publisherRepository.save(publisher);

        Book book = new Book();
        book.setTitle(request.getTitle());
        book.setPrice(request.getPrice());
        book.setISBN13(request.getISBN13());
        book.setPublisher(publisher);
        book = bookRepository.save(book);

        Author author = new Author();
        author.setAuthorNameSurname(request.getAuthorNameSurname());
        author.setBook(book);
        authorRepository.save(author);

        return book;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> getBooksStartingWithA() {
        return bookRepository.findByTitleStartingWith("A");
    }

    public List<Book> getBooksAfter2023() {
        return bookRepository.findBooksPublishedAfter2023();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}