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

            for (int i = 1; i <= 5; i++) {
                AuthLibrarianDto librarian = createAuthLibrarian(i);
                authService.registerLibrarian(librarian);
                authService.loginLibrarian(librarian);
            }

            for (int i = 1; i <= 5; i++) {
                AuthMemberDto member = createAuthMember(i);
                authService.registerMember(member);
                authService.loginMember(member);
            }

            for (int i = 0; i < 10; i++)
                bookService.create(createBook(i));
        };
    }


    public AuthMemberDto createAuthMember(int i) {
        return AuthMemberDto
                .builder()
                .name("Member " + i)
                .username("Member " + i + " Username")
                .email("member" + i + "@gmail.com")
                .password("Member " + i + " Password")
                .avatar("Member " + i + " Avatar")
                .build();
    }

    public AuthLibrarianDto createAuthLibrarian(int i) {
        return AuthLibrarianDto
                .builder()
                .name("Librarian " + i)
                .email("librarian" + i + "@gmail.com")
                .password("Librarian " + i + " Password")
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