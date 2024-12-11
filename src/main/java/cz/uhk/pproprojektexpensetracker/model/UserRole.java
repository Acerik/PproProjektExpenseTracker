package cz.uhk.pproprojektexpensetracker.model;

public enum UserRole {
    ADMIN("admin"),
    USER("user");


    public final String value;

    UserRole(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
