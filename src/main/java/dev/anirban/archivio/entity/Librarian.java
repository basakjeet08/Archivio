package dev.anirban.archivio.entity;


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

    @Column(name = "joiningDate", nullable = false)
    private Timestamp joiningDate;

    @Column(name = "salary", nullable = false)
    private Integer salary;

    @Column(name = "roles", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRole roles;

    @Column(name = "createdAt", nullable = false)
    private Timestamp createdAt;

    @Column(name = "updatedAt", nullable = false)
    private Timestamp updatedAt;
}