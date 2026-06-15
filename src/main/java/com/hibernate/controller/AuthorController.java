package com.hibernate.controller;

import com.hibernate.dto.AuthorDto;
import com.hibernate.dto.RequestAuthorDto;
import com.hibernate.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<AuthorDto> getAll() {
        return authorService.getAll()
                .stream()
                .map(AuthorDto::from)
                .toList();
    }

    @GetMapping("/{id}")
    public AuthorDto getById(@PathVariable Long id) {
        return AuthorDto.from(authorService.getById(id));
    }

    @PostMapping
    public ResponseEntity<AuthorDto> create(@RequestBody RequestAuthorDto dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(AuthorDto.from(authorService.create(dto)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        authorService.delete(id);
    }
}
