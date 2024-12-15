package cz.uhk.pproprojektexpensetracker.service.auditlog;

import cz.uhk.pproprojektexpensetracker.dto.AuditLogDto;

import java.util.List;
import java.util.Optional;

public interface AuditLogService {

    Optional<AuditLogDto> findById(Long id);

    List<AuditLogDto> findAll();
}
