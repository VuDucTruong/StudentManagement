package com.vdt.student_management.class_section.dto;

import java.time.LocalDate;

public record ClassSectionResponse(
    String semester,
    LocalDate startDate,
    LocalDate endDate
) {

}
