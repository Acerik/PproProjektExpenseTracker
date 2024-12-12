package cz.uhk.pproprojektexpensetracker.repository;

import cz.uhk.pproprojektexpensetracker.model.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
}
