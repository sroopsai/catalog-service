package com.polarbookshop.catalogservice.exception;

public class BookAlreadyExistsException extends RuntimeException {
    public BookAlreadyExistsException(String isbn) {
        super("The book with isbn " + isbn + " already exists");
    }
}
