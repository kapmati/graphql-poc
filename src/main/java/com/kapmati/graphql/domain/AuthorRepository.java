package com.kapmati.graphql.domain;

public interface AuthorRepository {
    Author getById(String authorId);
}
