package dev.anirban.archivio.exception;

public class BookNotFound extends DataNotFound {
    public BookNotFound(Integer id) {
        super("Book with ID : " + id + " is not found !!");
    }
}