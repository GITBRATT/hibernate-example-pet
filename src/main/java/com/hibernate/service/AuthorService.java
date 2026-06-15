package com.hibernate.service;

import com.hibernate.dto.RequestAuthorDto;
import com.hibernate.entity.Author;
import com.hibernate.exception.BookNotFoundException;
import com.hibernate.repository.AuthorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author create(RequestAuthorDto dto) {
        Author author = new Author(dto.getFullName());
        return authorRepository.save(author);
    }

    public Author getById(Long id) {
        return authorRepository.findById(id).orElseThrow(BookNotFoundException::new);
    }

    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    public void delete(Long id) {
        authorRepository.deleteById(id);
    }
}
