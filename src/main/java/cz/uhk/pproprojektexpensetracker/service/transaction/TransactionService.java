package cz.uhk.pproprojektexpensetracker.service.transaction;

import cz.uhk.pproprojektexpensetracker.model.Transaction;
import cz.uhk.pproprojektexpensetracker.service.AbstractService;

import java.util.List;
import java.util.Optional;

public interface TransactionService extends AbstractService<Transaction> {

    List<Transaction> getAllByProjectId(Long id);

    Optional<Transaction> findOneByIdAndUserId(Long id, Long userId);
}
