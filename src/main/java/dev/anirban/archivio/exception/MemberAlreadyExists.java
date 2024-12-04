package dev.anirban.archivio.exception;

public class MemberAlreadyExists extends AccountAlreadyExists {
    public MemberAlreadyExists(String username) {
        super("Member Account with username : " + username + " is already present !!");
    }
}