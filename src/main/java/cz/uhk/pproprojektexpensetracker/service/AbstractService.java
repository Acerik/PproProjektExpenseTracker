package cz.uhk.pproprojektexpensetracker.service;

import cz.uhk.pproprojektexpensetracker.model.AbstractAuditingEntity;

import java.util.List;
import java.util.Optional;

public interface AbstractService<T extends AbstractAuditingEntity> {

    Optional<T> findOne(Long id);

    List<T> getAll();

    List<T> findAllByIds(List<Long> ids);

    T create(T entity);

    T update(T entity);

    void delete(T entity);

    void delete(Long id);

}
