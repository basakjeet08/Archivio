package dev.anirban.archivio.service;


import dev.anirban.archivio.entity.Book;
import dev.anirban.archivio.exception.BookNotFound;
import dev.anirban.archivio.repo.BookRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
                .genre(book.getGenre())
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .updatedAt(Timestamp.valueOf(LocalDateTime.now()))
                .issuedList(new ArrayList<>())
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

    public List<Book> findByGenre(Book.Genre genre) {
        return bookRepo.findByGenre(genre);
    }

    public Book update(Book book) {
        Book savedBook = findById(book.getId());

        if (book.getTitle() != null)
            savedBook.setTitle(book.getTitle());
        if (book.getPublicationYear() != null)
            savedBook.setPublicationYear(book.getPublicationYear());
        if (book.getEdition() != null)
            savedBook.setEdition(book.getEdition());
        if (book.getStatus() != null)
            savedBook.setStatus(book.getStatus());

        book.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));

        return bookRepo.save(savedBook);
    }

    public void deleteById(Integer id) {
        if (!bookRepo.existsById(id))
            throw new BookNotFound(id);

        bookRepo.deleteById(id);
    }
}