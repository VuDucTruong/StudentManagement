package com.vdt.student_management.academic.dto.response;

import com.vdt.student_management.academic.enums.ProgramLevel;
import java.time.LocalDateTime;

public record ProgramResponse(
        Long id, LocalDateTime updatedAt, LocalDateTime deletedAt, String name, ProgramLevel level, Integer duration) {}
