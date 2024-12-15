package cz.uhk.pproprojektexpensetracker.service.transaction;

import cz.uhk.pproprojektexpensetracker.model.TransactionTag;
import cz.uhk.pproprojektexpensetracker.repository.TransactionRepository;
import cz.uhk.pproprojektexpensetracker.repository.TransactionTagRepository;
import cz.uhk.pproprojektexpensetracker.service.AbstractServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionTagServiceImpl extends AbstractServiceImpl<TransactionTag> implements TransactionTagService {

    private final TransactionRepository transactionRepository;

    @Override
    public Optional<TransactionTag> findOneByIdAndUserId(Long id, Long userId) {
        Optional<TransactionTag> transactionTag = findOne(id);
        return transactionTag.filter(value -> Objects.equals(value.getUser().getId(), userId));
    }

    @Override
    public List<TransactionTag> getAllByUserId(Long userId) {
        return ((TransactionTagRepository) repository).findAllByUserId(userId);
    }

    @Override
    public Boolean isTagDeletable(Long id, Long userId) {
        return transactionRepository.findAllByTransactionTagId(id).isEmpty()
                && findOneByIdAndUserId(id, userId).isPresent();
    }
}
