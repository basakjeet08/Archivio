package dev.anirban.archivio.exception;

public class LibrarianNotFound extends DataNotFound {
    public LibrarianNotFound(Integer id) {
        super("Librarian with ID : " + id + " is not found!!");
    }

    public LibrarianNotFound(String email) {
        super("Librarian with email : " + email + " is not found!!");
    }
}