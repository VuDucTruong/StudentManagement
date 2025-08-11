package com.vdt.student_management.academic.dto.request;

import com.vdt.student_management.academic.enums.ProgramLevel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AddProgramRequest(
        @NotBlank(message = "PROGRAM_NAME_REQUIRED") String name,
        ProgramLevel level,
        Integer duration,
        @NotNull(message = "MAJOR_REQUIRED") Long majorId) {

    public AddProgramRequest {
        if (level == null) {
            level = ProgramLevel.BACHELOR;
        }
        if (duration == null) {
            duration = 4;
        }
    }
}
