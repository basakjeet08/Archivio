package dev.anirban.archivio.service;

import dev.anirban.archivio.dto.request.AuthLibrarianDto;
import dev.anirban.archivio.dto.request.AuthMemberDto;
import dev.anirban.archivio.dto.response.TokenWrapper;
import dev.anirban.archivio.entity.Librarian;
import dev.anirban.archivio.entity.Member;
import dev.anirban.archivio.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final LibrarianService librarianService;
    private final MemberService memberService;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;

    public Librarian registerLibrarian(AuthLibrarianDto librarianDto) {
        return librarianService.create(librarianDto);
    }

    public TokenWrapper loginLibrarian(AuthLibrarianDto librarianDto) {

        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        librarianDto.getEmail(), librarianDto.getPassword()
                )
        );

        Librarian savedLibrarian = librarianService.findByEmail(librarianDto.getEmail());

        UserDetails userDetails = User
                .builder()
                .username(savedLibrarian.getEmail())
                .password(savedLibrarian.getPassword())
                .roles(savedLibrarian.getRoles().name())
                .build();

        return new TokenWrapper(jwtService.generateToken(userDetails));
    }

    public Member registerMember(AuthMemberDto memberDto) {
        return memberService.create(memberDto);
    }

    public TokenWrapper loginMember(AuthMemberDto authMemberDto) {

        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authMemberDto.getUsername(), authMemberDto.getPassword()
                )
        );

        Member savedMember = memberService.findByUsername(authMemberDto.getUsername());

        UserDetails userDetails = User
                .builder()
                .username(savedMember.getUsername())
                .password(savedMember.getPassword())
                .roles(savedMember.getRoles().name())
                .build();

        return new TokenWrapper(jwtService.generateToken(userDetails));
    }
}
