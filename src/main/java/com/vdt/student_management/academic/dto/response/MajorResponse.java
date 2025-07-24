package com.vdt.student_management.academic.dto.response;


import java.time.LocalDateTime;

public record MajorResponse(Long id, String name, LocalDateTime updatedAt, LocalDateTime deletedAt) {}
