package cz.uhk.pproprojektexpensetracker.service.transaction;

import cz.uhk.pproprojektexpensetracker.model.TransactionTag;
import cz.uhk.pproprojektexpensetracker.service.AbstractService;

import java.util.List;

public interface TransactionTagService extends AbstractService<TransactionTag> {

    List<TransactionTag> getAllByUserId(Long userId);

    Boolean isTagDeletable(Long id);
}
