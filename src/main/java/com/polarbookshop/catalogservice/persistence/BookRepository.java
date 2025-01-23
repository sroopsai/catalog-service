package com.polarbookshop.catalogservice.persistence;

import com.polarbookshop.catalogservice.model.Book;

import java.util.Optional;

public interface BookRepository {

    Iterable<Book> fetchAll();
    Book save(Book book);
    Optional<Book> getByIsbn(String isbn);
    boolean existsByIsbn(String isbn);
    void deleteByIsbn(String isbn);


}
