package com.kapmati.graphql.application;

import com.kapmati.graphql.domain.author.Author;
import com.kapmati.graphql.domain.author.AuthorRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    private AuthorRepository authorRepository;

    public Author getAuthorById(String authorId) {
        return authorRepository.getById(authorId);
    }
}
