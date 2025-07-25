package com.vdt.student_management.academic.dto.request;

import com.vdt.student_management.academic.enums.ProgramLevel;

public record AddProgramRequest(
    String name,
    ProgramLevel level,
    Integer duration,
    Long majorId
) {

}
