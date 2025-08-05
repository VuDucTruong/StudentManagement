package com.vdt.student_management.tuition.dto.response;

import com.vdt.student_management.academic.dto.response.SemesterResponse;
import com.vdt.student_management.tuition.enums.TuitionStatus;

public record TuitionResponse(
    Long id,
    Long totalAmount,
    Long remainingAmount,
    TuitionStatus status,
    SemesterResponse semester
) {

}
