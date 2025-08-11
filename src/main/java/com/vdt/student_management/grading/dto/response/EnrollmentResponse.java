package com.vdt.student_management.grading.dto.response;

import com.vdt.student_management.academic.dto.response.ClassSectionResponse;
import com.vdt.student_management.academic.dto.response.StudentResponse;
import com.vdt.student_management.grading.enums.EnrollmentStatus;

public record EnrollmentResponse(
        Long id, EnrollmentStatus status, ClassSectionResponse classSection, StudentResponse student) {}
