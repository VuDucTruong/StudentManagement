package com.vdt.student_management.student.dto.response;

import com.vdt.student_management.common.enums.Gender;
import com.vdt.student_management.major.dto.response.MajorResponse;
import com.vdt.student_management.student.enums.StudentStatus;
import java.util.Date;


public record StudentResponse(
    Long id,
    String name,
    Date dob,
    Gender gender,
    String phone,
    String email,
    String address,
    Date entryDate,
    StudentStatus status,
    MajorResponse major
) {}



