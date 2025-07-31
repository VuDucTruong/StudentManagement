package com.vdt.student_management.academic.dto.request;

import jakarta.validation.constraints.NotEmpty;
import java.time.LocalDate;

public record AddStudentClassRequest(
    @NotEmpty(message = "STUDENT_CLASS_NAME_REQUIRED")
    String name,
    Integer startYear,
    Integer endYear
) {

  public AddStudentClassRequest {
    if(startYear == null || endYear == null) {
      startYear = LocalDate.now().getYear();
      endYear = startYear + 4;
    }
  }
}
