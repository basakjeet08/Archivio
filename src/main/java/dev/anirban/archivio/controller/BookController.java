package dev.anirban.archivio.controller;

import dev.anirban.archivio.constants.UrlConstants;
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
    public ResponseWrapper<Book> create(@RequestBody Book book) {
        Book data = service.create(book);
        return new ResponseWrapper<>(data);
    }

    @GetMapping(UrlConstants.FIND_BOOK_BY_ID)
    public ResponseWrapper<Book> findById(@PathVariable Integer id) {
        Book data = service.findById(id);
        return new ResponseWrapper<>(data);
    }

    @GetMapping(UrlConstants.FIND_BOOK_QUERY)
    public ResponseWrapper<List<Book>> findBook(
            @RequestParam(name = "status", required = false) Book.BookStatus status,
            @RequestParam(name = "title", required = false) String title
    ) {
        List<Book> data;

        if (status != null)
            data = service.findByStatus(status);
        else if (title != null)
            data = service.findByTitle(title);
        else
            data = service.findAll();

        return new ResponseWrapper<>(data);
    }
}