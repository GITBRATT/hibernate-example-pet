package com.hibernate.service;

import com.hibernate.dto.RequestLoanDto;
import com.hibernate.entity.Book;
import com.hibernate.entity.Loan;
import com.hibernate.entity.Reader;
import com.hibernate.exception.BookNotFoundException;
import com.hibernate.repository.BookRepositoryImpl;
import com.hibernate.repository.LoanRepositoryImpl;
import com.hibernate.repository.ReaderRepositoryImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class LoanService {

    private final LoanRepositoryImpl loanRepository;
    private final BookRepositoryImpl bookRepository;
    private final ReaderRepositoryImpl readerRepository;

    public LoanService(LoanRepositoryImpl loanRepository,
                       BookRepositoryImpl bookRepository,
                       ReaderRepositoryImpl readerRepository) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
        this.readerRepository = readerRepository;
    }

    public Loan create(RequestLoanDto dto) {
        Book book = bookRepository.findById(dto.getBookId()).orElseThrow(BookNotFoundException::new);
        Reader reader = readerRepository.findById(dto.getReaderId()).orElseThrow(BookNotFoundException::new);
        book.setAvailable(false);
        bookRepository.save(book);
        Loan loan = new Loan(LocalDate.now(), dto.getDueDate(), book, reader);
        return loanRepository.save(loan);
    }

    public Loan getById(Long id) {
        return loanRepository.findById(id).orElseThrow(BookNotFoundException::new);
    }

    public List<Loan> getAll() {
        return loanRepository.findAll();
    }

    public void delete(Long id) {
        loanRepository.deleteById(id);
    }

    public Loan returnBook(Long loanId) {
        Loan loan = loanRepository.findById(loanId).orElseThrow(BookNotFoundException::new);
        loan.setReturnedDate(LocalDate.now());
        if (loan.getBook() != null) {
            loan.getBook().setAvailable(true);
            bookRepository.save(loan.getBook());
        }
        return loanRepository.save(loan);
    }
}
