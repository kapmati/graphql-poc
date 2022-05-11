package com.kapmati.graphql.adapters.inbound.graphql;

import com.kapmati.graphql.application.AuthorService;
import com.kapmati.graphql.domain.author.Author;
import com.kapmati.graphql.domain.book.Book;
import graphql.schema.DataFetcher;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

@Component
public class AuthorDataFetchers implements DataFetcherGroup<Author> {
    private final AuthorService authorService;

    public AuthorDataFetchers(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Override
    public Map<String, DataFetcher<?>> getQueries() {
        return Map.of("authorById", getAuthorByIdDataFetcher());
    }

    @Override
    public Map<String, DataFetcher<?>> getMutations() {
        return Map.of("addAuthor", addAuthorDataFetcher());
    }

    @Override
    public Map<String, DataFetcher<?>> getFieldQueries() {
        return Map.of("author", getAuthorDataFetcher());
    }

    @Override
    public Class<Author> getType() {
        return Author.class;
    }

    public DataFetcher<Author> getAuthorByIdDataFetcher() {
        return dataFetchingEnvironment -> {
            String authorId = dataFetchingEnvironment.getArgument("id");
            return authorService.getAuthorById(authorId);
        };
    }

    public DataFetcher<Author> getAuthorDataFetcher() {
        return dataFetchingEnvironment -> {
            Book book = dataFetchingEnvironment.getSource();
            return authorService.getAuthorById(book.authorId());
        };
    }

    public DataFetcher<Author> addAuthorDataFetcher() {
        return dataFetchingEnvironment -> {
            String firstName = dataFetchingEnvironment.getArgument("firstName");
            String lastName = dataFetchingEnvironment.getArgument("lastName");
            var author = new Author(UUID.randomUUID().toString(), firstName, lastName);
            return authorService.save(author);
        };
    }
}
