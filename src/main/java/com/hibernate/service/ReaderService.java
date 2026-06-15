package com.hibernate.service;

import com.hibernate.dto.RequestReaderDto;
import com.hibernate.entity.Reader;
import com.hibernate.exception.BookNotFoundException;
import com.hibernate.repository.ReaderRepositoryImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ReaderService {

    private final ReaderRepositoryImpl readerRepository;

    public ReaderService(ReaderRepositoryImpl readerRepository) {
        this.readerRepository = readerRepository;
    }

    public Reader create(RequestReaderDto dto) {
        Reader reader = new Reader(dto.getFullName());
        return readerRepository.save(reader);
    }

    public Reader getById(Long id) {
        return readerRepository.findById(id).orElseThrow(BookNotFoundException::new);
    }

    public List<Reader> getAll() {
        return readerRepository.findAll();
    }

    public void delete(Long id) {
        readerRepository.deleteById(id);
    }
}
