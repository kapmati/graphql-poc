package com.kapmati.graphql.domain.book;

import java.util.List;

public interface BookRepository {
    Book getById(String bookId);

    Book save(Book book);

    List<Book> getBooks();
}
