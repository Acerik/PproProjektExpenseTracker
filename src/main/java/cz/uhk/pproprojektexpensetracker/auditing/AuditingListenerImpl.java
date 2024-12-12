package cz.uhk.pproprojektexpensetracker.auditing;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.uhk.pproprojektexpensetracker.auditing.enums.AuditLogEntityType;
import cz.uhk.pproprojektexpensetracker.model.AuditLog;
import cz.uhk.pproprojektexpensetracker.repository.AuditLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuditingListenerImpl implements AuditingListener {

    private static final Long SYSTEM_USER = 1L;

    private final AuditLogRepository auditLogRepository;
    private final AuditorAware<Long> auditorAware;
    private final ObjectMapper objectMapper;

    @Override
    @EventListener
    public void onEvent(AuditEvent<?> event) {
        log.info("Caught audit event: {}", event);
        auditLogRepository.save(createAuditLog(event));
    }

    private AuditLog createAuditLog(AuditEvent<?> event) {
        String entityJson;
        try {
            entityJson = objectMapper.writeValueAsString(event.getEntity());
        } catch (JsonProcessingException e) {
            entityJson = e.getMessage();
        }
        return AuditLog.builder()
                .auditLogEntityType(getEntityType(event.getEntity().getClass()))
                .auditLogEventType(event.getEventType())
                .entityId(event.getEntity().getId())
                .entityJson(entityJson)
                .createdAt(OffsetDateTime.now())
                .createdBy(auditorAware.getCurrentAuditor().orElse(SYSTEM_USER))
                .build();
    }

    private AuditLogEntityType getEntityType(Class<?> clazz) {
        for (AuditLogEntityType entityType : AuditLogEntityType.values()) {
            if (clazz.getSimpleName().equals(entityType.name())) {
                return entityType;
            }
        }
        return AuditLogEntityType.UNDEFINED;
    }
}
