package dev.anirban.archivio;

import dev.anirban.archivio.dto.request.AuthLibrarianDto;
import dev.anirban.archivio.dto.request.AuthMemberDto;
import dev.anirban.archivio.entity.Book;
import dev.anirban.archivio.service.AuthService;
import dev.anirban.archivio.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@RequiredArgsConstructor
public class ArchivioApplication {

    private final AuthService authService;
    private final BookService bookService;

    public static void main(String[] args) {
        SpringApplication.run(ArchivioApplication.class, args);
    }


    @Bean
    public CommandLineRunner commandLineRunner() {
        return _ -> {

            AuthMemberDto member = createAuthMember();
            AuthLibrarianDto librarian = createAuthLibrarian();

            authService.registerLibrarian(librarian);
            authService.loginLibrarian(librarian);

            authService.registerMember(member);
            authService.loginMember(member);

            for (int i = 0; i < 10; i++)
                bookService.create(createBook(i));
        };
    }


    public AuthMemberDto createAuthMember() {
        return AuthMemberDto
                .builder()
                .name("Member 01")
                .username("Member 01 Username")
                .email("member01@gmail.com")
                .password("Member 01 Password")
                .avatar("Member 01 Avatar")
                .build();
    }

    public AuthLibrarianDto createAuthLibrarian() {
        return AuthLibrarianDto
                .builder()
                .name("Librarian 01")
                .email("librarian01@gmail.com")
                .password("Librarian 01 Password")
                .build();
    }

    public Book createBook(int i) {
        Book.Genre[] genres = Book.Genre.values();
        return Book
                .builder()
                .title("Book Title " + i)
                .publicationYear(2000)
                .edition(2)
                .genre(genres[(int) (Math.random() * genres.length)])
                .build();
    }
}