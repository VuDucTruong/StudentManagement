package com.vdt.student_management.common.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum RoleType {
    STUDENT("ROLE_STUDENT", 0),
    TEACHER("ROLE_TEACHER", 1),
    ADMIN("ROLE_ADMIN", 99);

    String value;
    int weight;

    RoleType(String value, int weight) {
        this.value = value;
        this.weight = weight;
    }

    public static int compare(RoleType r1, RoleType r2) {
        if (r1.weight == r2.weight) return 0;
        return r1.weight - r2.weight > 0 ? 1 : -1;
    }
}
