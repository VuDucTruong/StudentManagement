package com.vdt.student_management.tuition.controller;

import com.vdt.student_management.common.dto.ApiResponse;
import com.vdt.student_management.common.dto.PageResponse;
import com.vdt.student_management.tuition.dto.request.TuitionFeeRequest;
import com.vdt.student_management.tuition.dto.response.TuitionFeeResponse;
import com.vdt.student_management.tuition.service.TuitionFeeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fees")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Tag(name = "Tuition Fee", description = "Operations related to students tuition fee")
public class TuitionFeeController {

    TuitionFeeService tuitionFeeService;

    @GetMapping
    ApiResponse<PageResponse<TuitionFeeResponse>> getAllTuitionFees(Pageable pageable) {
        return ApiResponse.<PageResponse<TuitionFeeResponse>>builder()
                .code(200)
                .data(PageResponse.fromPage(tuitionFeeService.getAllTuitionFees(pageable)))
                .build();
    }

    @GetMapping("/{id}")
    ApiResponse<TuitionFeeResponse> getTuitionFee(@PathVariable Long id) {
        return ApiResponse.<TuitionFeeResponse>builder()
                .code(200)
                .data(tuitionFeeService.getTuitionFeeById(id))
                .build();
    }

    @PostMapping
    ApiResponse<TuitionFeeResponse> addTuitionFee(@RequestBody TuitionFeeRequest tuitionFeeRequest) {
        return ApiResponse.<TuitionFeeResponse>builder()
                .code(201)
                .data(tuitionFeeService.addTuitionFee(tuitionFeeRequest))
                .build();
    }

    @PutMapping("/{id}")
    ApiResponse<TuitionFeeResponse> updateTuitionFee(
            @PathVariable Long id, @RequestBody TuitionFeeRequest tuitionFeeRequest) {
        return ApiResponse.<TuitionFeeResponse>builder()
                .code(200)
                .data(tuitionFeeService.updateTuitionFee(id, tuitionFeeRequest))
                .build();
    }

    @DeleteMapping("/{id}")
    ApiResponse<TuitionFeeResponse> deleteTuitionFee(@PathVariable Long id) {
        tuitionFeeService.deleteTuitionFee(id);

        return ApiResponse.<TuitionFeeResponse>builder()
                .code(200)
                .message("Delete successfully")
                .build();
    }

    @GetMapping("/active")
    ApiResponse<TuitionFeeResponse> getActiveTuitionFees(
            @RequestParam Integer academicYear, @RequestParam Long programId) {
        return ApiResponse.<TuitionFeeResponse>builder()
                .code(200)
                .data(tuitionFeeService.getActiveTuitionFee(academicYear, programId))
                .build();
    }
}
