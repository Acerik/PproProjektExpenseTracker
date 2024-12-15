package cz.uhk.pproprojektexpensetracker.service.auditlog;

import cz.uhk.pproprojektexpensetracker.dto.AuditLogDto;
import cz.uhk.pproprojektexpensetracker.model.AuditLog;
import cz.uhk.pproprojektexpensetracker.model.User;
import cz.uhk.pproprojektexpensetracker.repository.AuditLogRepository;
import cz.uhk.pproprojektexpensetracker.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuditLogServiceImpl implements AuditLogService {

    private final AuditLogRepository auditLogRepository;

    private final UserService userService;

    @Override
    public Optional<AuditLogDto> findById(Long id) {
        Optional<AuditLog> auditLog = auditLogRepository.findById(id);
        return auditLog.map(this::mapToAuditLogDto);
    }

    @Override
    public List<AuditLogDto> findAll() {
        List<AuditLog> logs = auditLogRepository.findAll();
        List<User> users = userService.findAllByIds(
                logs.stream()
                        .map(AuditLog::getCreatedBy)
                        .toList());
        return logs
                .stream()
                .map(log -> mapToAuditLogDto(log, users.stream()
                        .filter(u -> u.getId().equals(log.getCreatedBy()))
                        .findFirst()
                        .orElse(null)))
                .toList();
    }

    private AuditLogDto mapToAuditLogDto(AuditLog auditLog) {
        return mapToAuditLogDto(auditLog, userService.findOne(auditLog.getCreatedBy()).orElse(null));
    }

    private AuditLogDto mapToAuditLogDto(AuditLog auditLog, User user) {
        return AuditLogDto.builder()
                .auditLogEntityType(auditLog.getAuditLogEntityType())
                .entityJson(auditLog.getEntityJson())
                .auditLogEventType(auditLog.getAuditLogEventType())
                .createdAt(auditLog.getCreatedAt())
                .createdBy(auditLog.getCreatedBy())
                .id(auditLog.getId())
                .entityId(auditLog.getEntityId())
                .userCreatedBy(user)
                .build();
    }
}
