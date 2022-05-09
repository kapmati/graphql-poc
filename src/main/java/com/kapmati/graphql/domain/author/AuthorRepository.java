package com.kapmati.graphql.domain.author;

public interface AuthorRepository {
    Author getById(String authorId);
}
