package com.kapmati.graphql.adapters.inbound.graphql;

import com.kapmati.graphql.application.AuthorService;
import com.kapmati.graphql.application.BookService;
import com.kapmati.graphql.domain.author.Author;
import com.kapmati.graphql.domain.book.Book;
import graphql.schema.DataFetcher;
import org.springframework.stereotype.Component;

@Component
public class GraphQLDataFetchers {
    private final AuthorService authorService;
    private final BookService bookService;

    public GraphQLDataFetchers(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    public DataFetcher<Book> getBookByIdDataFetcher() {
        return dataFetchingEnvironment -> {
            String bookId = dataFetchingEnvironment.getArgument("id");
            return bookService.getBookById(bookId);
        };
    }

    public DataFetcher<Author> getAuthorDataFetcher() {
        return dataFetchingEnvironment -> {
            Book book = dataFetchingEnvironment.getSource();
            return authorService.getAuthorById(book.authorId());
        };
    }

    public DataFetcher<Integer> getPageCountDataFetcher() {
        return dataFetchingEnvironment -> {
            Book book = dataFetchingEnvironment.getSource();
            return book.pageCount();
        };
    }
}
