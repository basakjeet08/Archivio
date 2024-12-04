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
@Table(name = "BOOK_DB")
public class Book {

    public enum BookStatus {
        AVAILABLE, ISSUED
    }

    public enum Genre {
        BIOGRAPHY,
        FANTASY,
        FICTION,
        HISTORY,
        HORROR,
        MYSTERY,
        NON_FICTION,
        ROMANCE,
        SCIENCE_FICTION,
        YOUNG_ADULT
    }

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "publication_year")
    private Integer publicationYear;

    @Column(name = "edition")
    private Integer edition;

    @Column(name = "status", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private BookStatus status;

    @Column(name = "genre", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;
}