package com.vdt.student_management.academic.dto.request;

import java.time.LocalDate;

public record AddTeacherRequest (
    String name,
    String degree,
    String specialization,
    String email,
    String phone,
    LocalDate hireDate,
    LocalDate dob,
    Long facultyId
) {

  public AddTeacherRequest {
    if(hireDate == null) hireDate = LocalDate.now();
  }
}