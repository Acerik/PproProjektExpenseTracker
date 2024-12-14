package cz.uhk.pproprojektexpensetracker.service.transaction;

import cz.uhk.pproprojektexpensetracker.model.TransactionTag;
import cz.uhk.pproprojektexpensetracker.repository.TransactionRepository;
import cz.uhk.pproprojektexpensetracker.repository.TransactionTagRepository;
import cz.uhk.pproprojektexpensetracker.service.AbstractServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionTagServiceImpl extends AbstractServiceImpl<TransactionTag> implements TransactionTagService {

    private final TransactionRepository transactionRepository;

    @Override
    public List<TransactionTag> getAllByUserId(Long userId) {
        return ((TransactionTagRepository) repository).findAllByUserId(userId);
    }

    @Override
    public Boolean isTagDeletable(Long id) {
        return transactionRepository.findAllByTransactionTagId(id).isEmpty();
    }
}
