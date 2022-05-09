package com.kapmati.graphql.application;

import com.kapmati.graphql.domain.book.Book;
import com.kapmati.graphql.domain.book.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    private BookRepository bookRepository;

    public Book getBookById(String bookId) {
        return bookRepository.getById(bookId);
    }


}
