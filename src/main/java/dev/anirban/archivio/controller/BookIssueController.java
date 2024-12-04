package dev.anirban.archivio.controller;

import dev.anirban.archivio.constants.UrlConstants;
import dev.anirban.archivio.dto.request.BookIssueRequest;
import dev.anirban.archivio.dto.response.ResponseWrapper;
import dev.anirban.archivio.entity.BookIssue;
import dev.anirban.archivio.service.BookIssueService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BookIssueController {

    private final BookIssueService service;

    @PostMapping(UrlConstants.CREATE_BOOK_ISSUE)
    public ResponseWrapper<BookIssue> create(@RequestBody BookIssueRequest issueRequest) {
        BookIssue data = service.create(issueRequest);
        return new ResponseWrapper<>(data);
    }
}