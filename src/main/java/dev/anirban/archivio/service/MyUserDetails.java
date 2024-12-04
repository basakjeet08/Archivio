package dev.anirban.archivio.service;


import dev.anirban.archivio.entity.Librarian;
import dev.anirban.archivio.entity.Member;
import dev.anirban.archivio.repo.LibrarianRepo;
import dev.anirban.archivio.repo.MemberRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class MyUserDetails implements UserDetailsService {

    private final MemberRepo memberRepo;
    private final LibrarianRepo librarianRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> member = memberRepo.findByUsername(username);

        if (member.isPresent())
            return User
                    .builder()
                    .username(member.get().getUsername())
                    .password(member.get().getPassword())
                    .roles(member.get().getRoles().name())
                    .build();

        Optional<Librarian> librarian = librarianRepo.findByEmail(username);

        if (librarian.isEmpty())
            throw new RuntimeException("User not present");

        return User
                .builder()
                .username(librarian.get().getEmail())
                .password(librarian.get().getPassword())
                .roles(librarian.get().getRoles().name())
                .build();
    }
}