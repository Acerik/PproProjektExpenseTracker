package cz.uhk.pproprojektexpensetracker.repository;

import cz.uhk.pproprojektexpensetracker.model.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends AbstractRepository<Transaction> {

}
