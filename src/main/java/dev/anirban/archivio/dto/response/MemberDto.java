package dev.anirban.archivio.dto.response;

import lombok.*;


@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
    private Integer id;
    private String name;
    private String email;
    private String username;
    private String avatar;
}