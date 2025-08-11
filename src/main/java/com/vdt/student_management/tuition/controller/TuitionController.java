package com.vdt.student_management.tuition.controller;

import com.vdt.student_management.common.dto.ApiResponse;
import com.vdt.student_management.tuition.dto.response.TuitionResponse;
import com.vdt.student_management.tuition.service.TuitionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tuitions")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Tag(name = "Tuition", description = "Operations related to students tuition")
public class TuitionController {

    TuitionService tuitionService;

    @GetMapping("/student/{studentId}")
    ApiResponse<List<TuitionResponse>> getAllTuitionsByStudentId(@PathVariable("studentId") Long studentId) {
        return ApiResponse.<List<TuitionResponse>>builder()
                .code(200)
                .data(tuitionService.getTuitionsByStudent(studentId))
                .build();
    }

    @GetMapping("/{tuitionId}")
    ApiResponse<TuitionResponse> getTuitionById(@PathVariable Long tuitionId) {
        return ApiResponse.<TuitionResponse>builder()
                .code(200)
                .data(tuitionService.getTuitionById(tuitionId))
                .build();
    }
}
