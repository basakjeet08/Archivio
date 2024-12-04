package dev.anirban.archivio.controller;


import dev.anirban.archivio.constants.UrlConstants;
import dev.anirban.archivio.dto.request.AuthLibrarianDto;
import dev.anirban.archivio.dto.request.AuthMemberDto;
import dev.anirban.archivio.dto.response.TokenWrapper;
import dev.anirban.archivio.entity.Librarian;
import dev.anirban.archivio.entity.Member;
import dev.anirban.archivio.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(UrlConstants.REGISTER_LIBRARIAN)
    public Librarian registerLibrarian(@RequestBody AuthLibrarianDto librarian) {
        return authService.registerLibrarian(librarian);
    }

    @PostMapping(UrlConstants.LOGIN_LIBRARIAN)
    public TokenWrapper loginLibrarian(@RequestBody AuthLibrarianDto librarian) {
        return authService.loginLibrarian(librarian);
    }

    @PostMapping(UrlConstants.REGISTER_MEMBER)
    public Member registerMember(@RequestBody AuthMemberDto member) {
        return authService.registerMember(member);
    }

    @PostMapping(UrlConstants.LOGIN_MEMBER)
    public TokenWrapper loginMember(@RequestBody AuthMemberDto member) {
        return authService.loginMember(member);
    }
}