package dev.anirban.archivio.dto.request;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthMemberDto {
    private String name;
    private String username;
    private String email;
    private String password;
    private String avatar;
}