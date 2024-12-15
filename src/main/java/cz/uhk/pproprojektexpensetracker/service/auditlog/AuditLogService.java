package cz.uhk.pproprojektexpensetracker.service.auditlog;

import cz.uhk.pproprojektexpensetracker.model.AuditLog;

import java.util.List;
import java.util.Optional;

public interface AuditLogService {

    Optional<AuditLog> findById(Long id);

    List<AuditLog> findAll();
}
