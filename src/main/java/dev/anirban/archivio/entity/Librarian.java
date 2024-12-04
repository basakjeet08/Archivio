package dev.anirban.archivio.entity;


import dev.anirban.archivio.dto.response.LibrarianDto;
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
@Table(name = "LIBRARIAN_DB")
public class Librarian {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "joining_date", nullable = false)
    private Timestamp joiningDate;

    @Column(name = "salary", nullable = false)
    private Integer salary;

    @Column(name = "roles", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRole roles;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

    public LibrarianDto toLibrarianDto() {
        return LibrarianDto
                .builder()
                .id(id)
                .name(name)
                .email(email)
                .joiningDate(joiningDate)
                .salary(salary)
                .build();
    }
}