package dev.anirban.archivio.entity;


import dev.anirban.archivio.dto.response.MemberDto;
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
@Table(name = "MEMBER_DB")
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "roles", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRole roles;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

    public MemberDto toMemberDto() {
        return MemberDto
                .builder()
                .id(id)
                .name(name)
                .email(email)
                .username(username)
                .avatar(avatar)
                .build();
    }
}