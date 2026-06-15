package com.hibernate.controller;

import com.hibernate.dto.ReaderDto;
import com.hibernate.dto.RequestReaderDto;
import com.hibernate.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/readers")
public class ReaderController {

    private final ReaderService readerService;

    @Autowired
    public ReaderController(ReaderService readerService) {
        this.readerService = readerService;
    }

    @GetMapping
    public List<ReaderDto> getAll() {
        return readerService.getAll()
                .stream()
                .map(ReaderDto::from)
                .toList();
    }

    @GetMapping("/{id}")
    public ReaderDto getById(@PathVariable Long id) {
        return ReaderDto.from(readerService.getById(id));
    }

    @PostMapping
    public ResponseEntity<ReaderDto> create(@RequestBody RequestReaderDto dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ReaderDto.from(readerService.create(dto)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        readerService.delete(id);
    }
}
