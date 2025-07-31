package com.vdt.student_management.academic.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record AddTeacherRequest (
    @NotEmpty(message = "TEACHER_NAME_REQUIRED")
    String name,
    String degree,
    String specialization,
    String email,
    String phone,
    LocalDate hireDate,
    LocalDate dob,

    @NotNull(message = "FACULTY_REQUIRED")
    Long facultyId
) {

  public AddTeacherRequest {
    if(hireDate == null) hireDate = LocalDate.now();
  }
}