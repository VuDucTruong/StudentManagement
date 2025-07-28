package com.vdt.student_management.common.enums;

public enum RoleType {
  STUDENT("ROLE_STUDENT"),
  TEACHER("ROLE_TEACHER");

  private String value;

  RoleType(String value) {
    this.value = value;
  }

  public String getValue() {
    return this.value;
  }
}
