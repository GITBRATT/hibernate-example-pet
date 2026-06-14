package com.hibernate.repository;

import com.hibernate.entity.Book;
import com.hibernate.entity.Reader;
import com.hibernate.repository.dao.BaseRepositoryDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ReaderRepositoryImpl implements BaseRepositoryDao<Reader, Long> {
   // Передаем управленеи за транзакцией
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Reader save(Reader reader) {
        if (reader.getId() == null) {
            entityManager.persist(reader);
            return reader;
        }
        return entityManager.merge(reader);
    }

    @Override
    public Optional<Reader> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Reader.class, id));
    }

    @Override
    public List<Reader> findAll() {
        return entityManager.createQuery("""
                                        select r
                                        from Reader r
                                        """, Reader.class).getResultList();
    }

    @Override
    public void deleteById(Long id) {
        String hql = """  
                   delete from Reader r 
                   where r.id = :id
                """;
        Query query = entityManager.createQuery(hql);
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
