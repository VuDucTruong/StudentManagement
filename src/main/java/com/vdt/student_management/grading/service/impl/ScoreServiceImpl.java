package com.vdt.student_management.grading.service.impl;

import com.vdt.student_management.common.enums.ErrorCode;
import com.vdt.student_management.common.exception.AppException;
import com.vdt.student_management.grading.dto.request.UpdateScoreRequest;
import com.vdt.student_management.grading.dto.response.ScoreResponse;
import com.vdt.student_management.grading.mapper.ScoreMapper;
import com.vdt.student_management.grading.model.Enrollment;
import com.vdt.student_management.grading.model.Score;
import com.vdt.student_management.grading.repository.ScoreRepository;
import com.vdt.student_management.grading.service.ScoreService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
@RequiredArgsConstructor
public class ScoreServiceImpl implements ScoreService {
  ScoreRepository scoreRepository;
  ScoreMapper scoreMapper;


  @Override
  public ScoreResponse getScore(Long enrollmentId) {
    return scoreMapper.toScoreResponse(scoreRepository.findScoreByEnrollmentId(enrollmentId).orElseThrow(() -> new AppException(ErrorCode.SCORE_NOT_FOUND)));
  }

  @Override
  public ScoreResponse updateScore(Long scoreId, UpdateScoreRequest request) {
    var score = scoreRepository.findById(scoreId).orElseThrow(() -> new AppException(ErrorCode.SCORE_NOT_FOUND));

    scoreMapper.updateScore(request , score);

    return scoreMapper.toScoreResponse(scoreRepository.save(score));
  }
}
