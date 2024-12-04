package dev.anirban.archivio.dto.request;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookIssueRequest {
    private Integer id;
    private Integer bookId;
    private String requesterUsername;
    private String approvedByEmail;
    private String returnedByEmail;
}