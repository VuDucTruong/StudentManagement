package com.vdt.student_management.academic.dto.request;

import com.vdt.student_management.common.enums.Gender;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

public record AddStudentRequest (
    @NotEmpty(message = "STUDENT_NAME_REQUIRED")
    String name,
    LocalDate dob,
    Gender gender,
    String email,
    String phone,
    String address,
    LocalDate entryDate,
    @NotNull(message = "MAJOR_REQUIRED")
    Long majorId
) {
  public AddStudentRequest {
    if(entryDate == null) entryDate = LocalDate.now();
  }
}
