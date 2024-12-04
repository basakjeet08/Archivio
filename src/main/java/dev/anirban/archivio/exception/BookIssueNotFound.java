package dev.anirban.archivio.exception;

public class BookIssueNotFound extends DataNotFound {
    public BookIssueNotFound(Integer id) {
        super("Book Issue with ID : " + id + " is not found !!");
    }
}