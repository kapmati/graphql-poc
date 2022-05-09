package com.kapmati.graphql.application;

import com.kapmati.graphql.domain.book.Book;
import com.kapmati.graphql.domain.book.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book getBookById(String bookId) {
        return bookRepository.getById(bookId);
    }


}
