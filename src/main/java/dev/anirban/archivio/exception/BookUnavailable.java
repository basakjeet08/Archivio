package dev.anirban.archivio.exception;

public class BookUnavailable extends RuntimeException {
    public BookUnavailable(Integer id) {
        super("Book with the ID : " + id + " is not available right now !!");
    }
}