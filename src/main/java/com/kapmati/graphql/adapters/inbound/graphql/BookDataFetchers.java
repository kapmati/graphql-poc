package com.kapmati.graphql.adapters.inbound.graphql;

import com.kapmati.graphql.application.BookService;
import com.kapmati.graphql.domain.book.Book;
import graphql.schema.DataFetcher;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
public class BookDataFetchers implements DataFetcherGroup<Book> {
    private final BookService bookService;

    public BookDataFetchers(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public Map<String, DataFetcher<?>> getQueries() {
        return Map.of("books", getBooksDataFetcher(),
                "bookById", getBookByIdDataFetcher());
    }

    @Override
    public Map<String, DataFetcher<?>> getMutations() {
        return Map.of("addBook", addBookDataFetcher());
    }

    @Override
    public Map<String, DataFetcher<?>> getFieldQueries() {
        return Map.of("pageCount", getPageCountDataFetcher());
    }

    @Override
    public Class<Book> getType() {
        return Book.class;
    }

    public DataFetcher<List<Book>> getBooksDataFetcher() {
        return dataFetchingEnvironment -> bookService.getBooks();
    }

    public DataFetcher<Book> getBookByIdDataFetcher() {
        return dataFetchingEnvironment -> {
            String bookId = dataFetchingEnvironment.getArgument("id");
            return bookService.getBookById(bookId);
        };
    }

    public DataFetcher<Integer> getPageCountDataFetcher() {
        return dataFetchingEnvironment -> {
            Book book = dataFetchingEnvironment.getSource();
            return book.pageCount();
        };
    }

    public DataFetcher<Book> addBookDataFetcher() {
        return dataFetchingEnvironment -> {
            String name = dataFetchingEnvironment.getArgument("name");
            Integer pageCount = dataFetchingEnvironment.getArgument("pageCount");
            String authorId = dataFetchingEnvironment.getArgument("authorId");
            var book = new Book(UUID.randomUUID().toString(), name, pageCount, authorId);
            return bookService.save(book);
        };
    }
}
