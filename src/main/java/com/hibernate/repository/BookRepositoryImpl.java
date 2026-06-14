package com.hibernate.repository;

import com.hibernate.entity.Book;
import com.hibernate.repository.dao.BaseRepositoryDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public class BookRepositoryImpl implements BaseRepositoryDao<Book, Long> {
   // Передаем управленеи за транзакцией
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Book save(Book book) {
        if (book.getId() == null) {
            entityManager.persist(book);
            return book;
        }
        return entityManager.merge(book);
    }

    @Override
    public Optional<Book> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Book.class, id));
    }

    @Override
    public List<Book> findAll() {
        String hql = """  
                   select b 
                   from Book b
                """;
        return entityManager.createQuery(hql, Book.class).getResultList();
    }

    @Override
    public void deleteById(Long id) {
        String hql = """  
                   delete from Book b 
                   where b.id = :id
                """;
        Query query = entityManager.createQuery(hql);
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
