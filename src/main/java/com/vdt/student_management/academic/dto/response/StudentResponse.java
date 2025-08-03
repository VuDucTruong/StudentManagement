package com.vdt.student_management.academic.dto.response;

import com.vdt.student_management.common.enums.Gender;
import com.vdt.student_management.academic.enums.StudentStatus;
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
    MajorResponse major,
    StudentClassResponse studentClass
) {}



