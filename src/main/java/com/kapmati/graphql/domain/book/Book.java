package com.kapmati.graphql.domain.book;

public record Book(
        String id,
        String name,
        int pageCount,
        String authorId) {
}
