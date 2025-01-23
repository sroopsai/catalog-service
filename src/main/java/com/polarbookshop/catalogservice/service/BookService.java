package com.polarbookshop.catalogservice.service;

import com.polarbookshop.catalogservice.exception.BookAlreadyExistsException;
import com.polarbookshop.catalogservice.exception.BookNotFoundException;
import com.polarbookshop.catalogservice.model.Book;
import com.polarbookshop.catalogservice.persistence.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Iterable<Book> fetchAllBooksFromCatalog() {
        return bookRepository.fetchAll();
    }

    public Book getBookFromCatalog(String isbn) {
        return bookRepository.getByIsbn(isbn).orElseThrow(() -> new BookNotFoundException(isbn));
    }

    public Book addToCatalog(Book book) {
        if (bookRepository.existsByIsbn(book.isbn())) {
            throw new BookAlreadyExistsException(book.isbn());
        }
        return bookRepository.save(book);
    }

    public void removeBookFromCatalog(String isbn) {
        bookRepository.deleteByIsbn(isbn);
    }

    public Book editBookDetailsOfCatalog(String isbn, Book book) {
        return bookRepository.getByIsbn(isbn).map(existingBook ->
                new Book(existingBook.isbn(), book.title(), book.author(), book.price())
        ).orElseGet(() -> addToCatalog(book));
    }
}
