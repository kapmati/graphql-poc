package com.kapmati.graphql.adapters.outbound.db;

import com.kapmati.graphql.domain.author.Author;
import com.kapmati.graphql.domain.author.AuthorRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Repository
public class AuthorRepositoryInMemory implements AuthorRepository {
    private static final List<AuthorEntity> AUTHORS = new ArrayList<>();

    static {
        AUTHORS.add(new AuthorEntity("author-1", "Joanne", "Rowling"));
        AUTHORS.add(new AuthorEntity("author-2", "Herman", "Melville"));
        AUTHORS.add(new AuthorEntity("author-3", "Anne", "Rice"));
        AUTHORS.add(new AuthorEntity("author-4", "Bob", "Uncle"));
    }

    @Override
    public Author getById(String authorId) {
        return AUTHORS.stream()
                .filter(book -> book.id().equals(authorId))
                .findFirst()
                .map(mapEntityToDomainAuthor())
                .orElse(null);
    }

    @Override
    public Author save(Author author) {
        var authorEntity = mapToEntity(author);
        AUTHORS.add(authorEntity);
        return author;
    }

    private AuthorEntity mapToEntity(Author author) {
        return new AuthorEntity(author.id(), author.firstName(), author.lastName());
    }

    private Function<AuthorEntity, Author> mapEntityToDomainAuthor() {
        return authorEntity -> new Author(authorEntity.id(), authorEntity.firstName(), authorEntity.lastName());
    }
}
