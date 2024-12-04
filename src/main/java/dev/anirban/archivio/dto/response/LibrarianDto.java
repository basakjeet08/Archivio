package dev.anirban.archivio.dto.response;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LibrarianDto {
    private Integer id;
    private String name;
    private String email;
    private Timestamp joiningDate;
    private Integer salary;
}