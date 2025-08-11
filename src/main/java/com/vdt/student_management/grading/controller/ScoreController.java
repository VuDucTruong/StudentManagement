package com.vdt.student_management.grading.controller;

import com.vdt.student_management.common.dto.ApiResponse;
import com.vdt.student_management.grading.dto.request.UpdateScoreRequest;
import com.vdt.student_management.grading.dto.response.ScoreResponse;
import com.vdt.student_management.grading.service.ScoreService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scores")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Tag(name = "Scores", description = "Operations related to students score in class sections")
public class ScoreController {

    ScoreService scoreService;

    @PatchMapping("/{id}")
    ResponseEntity<ApiResponse<ScoreResponse>> updateScore(
            @PathVariable("id") Long id, @RequestBody UpdateScoreRequest request) {
        return ResponseEntity.ok(ApiResponse.<ScoreResponse>builder()
                .code(200)
                .data(scoreService.updateScore(id, request))
                .build());
    }
}
