package cz.uhk.pproprojektexpensetracker.auditing.enums;

public enum AuditLogEntityType {
    UNDEFINED("undefined"),
    USER("User"),
    AUDIT_LOG("AuditLog");


    private String value;

    AuditLogEntityType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
