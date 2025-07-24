package com.vdt.student_management.grading.dto.request;

public record UpdateScoreRequest(
    Float processScore,
    Float midTermScore,
    Float finalScore
) {

}
