package com.kapmati.graphql.domain;

public interface BookRepository {
    Book getById(String bookId);
}
