package com.hibernate.repository.dao;

import com.hibernate.entity.Book;
import java.util.List;
import java.util.Optional;

public interface BaseRepositoryDao<T, ID>  {
    T save(T book);
    Optional<T> findById(ID id);
    List<T> findAll();
    void deleteById(ID id);
}