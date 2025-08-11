package com.vdt.student_management.grading.dto.response;

import java.time.LocalDateTime;

public record ScoreResponse(
        Long id,
        Float processScore,
        Float midTermScore,
        Float finalScore,
        float avgScore,
        LocalDateTime updatedAt,
        LocalDateTime deletedAt) {}
