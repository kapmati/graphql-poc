package com.kapmati.graphql.adapters.outbound.db;

public record BookEntity(
        String id,
        String name,
        int pageCount,
        String authorId) {
}
