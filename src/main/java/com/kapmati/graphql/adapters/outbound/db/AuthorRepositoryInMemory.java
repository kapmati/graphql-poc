package com.kapmati.graphql.adapters.outbound.db;

import com.kapmati.graphql.domain.author.Author;
import com.kapmati.graphql.domain.author.AuthorRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

@Repository
public class AuthorRepositoryInMemory implements AuthorRepository {
    private static final List<AuthorEntity> AUTHORS = Arrays.asList(
            new AuthorEntity("author-1", "Joanne", "Rowling"),
            new AuthorEntity("author-2", "Herman", "Melville"),
            new AuthorEntity("author-3", "Anne", "Rice")
    );

    @Override
    public Author getById(String authorId) {
        return AUTHORS.stream()
                .filter(book -> book.id().equals(authorId))
                .findFirst()
                .map(mapEntityToDomainAuthor())
                .orElse(null);
    }

    private Function<AuthorEntity, Author> mapEntityToDomainAuthor() {
        return authorEntity -> new Author(authorEntity.id(), authorEntity.firstName(), authorEntity.lastName());
    }
}
