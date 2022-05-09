package com.kapmati.graphql.domain;

public record Book(
        String id,
        String name,
        int pageCount,
        String authorId) {
}
