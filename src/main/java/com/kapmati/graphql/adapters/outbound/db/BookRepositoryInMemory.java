package com.kapmati.graphql.adapters.outbound.db;

import com.kapmati.graphql.domain.Book;
import com.kapmati.graphql.domain.BookRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

@Repository
public class BookRepositoryInMemory implements BookRepository {

    private static final List<BookEntity> BOOKS = Arrays.asList(
            new BookEntity("book-1", "Harry Potter and the Philosopher's Stone", 223, "author-1"),
            new BookEntity("book-2", "Moby Dick", 635, "author-2"),
            new BookEntity("book-3", "Interview with the vampire", 371, "author-3")
    );

    @Override
    public Book getById(String bookId) {
        return BOOKS.stream()
                .filter(book -> book.id().equals(bookId))
                .findFirst()
                .map(mapEntityToDomainBook())
                .orElse(null);
    }

    private Function<BookEntity, Book> mapEntityToDomainBook() {
        return bookEntity -> new Book(bookEntity.id(), bookEntity.name(), bookEntity.pageCount(), bookEntity.authorId());
    }
}
