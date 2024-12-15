package cz.uhk.pproprojektexpensetracker.dto;

import cz.uhk.pproprojektexpensetracker.auditing.enums.AuditLogEntityType;
import cz.uhk.pproprojektexpensetracker.auditing.enums.AuditLogEventType;
import cz.uhk.pproprojektexpensetracker.model.User;
import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Builder
public class AuditLogDto {

    private Long entityId;

    private AuditLogEventType auditLogEventType;

    private AuditLogEntityType auditLogEntityType;

    private String entityJson;

    private Long id;

    private Long createdBy;

    private User userCreatedBy;

    private OffsetDateTime createdAt;
}
