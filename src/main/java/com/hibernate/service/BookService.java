package com.hibernate.service;

import com.hibernate.dto.RequestBookDto;
import com.hibernate.entity.Author;
import com.hibernate.entity.Book;
import com.hibernate.exception.BookIsNullException;
import com.hibernate.exception.BookNotFoundException;
import com.hibernate.repository.AuthorRepository;
import com.hibernate.repository.BookRepositoryImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BookService {

    private final BookRepositoryImpl bookRepository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepositoryImpl bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public Book create(RequestBookDto requestBookDto) {
        Author author = authorRepository.findById(requestBookDto.getAuthorId()).orElseThrow(BookNotFoundException::new);
        Book book = new Book(requestBookDto.getTitle(), author);
        return bookRepository.save(book);
    }

    public Book getById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
        return book;
    }

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }



}
