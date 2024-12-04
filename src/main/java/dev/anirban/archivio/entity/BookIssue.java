package dev.anirban.archivio.entity;

import dev.anirban.archivio.dto.response.BookIssueDto;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "BOOK_ISSUE_DB")
public class BookIssue {

    public enum BookIssueStatus {
        PENDING,
        APPROVED, REJECTED,
        RETURNED, NOT_RETURNED
    }

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "issue_date")
    private Timestamp issueDate;

    @Column(name = "due_date")
    private Timestamp dueDate;

    @Column(name = "return_date")
    private Timestamp returnDate;

    @Column(name = "fine")
    private Integer fine;

    @Column(name = "status", nullable = false)
    private BookIssueStatus status;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

    @ManyToOne(
            cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST},
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne(
            cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST},
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "requester_id", nullable = false)
    private Member requester;

    @ManyToOne(
            cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST},
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "approved_by_id")
    private Librarian approvedBy;

    @ManyToOne(
            cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST},
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "returned_by_id")
    private Librarian returnedBy;

    public BookIssueDto toBookIssueDto() {
        return BookIssueDto
                .builder()
                .id(id)
                .issueDate(issueDate)
                .dueDate(dueDate)
                .returnDate(returnDate)
                .fine(fine)
                .status(status)
                .book(book.toBookDto())
                .requester(requester.toMemberDto())
                .approvedBy(approvedBy == null ? null : approvedBy.toLibrarianDto())
                .returnedBy(returnedBy == null ? null : returnedBy.toLibrarianDto())
                .build();
    }
}