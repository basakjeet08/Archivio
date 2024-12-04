package dev.anirban.archivio.controller;

import dev.anirban.archivio.constants.UrlConstants;
import dev.anirban.archivio.dto.request.BookIssueRequest;
import dev.anirban.archivio.dto.response.BookIssueDto;
import dev.anirban.archivio.dto.response.ResponseWrapper;
import dev.anirban.archivio.entity.BookIssue;
import dev.anirban.archivio.service.BookIssueService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookIssueController {

    private final BookIssueService service;

    @PostMapping(UrlConstants.CREATE_BOOK_ISSUE)
    public ResponseWrapper<BookIssueDto> create(@RequestBody BookIssueRequest issueRequest) {
        BookIssueDto data = service.create(issueRequest).toBookIssueDto();
        return new ResponseWrapper<>(data);
    }

    @GetMapping(UrlConstants.FIND_BOOK_ISSUE_QUERY)
    public ResponseWrapper<List<BookIssueDto>> findBookIssue(
            @RequestParam(name = "status") BookIssue.BookIssueStatus status
    ) {
        List<BookIssueDto> data = service
                .findByStatus(status)
                .stream()
                .map(BookIssue::toBookIssueDto)
                .toList();
        return new ResponseWrapper<>(data);
    }
}