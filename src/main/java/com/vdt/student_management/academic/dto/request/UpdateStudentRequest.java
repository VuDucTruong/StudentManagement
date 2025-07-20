package com.vdt.student_management.academic.dto.request;

import com.vdt.student_management.common.enums.Gender;
import java.util.Date;

public record UpdateStudentRequest (
    String name,
    Date dob,
    Gender gender,
    String email,
    String phone,
    String address,
    Long majorId
) {

}
