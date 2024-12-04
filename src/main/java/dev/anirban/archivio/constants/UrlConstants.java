package dev.anirban.archivio.constants;

public class UrlConstants {

    // Authentication Endpoints (Librarian)
    public static final String REGISTER_LIBRARIAN = "/librarian/register";
    public static final String LOGIN_LIBRARIAN = "/librarian/login";

    // Authentication Endpoints (Member)
    public static final String REGISTER_MEMBER = "/member/register";
    public static final String LOGIN_MEMBER = "/member/login";

    // Book Endpoints
    public static final String CREATE_BOOK = "/books";
    public static final String FIND_BOOK_BY_ID = "/books/{id}";
    public static final String FIND_BOOK_QUERY = "/books";
    public static final String PUT_BOOK = "/books";
    public static final String DELETE_BOOK_BY_ID = "/books/{id}";

    // Book Issue Endpoints
    public static final String CREATE_BOOK_ISSUE = "/book_issues";
}