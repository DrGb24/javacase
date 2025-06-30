package com.library.service;

import com.library.Entity.Author;
import com.library.Entity.Book;
import com.library.Entity.Publisher;
import com.library.dto.BookRequest;
import com.library.repository.AuthorRepository;
import com.library.repository.BookRepository;
import com.library.repository.PublisherRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;
    private final AuthorRepository authorRepository;

    public Book createBook(BookRequest request) {
        Publisher publisher = publisherRepository.findByPublisherName(request.getPublisherName())
                .orElseGet(() -> {
                    Publisher p = new Publisher();
                    p.setPublisherName(request.getPublisherName());
                    return publisherRepository.save(p);
                });

        Author author = authorRepository.findByAuthorNameSurname(request.getAuthorNameSurname())
                .orElseGet(() -> {
                    Author a = new Author();
                    a.setAuthorNameSurname(request.getAuthorNameSurname());
                    return authorRepository.save(a);
                });

        // En küçük kullanılmayan bookID'yi bul
        Long minId = 1L;
        List<Long> ids = bookRepository.findAllIdsSorted();
        for (Long id : ids) {
            if (id.equals(minId)) {
                minId++;
            } else {
                break;
            }
        }

        Book book = new Book();
        book.setBookID(minId); // bookID'yi elle set et
        book.setTitle(request.getTitle());
        book.setPrice(request.getPrice());
        book.setISBN13(request.getISBN13());
        book.setPublicationYear(request.getPublicationYear());
        book.setPublisher(publisher);
        book.setAuthor(author);

        return bookRepository.save(book);
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

    public void deleteBook(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        Author author = book.getAuthor();
        Publisher publisher = book.getPublisher();

        bookRepository.delete(book);

        // Author kesinlikle silinsin
        authorRepository.delete(author);

        // Publisher başka kitapta kullanılmıyorsa silinsin
        if (bookRepository.countByPublisher(publisher) == 0) {
            publisherRepository.delete(publisher);
        }
    }

    public Book updateBook(Long id, BookRequest request) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        book.setTitle(request.getTitle());
        book.setPrice(request.getPrice());
        book.setISBN13(request.getISBN13());
        book.setPublicationYear(request.getPublicationYear());
        // publisher güncellemesi gerekiyorsa ekleyin
        return bookRepository.save(book);
    }
}