package com.vdt.student_management.academic.controller;

import com.vdt.student_management.academic.dto.request.AddPrerequisiteRequest;
import com.vdt.student_management.academic.dto.response.PrerequisiteResponse;
import com.vdt.student_management.academic.service.PrerequisiteService;
import com.vdt.student_management.common.dto.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prerequisites")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Tag(name = "Prerequisite", description = "Operations related to precondition for subjects")
public class PrerequisiteController {

    PrerequisiteService prerequisiteService;

    @PostMapping
    @PreAuthorize("@authServiceImpl.hasMinRole(T(com.vdt.student_management.common.enums.RoleType).TEACHER)")
    ResponseEntity<ApiResponse<PrerequisiteResponse>> addPreconditionForSubject(
            @RequestBody AddPrerequisiteRequest request) {
        return ResponseEntity.ok(ApiResponse.<PrerequisiteResponse>builder()
                .code(200)
                .data(prerequisiteService.addPrerequisite(request))
                .build());
    }

    @PutMapping("/{id}")
    @PreAuthorize("@authServiceImpl.hasMinRole(T(com.vdt.student_management.common.enums.RoleType).TEACHER)")
    ResponseEntity<ApiResponse<PrerequisiteResponse>> updatePrecondition(
            @PathVariable("id") Long id, @RequestBody AddPrerequisiteRequest request) {
        return ResponseEntity.ok(ApiResponse.<PrerequisiteResponse>builder()
                .code(201)
                .data(prerequisiteService.updatePrerequisite(id, request))
                .build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@authServiceImpl.hasMinRole(T(com.vdt.student_management.common.enums.RoleType).TEACHER)")
    ResponseEntity<ApiResponse<Void>> deletePrecondition(@PathVariable("id") Long id) {
        prerequisiteService.deletePrerequisite(id);
        return ResponseEntity.ok(ApiResponse.<Void>builder()
                .code(200)
                .message("Permanent delete successfully")
                .build());
    }
}
