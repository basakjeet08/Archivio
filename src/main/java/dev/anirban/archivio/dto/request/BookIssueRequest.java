package dev.anirban.archivio.dto.request;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookIssueRequest {
    private Integer bookId;
    private String requesterUsername;
}