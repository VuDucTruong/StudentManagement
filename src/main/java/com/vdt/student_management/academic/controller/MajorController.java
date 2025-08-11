package com.vdt.student_management.academic.controller;

import com.vdt.student_management.academic.dto.request.AddMajorRequest;
import com.vdt.student_management.academic.dto.response.MajorResponse;
import com.vdt.student_management.academic.service.MajorService;
import com.vdt.student_management.common.dto.ApiResponse;
import com.vdt.student_management.common.dto.PageResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/majors")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Tag(name = "Majors", description = "Operations related to majors")
public class MajorController {

    MajorService majorService;

    @GetMapping
    ResponseEntity<ApiResponse<PageResponse<MajorResponse>>> getAllMajors(Pageable pageable) {
        return ResponseEntity.ok(ApiResponse.<PageResponse<MajorResponse>>builder()
                .code(200)
                .data(PageResponse.fromPage(majorService.getAllMajor(pageable)))
                .build());
    }

    @GetMapping("/{id}")
    ResponseEntity<ApiResponse<MajorResponse>> getMajorById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.<MajorResponse>builder()
                .code(200)
                .data(majorService.getMajor(id))
                .build());
    }

    @PostMapping
    @PreAuthorize("hasRole(T(com.vdt.student_management.common.enums.RoleType).ADMIN)")
    ResponseEntity<ApiResponse<MajorResponse>> createMajor(@RequestBody AddMajorRequest addMajorRequest) {
        return ResponseEntity.ok(ApiResponse.<MajorResponse>builder()
                .code(201)
                .data(majorService.upsertMajor(null, addMajorRequest))
                .build());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole(T(com.vdt.student_management.common.enums.RoleType).ADMIN)")
    ResponseEntity<ApiResponse<MajorResponse>> updateMajor(
            @PathVariable("id") Long id, @RequestBody AddMajorRequest addMajorRequest) {
        return ResponseEntity.ok(ApiResponse.<MajorResponse>builder()
                .code(200)
                .data(majorService.upsertMajor(id, addMajorRequest))
                .build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole(T(com.vdt.student_management.common.enums.RoleType).ADMIN)")
    ResponseEntity<ApiResponse<Void>> deleteMajor(@PathVariable("id") Long id) {
        majorService.deleteMajor(id);

        return ResponseEntity.ok(ApiResponse.<Void>builder()
                .code(200)
                .message("Delete successfully")
                .build());
    }

    @PostMapping("/recover/{id}")
    @PreAuthorize("hasRole(T(com.vdt.student_management.common.enums.RoleType).ADMIN)")
    ResponseEntity<ApiResponse<Void>> recoverMajor(@PathVariable("id") Long id) {
        majorService.recoverMajor(id);

        return ResponseEntity.ok(ApiResponse.<Void>builder()
                .code(200)
                .message("Recover successfully")
                .build());
    }
}
