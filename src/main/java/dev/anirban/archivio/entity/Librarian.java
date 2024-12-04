package dev.anirban.archivio.entity;


import dev.anirban.archivio.dto.response.LibrarianDto;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

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

    @OneToMany(
            mappedBy = "approvedBy",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}
    )
    private List<BookIssue> approvedBookIssues;

    @OneToMany(
            mappedBy = "returnedBy",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}
    )
    private List<BookIssue> returnedBookIssues;

    public void addApprovedBooks(BookIssue issue) {
        approvedBookIssues.add(issue);
        issue.setApprovedBy(this);
    }

    public void addReturnedBooks(BookIssue issue) {
        returnedBookIssues.add(issue);
        issue.setReturnedBy(this);
    }

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