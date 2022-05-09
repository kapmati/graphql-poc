package com.kapmati.graphql.domain.book;

public interface BookRepository {
    Book getById(String bookId);
}
