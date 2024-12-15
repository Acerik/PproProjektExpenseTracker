package cz.uhk.pproprojektexpensetracker.model;

import lombok.Getter;
import lombok.experimental.FieldNameConstants;

@Getter
@FieldNameConstants(onlyExplicitlyIncluded = true)
public enum UserRole {
    @FieldNameConstants.Include ADMIN("admin"),
    @FieldNameConstants.Include USER("user");

    public final String value;

    UserRole(String value) {
        this.value = value;
    }
}
