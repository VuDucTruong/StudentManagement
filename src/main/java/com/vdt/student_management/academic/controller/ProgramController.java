package com.vdt.student_management.academic.controller;

import com.vdt.student_management.academic.dto.request.AddProgramRequest;
import com.vdt.student_management.academic.dto.response.ProgramResponse;
import com.vdt.student_management.academic.service.ProgramService;
import com.vdt.student_management.common.dto.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/programs")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Tag(name = "Programs", description = "Operations related to major's programs")
public class ProgramController {

  ProgramService programService;

  @GetMapping("/{id}")
  ResponseEntity<ApiResponse<ProgramResponse>> getProgramById(@PathVariable("id") Long id) {
    return ResponseEntity.ok(
        ApiResponse.<ProgramResponse>builder().code(200).data(programService.getProgram(id))
            .build());
  }

  @PostMapping
  ResponseEntity<ApiResponse<ProgramResponse>> addProgram(@RequestBody AddProgramRequest request) {
    return ResponseEntity.ok(ApiResponse.<ProgramResponse>builder().code(201)
        .data(programService.upsertProgram(null, request)).build());
  }

  @PutMapping("/{id}")
  ResponseEntity<ApiResponse<ProgramResponse>> updateProgram(@PathVariable("id") Long id,@RequestBody AddProgramRequest request) {
    return ResponseEntity.ok(ApiResponse.<ProgramResponse>builder().code(200)
        .data(programService.upsertProgram(id, request)).build());
  }

  @DeleteMapping("/{id}")
  ResponseEntity<ApiResponse<Void>> deleteProgram(@PathVariable("id") Long id) {
    programService.deleteProgram(id);
    return ResponseEntity.ok(ApiResponse.<Void>builder().code(200).message("Delete successfully").build());
  }

  @PostMapping("/{id}")
  ResponseEntity<ApiResponse<Void>> recoverProgram(@PathVariable("id") Long id) {
    programService.recoverProgram(id);
    return ResponseEntity.ok(ApiResponse.<Void>builder().code(200).message("Recover successfully").build());
  }
}
