package com.hibernate.repository;

import com.hibernate.entity.Author;
import com.hibernate.repository.dao.BaseRepositoryDao;
import jakarta.persistence.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepository implements BaseRepositoryDao<Author, Long> {
    // Передаем управленеи за транзакцией
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Author save(Author author) {
        if (author.getId() == null) {
            entityManager.persist(author);
            return author;
        }
        return entityManager.merge(author);
    }

    @Override
    public Optional<Author> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Author.class, id));
    }

    @Override
    public List<Author> findAll() {
        String hql = """  
                   select b 
                   from Author b
                """;
        return entityManager.createQuery(hql, Author.class).getResultList();
    }

    @Override
    public void deleteById(Long id) {
        String hql = """  
                   delete from Author b 
                   where b.id = :id
                """;
        Query query = entityManager.createQuery(hql);
        query.setParameter("id", id);
        query.executeUpdate();
    }
}