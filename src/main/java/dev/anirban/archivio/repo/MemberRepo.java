package dev.anirban.archivio.repo;

import dev.anirban.archivio.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepo extends JpaRepository<Member, Integer> {
    Optional<Member> findByUsername(String username);
}