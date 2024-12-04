package dev.anirban.archivio.service;

import dev.anirban.archivio.dto.request.AuthMemberDto;
import dev.anirban.archivio.entity.Member;
import dev.anirban.archivio.entity.UserRole;
import dev.anirban.archivio.exception.MemberAlreadyExists;
import dev.anirban.archivio.exception.MemberNotFound;
import dev.anirban.archivio.repo.MemberRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepo memberRepo;
    private final PasswordEncoder encoder;

    public Member create(AuthMemberDto memberDto) {

        if (memberRepo.findByUsername(memberDto.getUsername()).isPresent())
            throw new MemberAlreadyExists(memberDto.getUsername());

        String hashedPassword = encoder.encode(memberDto.getPassword());

        Member member = Member
                .builder()
                .name(memberDto.getName())
                .email(memberDto.getEmail())
                .username(memberDto.getUsername())
                .password(hashedPassword)
                .avatar(memberDto.getAvatar())
                .roles(UserRole.MEMBER)
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .updatedAt(Timestamp.valueOf(LocalDateTime.now()))
                .build();

        return memberRepo.save(member);
    }

    public Member findByUsername(String username) {
        return memberRepo
                .findByUsername(username)
                .orElseThrow(() -> new MemberNotFound(username));
    }
}