package com.hibernate.repository;

import com.hibernate.entity.Loan;
import com.hibernate.entity.Reader;
import com.hibernate.repository.dao.BaseRepositoryDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class LoanRepositoryImpl implements BaseRepositoryDao<Loan, Integer> {
   // Передаем управленеи за транзакцией
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Loan save(Loan loan) {
        if (loan.getId() == null) {
            entityManager.persist(loan);
            return loan;
        }
        return entityManager.merge(loan);
    }

    @Override
    public Optional<Loan> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Loan.class, id));
    }

    @Override
    public List<Loan> findAll() {
        return entityManager.createQuery("""
                                        select r
                                        from Loan r
                                        """, Loan.class).getResultList();
    }

    @Override
    public void deleteById(Long id) {
        Loan loan = entityManager.find(Loan.class, id);
        if (loan != null) {
            entityManager.remove(loan);
        }
    }
}
