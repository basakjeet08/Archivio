package dev.anirban.archivio.controller;


import dev.anirban.archivio.constants.UrlConstants;
import dev.anirban.archivio.dto.request.AuthLibrarianDto;
import dev.anirban.archivio.dto.request.AuthMemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    @PostMapping(UrlConstants.REGISTER_LIBRARIAN)
    public String registerLibrarian(@RequestBody AuthLibrarianDto librarian) {
        return "Librarian Registered";
    }

    @PostMapping(UrlConstants.LOGIN_LIBRARIAN)
    public String loginLibrarian(@RequestBody AuthLibrarianDto librarian) {
        return "Librarian Logged In";
    }

    @PostMapping(UrlConstants.REGISTER_MEMBER)
    public String registerMember(@RequestBody AuthMemberDto member) {
        return "Member Registered";
    }

    @PostMapping(UrlConstants.LOGIN_MEMBER)
    public String loginMember(@RequestBody AuthMemberDto member) {
        return "Member Logged In";
    }
}