package dev.anirban.archivio.repo;

import dev.anirban.archivio.entity.BookIssue;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookIssueRepo extends JpaRepository<BookIssue, Integer> {
}