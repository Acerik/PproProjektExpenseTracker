package cz.uhk.pproprojektexpensetracker.auditing.enums;

public enum AuditLogEntityType {
    UNDEFINED("undefined"),
    USER("User"),
    AUDIT_LOG("AuditLog"),
    TRANSACTION("Transaction"),
    PROJECT("Project"),
    TRANSACTION_TAG("TransactionTag");


    private String value;

    AuditLogEntityType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
