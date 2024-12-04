package dev.anirban.archivio.dto.request;

import lombok.*;


@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthLibrarianDto {
    private String name;
    private String email;
    private String password;
}