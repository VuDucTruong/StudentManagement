package com.vdt.student_management.academic.dto.request;

import com.vdt.student_management.common.enums.Gender;
import java.util.Date;

public record AddStudentRequest (
    String name,
    Date dob,
    Gender gender,
    String email,
    String phone,
    String address,
    Date entryDate,
    Long majorId
) {
  public AddStudentRequest {
    if(entryDate == null) entryDate = new Date();
  }
}
