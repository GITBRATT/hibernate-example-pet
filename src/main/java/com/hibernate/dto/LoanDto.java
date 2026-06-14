package com.hibernate.dto;

import com.hibernate.entity.Loan;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoanDto {

    private Long id;
    private LocalDate loanDate;
    private LocalDate dueDate;
    private LocalDate returnedDate;

    // Из Book берём только id и title — иначе Book → List<Loan> → LoanDto → BookDto → ...
    private Long bookId;
    private String bookTitle;

    // Из Reader берём только id и имя
    private Long readerId;
    private String readerName;

    public static LoanDto from(Loan loan) {
        return LoanDto.builder()
                .id(loan.getId())
                .loanDate(loan.getLoanDate())
                .dueDate(loan.getDueDate())
                .returnedDate(loan.getReturnedDate())
                .bookId(loan.getBook() != null ? loan.getBook().getId() : null)
                .bookTitle(loan.getBook() != null ? loan.getBook().getTitle() : null)
                .readerId(loan.getReader() != null ? loan.getReader().getId() : null)
                .readerName(loan.getReader() != null ? loan.getReader().getFullName() : null)
                .build();
    }
}
