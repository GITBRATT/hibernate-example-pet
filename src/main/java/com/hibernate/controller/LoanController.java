package com.hibernate.controller;

import com.hibernate.dto.LoanDto;
import com.hibernate.dto.RequestLoanDto;
import com.hibernate.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loans")
public class LoanController {

    private final LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping
    public List<LoanDto> getAll() {
        return loanService.getAll()
                .stream()
                .map(LoanDto::from)
                .toList();
    }

    @GetMapping("/{id}")
    public LoanDto getById(@PathVariable Long id) {
        return LoanDto.from(loanService.getById(id));
    }

    @PostMapping
    public ResponseEntity<LoanDto> create(@RequestBody RequestLoanDto dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(LoanDto.from(loanService.create(dto)));
    }

    @PatchMapping("/{id}/return")
    public LoanDto returnBook(@PathVariable Long id) {
        return LoanDto.from(loanService.returnBook(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        loanService.delete(id);
    }
}
