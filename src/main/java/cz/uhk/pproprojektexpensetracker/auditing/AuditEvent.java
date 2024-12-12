package cz.uhk.pproprojektexpensetracker.auditing;

import cz.uhk.pproprojektexpensetracker.auditing.enums.AuditLogEventType;
import cz.uhk.pproprojektexpensetracker.model.AbstractAuditingEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuditEvent<T extends AbstractAuditingEntity> {

    private T entity;

    private AuditLogEventType eventType;
}
