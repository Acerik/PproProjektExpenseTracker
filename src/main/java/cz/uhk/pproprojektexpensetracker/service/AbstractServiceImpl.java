package cz.uhk.pproprojektexpensetracker.service;

import cz.uhk.pproprojektexpensetracker.auditing.AuditEvent;
import cz.uhk.pproprojektexpensetracker.auditing.enums.AuditLogEventType;
import cz.uhk.pproprojektexpensetracker.model.AbstractAuditingEntity;
import cz.uhk.pproprojektexpensetracker.repository.AbstractRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class AbstractServiceImpl<T extends AbstractAuditingEntity> implements AbstractService<T> {

    private final AbstractRepository<T> repository;

    private final ApplicationEventPublisher eventPublisher;

    @Override
    public Optional<T> findOne(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<T> getAll() {
        return repository.findAll();
    }

    @Override
    public T create(T entity) {
        T saved = repository.save(entity);
        eventPublisher.publishEvent(new AuditEvent<>(saved, AuditLogEventType.CREATED));
        return saved;
    }

    @Override
    public T update(T entity) {
        T updated = repository.save(entity);
        eventPublisher.publishEvent(new AuditEvent<>(updated, AuditLogEventType.UPDATED));
        return updated;
    }

    @Override
    public void delete(T entity) {
        repository.delete(entity);
        eventPublisher.publishEvent(new AuditEvent<>(entity, AuditLogEventType.DELETED));
    }

    @Override
    public void delete(Long id) {
        T entity = findOne(id).orElseThrow(EntityNotFoundException::new);
        repository.deleteById(id);
        eventPublisher.publishEvent(new AuditEvent<>(entity, AuditLogEventType.DELETED));
    }
}
