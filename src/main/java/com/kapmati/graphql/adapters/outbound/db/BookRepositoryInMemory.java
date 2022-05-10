package com.kapmati.graphql.adapters.outbound.db;

import com.kapmati.graphql.domain.book.Book;
import com.kapmati.graphql.domain.book.BookRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Repository
public class BookRepositoryInMemory implements BookRepository {

    private static final List<BookEntity> BOOKS = new ArrayList<>();

    static {
        BOOKS.add(new BookEntity("book-1", "Harry Potter and the Philosopher's Stone", 223, "author-1"));
        BOOKS.add(new BookEntity("book-2", "Moby Dick", 635, "author-2"));
        BOOKS.add(new BookEntity("book-3", "Interview with the vampire", 371, "author-3"));
    }

    @Override
    public Book getById(String bookId) {
        return BOOKS.stream()
                .filter(book -> book.id().equals(bookId))
                .findFirst()
                .map(mapEntityToDomainBook())
                .orElse(null);
    }

    @Override
    public Book save(Book book) {
        var entityBook = mapToEntity(book);
        BOOKS.add(entityBook);
        return book;
    }

    @Override
    public List<Book> getBooks() {
        return BOOKS.stream()
                .map(mapEntityToDomainBook())
                .toList();
    }

    private BookEntity mapToEntity(Book book) {
        return new BookEntity(book.id(), book.name(), book.pageCount(), book.authorId());
    }

    private Function<BookEntity, Book> mapEntityToDomainBook() {
        return bookEntity -> new Book(bookEntity.id(), bookEntity.name(), bookEntity.pageCount(), bookEntity.authorId());
    }
}
