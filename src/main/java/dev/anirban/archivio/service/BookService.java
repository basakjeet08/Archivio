package dev.anirban.archivio.service;


import dev.anirban.archivio.entity.Book;
import dev.anirban.archivio.exception.BookNotFound;
import dev.anirban.archivio.repo.BookRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepo bookRepo;

    public Book create(Book book) {
        book = Book
                .builder()
                .title(book.getTitle())
                .publicationYear(book.getPublicationYear())
                .edition(book.getEdition())
                .status(Book.BookStatus.AVAILABLE)
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .updatedAt(Timestamp.valueOf(LocalDateTime.now()))
                .build();

        return bookRepo.save(book);
    }

    public List<Book> findAll() {
        return bookRepo.findAll();
    }

    public List<Book> findByStatus(Book.BookStatus status) {
        return bookRepo.findByStatus(status);
    }

    public Book findById(Integer id) {
        return bookRepo
                .findById(id)
                .orElseThrow(() -> new BookNotFound(id));
    }

    public List<Book> findByTitle(String title) {
        return bookRepo.findByTitle(title);
    }
}