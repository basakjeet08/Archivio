package dev.anirban.archivio.controller;

import dev.anirban.archivio.constants.UrlConstants;
import dev.anirban.archivio.dto.response.BookDto;
import dev.anirban.archivio.dto.response.ResponseWrapper;
import dev.anirban.archivio.entity.Book;
import dev.anirban.archivio.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService service;

    @PostMapping(UrlConstants.CREATE_BOOK)
    public ResponseWrapper<BookDto> create(@RequestBody Book book) {
        BookDto data = service.create(book).toBookDto();
        return new ResponseWrapper<>(data);
    }

    @GetMapping(UrlConstants.FIND_BOOK_BY_ID)
    public ResponseWrapper<BookDto> findById(@PathVariable Integer id) {
        BookDto data = service.findById(id).toBookDto();
        return new ResponseWrapper<>(data);
    }

    @GetMapping(UrlConstants.FIND_BOOK_QUERY)
    public ResponseWrapper<List<BookDto>> findBook(
            @RequestParam(name = "status", required = false) Book.BookStatus status,
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "genre", required = false) Book.Genre genre
    ) {
        List<Book> bookList;

        if (status != null)
            bookList = service.findByStatus(status);
        else if (title != null)
            bookList = service.findByTitle(title);
        else if (genre != null)
            bookList = service.findByGenre(genre);
        else
            bookList = service.findAll();

        List<BookDto> data = bookList
                .stream()
                .map(Book::toBookDto)
                .toList();

        return new ResponseWrapper<>(data);
    }

    @PutMapping(UrlConstants.PUT_BOOK)
    public ResponseWrapper<BookDto> update(@RequestBody Book book) {
        BookDto data = service.update(book).toBookDto();
        return new ResponseWrapper<>(data);
    }

    @DeleteMapping(UrlConstants.DELETE_BOOK_BY_ID)
    public ResponseWrapper<BookDto> deleteById(@PathVariable Integer id) {
        service.deleteById(id);
        return new ResponseWrapper<>("Book is deleted Successfully !!");
    }
}