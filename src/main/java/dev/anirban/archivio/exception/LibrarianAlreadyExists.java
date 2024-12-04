package dev.anirban.archivio.exception;

public class LibrarianAlreadyExists extends AccountAlreadyExists {
    public LibrarianAlreadyExists(String email) {
        super("Librarian Account with email : " + email + " is already present !!");
    }
}