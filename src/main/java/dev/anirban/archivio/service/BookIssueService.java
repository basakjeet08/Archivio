package dev.anirban.archivio.service;

import dev.anirban.archivio.dto.request.BookIssueRequest;
import dev.anirban.archivio.entity.Book;
import dev.anirban.archivio.entity.BookIssue;
import dev.anirban.archivio.entity.Member;
import dev.anirban.archivio.repo.BookIssueRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookIssueService {

    private final BookIssueRepo bookIssueRepo;
    private final BookService bookService;
    private final MemberService memberService;

    public BookIssue create(BookIssueRequest issueRequest) {

        // Check if the Book is valid and available
        Book requestedBook = bookService.findById(issueRequest.getBookId());

        if (requestedBook.getStatus() == Book.BookStatus.ISSUED)
            throw new RuntimeException("Book is not available right now !!");

        // Check username is correct
        Member requester = memberService.findByUsername(issueRequest.getRequesterUsername());

        BookIssue newIssue = BookIssue
                .builder()
                .status(BookIssue.BookIssueStatus.PENDING)
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .updatedAt(Timestamp.valueOf(LocalDateTime.now()))
                .book(requestedBook)
                .requester(requester)
                .build();

        requestedBook.addToIssueList(newIssue);
        requester.addBooksBorrowed(newIssue);
        requestedBook.setStatus(Book.BookStatus.REQUESTED);

        return bookIssueRepo.save(newIssue);
    }

    public List<BookIssue> findByStatus(BookIssue.BookIssueStatus status) {
        return bookIssueRepo.findByStatus(status);
    }
}