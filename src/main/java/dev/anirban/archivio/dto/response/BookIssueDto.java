package dev.anirban.archivio.dto.response;

import dev.anirban.archivio.entity.BookIssue;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookIssueDto {
    private Integer id;
    private Timestamp issueDate;
    private Timestamp dueDate;
    private Timestamp returnDate;
    private Integer fine;
    private BookIssue.BookIssueStatus status;
    private BookDto book;
    private MemberDto requester;
    private LibrarianDto approvedBy;
    private LibrarianDto returnedBy;
}
