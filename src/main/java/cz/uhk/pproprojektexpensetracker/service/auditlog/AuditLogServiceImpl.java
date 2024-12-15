package cz.uhk.pproprojektexpensetracker.service.auditlog;

import cz.uhk.pproprojektexpensetracker.model.AuditLog;
import cz.uhk.pproprojektexpensetracker.repository.AuditLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuditLogServiceImpl implements AuditLogService {

    private final AuditLogRepository auditLogRepository;


    @Override
    public Optional<AuditLog> findById(Long id) {
        return auditLogRepository.findById(id);
    }

    @Override
    public List<AuditLog> findAll() {
        return auditLogRepository.findAll();
    }
}
