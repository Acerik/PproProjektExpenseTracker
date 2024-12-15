package cz.uhk.pproprojektexpensetracker.service.transaction;

import cz.uhk.pproprojektexpensetracker.model.TransactionTag;
import cz.uhk.pproprojektexpensetracker.service.AbstractService;

import java.util.List;
import java.util.Optional;

public interface TransactionTagService extends AbstractService<TransactionTag> {

    List<TransactionTag> getAllByUserId(Long userId);

    Optional<TransactionTag> findOneByIdAndUserId(Long id, Long userId);

    Boolean isTagDeletable(Long id, Long userId);
}
