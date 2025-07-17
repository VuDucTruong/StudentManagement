package com.vdt.student_management.major.dto.response;

import java.util.Date;

public record MajorDetailResponse(
    Long id,
    String name,
    Date deletedAt,
    Date updatedAt
) {

}
