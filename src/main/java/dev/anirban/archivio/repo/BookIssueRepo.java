package dev.anirban.archivio.repo;

import dev.anirban.archivio.entity.BookIssue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BookIssueRepo extends JpaRepository<BookIssue, Integer> {

    List<BookIssue> findByStatus(BookIssue.BookIssueStatus status);
}