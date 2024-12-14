package cz.uhk.pproprojektexpensetracker.service;

import cz.uhk.pproprojektexpensetracker.auditing.AuditEvent;
import cz.uhk.pproprojektexpensetracker.auditing.enums.AuditLogEventType;
import cz.uhk.pproprojektexpensetracker.model.AbstractAuditingEntity;
import cz.uhk.pproprojektexpensetracker.repository.AbstractRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

import java.util.List;
import java.util.Optional;

@Slf4j
public abstract class AbstractServiceImpl<T extends AbstractAuditingEntity> implements AbstractService<T> {

    @Autowired
    protected AbstractRepository<T> repository;

    @Autowired
    protected ApplicationEventPublisher eventPublisher;

    @Override
    public Optional<T> findOne(Long id) {
        log.info("Find one for id {}", id);
        return repository.findById(id);
    }

    @Override
    public List<T> getAll() {
        log.info("Get all");
        return repository.findAll();
    }

    @Override
    public T create(T entity) {
        log.info("Create {}", entity);
        entity.setId(null);
        T saved = repository.save(entity);
        eventPublisher.publishEvent(new AuditEvent<>(saved, AuditLogEventType.CREATED));
        return saved;
    }

    @Override
    public T update(T entity) {
        log.info("Update {}", entity);
        T updated = repository.save(entity);
        eventPublisher.publishEvent(new AuditEvent<>(entity, AuditLogEventType.CREATED));
        return updated;
    }

    @Override
    public void delete(T entity) {
        log.info("Delete {}", entity);
        repository.delete(entity);
        eventPublisher.publishEvent(new AuditEvent<>(entity, AuditLogEventType.DELETED));
    }

    @Override
    public void delete(Long id) {
        log.info("Delete {}", id);
        T entity = findOne(id).orElseThrow(EntityNotFoundException::new);
        repository.deleteById(id);
        eventPublisher.publishEvent(new AuditEvent<>(entity, AuditLogEventType.DELETED));
    }
}
