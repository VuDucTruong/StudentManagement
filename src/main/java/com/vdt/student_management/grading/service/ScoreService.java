package com.vdt.student_management.grading.service;

import com.vdt.student_management.grading.dto.request.UpdateScoreRequest;
import com.vdt.student_management.grading.dto.response.ScoreResponse;

public interface ScoreService {

    ScoreResponse getScore(Long enrollmentId);

    ScoreResponse updateScore(Long scoreId, UpdateScoreRequest request);
}
