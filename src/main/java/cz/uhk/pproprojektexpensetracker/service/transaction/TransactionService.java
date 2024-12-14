package cz.uhk.pproprojektexpensetracker.service.transaction;

import cz.uhk.pproprojektexpensetracker.model.Transaction;
import cz.uhk.pproprojektexpensetracker.service.AbstractService;

import java.util.List;

public interface TransactionService extends AbstractService<Transaction> {
    
    List<Transaction> getAllByProjectId(Long id);
}
