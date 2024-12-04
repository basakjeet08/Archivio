package dev.anirban.archivio.dto.response;


import dev.anirban.archivio.entity.Book;
import lombok.*;


@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    private Integer id;
    private String title;
    private Integer publicationYear;
    private Integer edition;
    private Book.BookStatus status;
    private Book.Genre genre;
}