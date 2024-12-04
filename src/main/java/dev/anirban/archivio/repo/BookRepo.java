package dev.anirban.archivio.repo;

import dev.anirban.archivio.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepo extends JpaRepository<Book, Integer> {

    List<Book> findByStatus(Book.BookStatus status);

    List<Book> findByTitle(String title);

    List<Book> findByGenre(Book.Genre genre);
}