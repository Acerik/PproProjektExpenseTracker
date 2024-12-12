package cz.uhk.pproprojektexpensetracker.auditing;

public interface AuditingListener {

    void onEvent(AuditEvent<?> event);
}
