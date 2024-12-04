package dev.anirban.archivio.service;

import dev.anirban.archivio.dto.request.BookIssueRequest;
import dev.anirban.archivio.entity.Book;
import dev.anirban.archivio.entity.BookIssue;
import dev.anirban.archivio.entity.Librarian;
import dev.anirban.archivio.entity.Member;
import dev.anirban.archivio.exception.BookIssueNotFound;
import dev.anirban.archivio.exception.BookUnavailable;
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
    private final LibrarianService librarianService;

    public BookIssue create(BookIssueRequest issueRequest) {

        // Check if the Book is valid and available
        Book requestedBook = bookService.findById(issueRequest.getBookId());

        if (requestedBook.getStatus() == Book.BookStatus.ISSUED)
            throw new BookUnavailable(requestedBook.getId());

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

    public BookIssue update(BookIssueRequest issueRequest) {

        // Check if the Book is Available anymore
        BookIssue savedIssue = bookIssueRepo
                .findById(issueRequest.getId())
                .orElseThrow(() -> new BookIssueNotFound(issueRequest.getId()));

        if (savedIssue.getBook().getStatus() == Book.BookStatus.ISSUED && savedIssue.getApprovedBy() == null)
            throw new BookUnavailable(savedIssue.getBook().getId());

        // Approving Book Issue
        if (issueRequest.getApprovedByEmail() != null && savedIssue.getApprovedBy() == null)
            approveBookIssue(issueRequest.getApprovedByEmail(), savedIssue);

        // Returning Book Issue
        if (issueRequest.getReturnedByEmail() != null && savedIssue.getReturnedBy() == null)
            returnBookIssue(issueRequest.getReturnedByEmail(), savedIssue);

        return bookIssueRepo.save(savedIssue);
    }


    public void approveBookIssue(String approvedByEmail, BookIssue savedIssue) {
        Librarian approvedBy = librarianService.findByEmail(approvedByEmail);

        // Saved Issue Updates
        savedIssue.setIssueDate(Timestamp.valueOf(LocalDateTime.now()));
        savedIssue.setDueDate(Timestamp.valueOf(LocalDateTime.now().plusDays(7)));
        savedIssue.setStatus(BookIssue.BookIssueStatus.APPROVED);
        savedIssue.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        approvedBy.addApprovedBooks(savedIssue);
    }

    public void returnBookIssue(String returnedByEmail, BookIssue savedIssue) {
        Librarian returnedBy = librarianService.findByEmail(returnedByEmail);

        // Saved Issue Updates
        savedIssue.setReturnDate(Timestamp.valueOf(LocalDateTime.now()));
        savedIssue.setStatus(BookIssue.BookIssueStatus.RETURNED);
        savedIssue.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        returnedBy.addReturnedBooks(savedIssue);
    }
}