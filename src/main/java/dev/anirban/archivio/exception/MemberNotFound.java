package dev.anirban.archivio.exception;

public class MemberNotFound extends DataNotFound {
    public MemberNotFound(Integer id) {
        super("Member with ID : " + id + " is not found!!");
    }

    public MemberNotFound(String username) {
        super("Member with username : " + username + " is not found!!");
    }
}