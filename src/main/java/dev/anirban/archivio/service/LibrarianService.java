package dev.anirban.archivio.service;


import dev.anirban.archivio.dto.request.AuthLibrarianDto;
import dev.anirban.archivio.entity.Librarian;
import dev.anirban.archivio.entity.UserRole;
import dev.anirban.archivio.repo.LibrarianRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LibrarianService {

    private final LibrarianRepo librarianRepo;
    private final PasswordEncoder encoder;

    public Librarian create(AuthLibrarianDto authLibrarianDto) {
        String hashedPassword = encoder.encode(authLibrarianDto.getPassword());

        Librarian librarian = Librarian
                .builder()
                .name(authLibrarianDto.getName())
                .email(authLibrarianDto.getEmail())
                .password(hashedPassword)
                .joiningDate(Timestamp.valueOf(LocalDateTime.now()))
                .salary(10000)
                .roles(UserRole.LIBRARIAN)
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .updatedAt(Timestamp.valueOf(LocalDateTime.now()))
                .build();

        return librarianRepo.save(librarian);
    }


    public Librarian findByEmail(String email) {
        return librarianRepo
                .findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Librarian not found !!"));
    }
}