package dev.anirban.archivio.controller;


import dev.anirban.archivio.constants.UrlConstants;
import dev.anirban.archivio.dto.request.AuthLibrarianDto;
import dev.anirban.archivio.dto.request.AuthMemberDto;
import dev.anirban.archivio.dto.response.ResponseWrapper;
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
    public ResponseWrapper<Librarian> registerLibrarian(@RequestBody AuthLibrarianDto librarian) {
        Librarian data = authService.registerLibrarian(librarian);
        return new ResponseWrapper<>(data);
    }

    @PostMapping(UrlConstants.LOGIN_LIBRARIAN)
    public ResponseWrapper<TokenWrapper> loginLibrarian(@RequestBody AuthLibrarianDto librarian) {
        TokenWrapper data = authService.loginLibrarian(librarian);
        return new ResponseWrapper<>(data);
    }

    @PostMapping(UrlConstants.REGISTER_MEMBER)
    public ResponseWrapper<Member> registerMember(@RequestBody AuthMemberDto member) {
        Member data = authService.registerMember(member);
        return new ResponseWrapper<>(data);
    }

    @PostMapping(UrlConstants.LOGIN_MEMBER)
    public ResponseWrapper<TokenWrapper> loginMember(@RequestBody AuthMemberDto member) {
        TokenWrapper data = authService.loginMember(member);
        return new ResponseWrapper<>(data);
    }
}