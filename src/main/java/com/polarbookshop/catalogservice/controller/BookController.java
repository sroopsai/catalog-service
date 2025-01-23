package com.polarbookshop.catalogservice.controller;

import com.polarbookshop.catalogservice.model.Book;
import com.polarbookshop.catalogservice.service.BookService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public Iterable<Book> fetchAllBooksFromCatalog() {
        return bookService.fetchAllBooksFromCatalog();
    }

    @GetMapping("{isbn}")
    public Book getBookFromCatalog(@PathVariable String isbn) {
        return bookService.getBookFromCatalog(isbn);
    }

    @PostMapping
    public Book addBookToCatalog(@Valid @RequestBody Book book) {
        return bookService.addToCatalog(book);
    }

    @PutMapping("{isbn}")
    public Book updateBook(@PathVariable String isbn, @Valid @RequestBody Book book) {
        return bookService.editBookDetailsOfCatalog(isbn, book);
    }

    @DeleteMapping("{isbn}")
    public void deleteBook(@PathVariable String isbn) {
        bookService.removeBookFromCatalog(isbn);
    }


}
