package cz.uhk.pproprojektexpensetracker.repository;

import cz.uhk.pproprojektexpensetracker.model.TransactionTag;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionTagRepository extends AbstractRepository<TransactionTag> {

    List<TransactionTag> findAllByUserId(Long userId);
}
