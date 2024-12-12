package cz.uhk.pproprojektexpensetracker.model;

import cz.uhk.pproprojektexpensetracker.auditing.enums.AuditLogEntityType;
import cz.uhk.pproprojektexpensetracker.auditing.enums.AuditLogEventType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Immutable;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import java.time.OffsetDateTime;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "audit_log")
@Immutable
@ToString(callSuper = true)
@EqualsAndHashCode
public class AuditLog {

    @Column(name = "entity_id")
    private Long entityId;

    @Column(name = "audit_log_event_type")
    @Enumerated(EnumType.STRING)
    private AuditLogEventType auditLogEventType;

    @Column(name = "audit_log_entity_type")
    @Enumerated(EnumType.STRING)
    private AuditLogEntityType auditLogEntityType;

    @Column(name = "entity_json")
    private String entityJson;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @CreatedBy
    @Column(name = "created_by", updatable = false)
    private Long createdBy;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private OffsetDateTime createdAt;
}
