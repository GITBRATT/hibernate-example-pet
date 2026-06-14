package com.hibernate.service;

import com.hibernate.entity.Book;
import com.hibernate.exception.BookIsNullException;
import com.hibernate.exception.BookNotFoundException;
import com.hibernate.repository.BookRepositoryImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BookService {

    private final BookRepositoryImpl bookRepository;

    public BookService(BookRepositoryImpl bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book create(Book book) {
        if (book == null) {
            throw new BookIsNullException();
        }
        return bookRepository.save(book);
    }

    public Book getById(Long id) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book == null) {
            throw new BookNotFoundException();
        }
        return book;
    }

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }



}
