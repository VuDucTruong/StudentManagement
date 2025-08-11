package com.vdt.student_management.academic.dto.response;

import java.time.LocalDateTime;

public record StudentClassResponse(
        Long id, LocalDateTime updatedAt, LocalDateTime deletedAt, String name, Integer startYear, Integer endYear) {}
