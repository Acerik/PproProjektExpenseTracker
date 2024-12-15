package cz.uhk.pproprojektexpensetracker.service.transaction;

import cz.uhk.pproprojektexpensetracker.model.Transaction;
import cz.uhk.pproprojektexpensetracker.repository.TransactionRepository;
import cz.uhk.pproprojektexpensetracker.service.AbstractServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl extends AbstractServiceImpl<Transaction> implements TransactionService {


    @Override
    public List<Transaction> getAllByProjectId(Long id) {
        return ((TransactionRepository) repository).findAllByProjectId(id);
    }

    @Override
    public Optional<Transaction> findOneByIdAndUserId(Long id, Long userId) {
        Optional<Transaction> transaction = findOne(id);
        return transaction.filter(value -> Objects.equals(value.getProject().getUser().getId(), userId));
    }
}
