package cz.uhk.pproprojektexpensetracker.service.transaction;

import cz.uhk.pproprojektexpensetracker.model.Transaction;
import cz.uhk.pproprojektexpensetracker.repository.TransactionRepository;
import cz.uhk.pproprojektexpensetracker.service.AbstractServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl extends AbstractServiceImpl<Transaction> implements TransactionService {


    @Override
    public List<Transaction> getAllByProjectId(Long id) {
        return ((TransactionRepository) repository).findAllByProjectId(id);
    }
}
