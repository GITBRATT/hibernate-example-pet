package com.hibernate.controller;

import com.hibernate.dto.BookDto;
import com.hibernate.dto.RequestBookDto;
import com.hibernate.entity.Book;
import com.hibernate.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookDto> getAll() {
        List<BookDto> books = bookService.getAll()
                .stream()
                .map(BookDto::from)
                .toList();
        return books;
    }

    @GetMapping("/{id}")
    public BookDto getById(@PathVariable Long id) {
        Book book = bookService.getById(id);
        return BookDto.from(book);
    }

    @PostMapping
    public ResponseEntity<BookDto> create(@RequestBody RequestBookDto requestBookDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(BookDto.from(bookService.create(requestBookDto)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // вернёт 204
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }
}
