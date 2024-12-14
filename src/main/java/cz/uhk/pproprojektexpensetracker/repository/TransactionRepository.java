package cz.uhk.pproprojektexpensetracker.repository;

import cz.uhk.pproprojektexpensetracker.model.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends AbstractRepository<Transaction> {

    List<Transaction> findAllByProjectId(Long id);

    List<Transaction> findAllByTransactionTagId(Long id);
}
