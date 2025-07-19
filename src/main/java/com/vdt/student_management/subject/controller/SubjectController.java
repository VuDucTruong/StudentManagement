package com.vdt.student_management.subject.controller;

import com.vdt.student_management.common.dto.ApiResponse;
import com.vdt.student_management.subject.dto.AddSubjectRequest;
import com.vdt.student_management.subject.dto.SubjectResponse;
import com.vdt.student_management.subject.mapper.SubjectMapper;
import com.vdt.student_management.subject.service.SubjectService;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
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
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
@RequestMapping("/subjects")
@RequiredArgsConstructor
public class SubjectController {
  SubjectService subjectService;
  SubjectMapper subjectMapper;

  @GetMapping
  ResponseEntity<ApiResponse<List<SubjectResponse>>> getAllSubjects() {
    var response = ApiResponse.<List<SubjectResponse>>builder().code(200).data(subjectService.getAllSubjects()).build();
    return ResponseEntity.ok(response);
  }

  @GetMapping("/{id}")
  ResponseEntity<ApiResponse<SubjectResponse>> getSubject(@PathVariable Long id) {
    var response = ApiResponse.<SubjectResponse>builder().code(200).data(subjectService.getSubjectById(id)).build();

    return ResponseEntity.ok(response);
  }


  @PostMapping
  ResponseEntity<ApiResponse<SubjectResponse>> addSubject(@RequestBody AddSubjectRequest request) {
    var subject = subjectMapper.toSubject(request);
    var response = ApiResponse.<SubjectResponse>builder().code(201).data(subjectService.upsertSubject(subject)).build();

    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @PostMapping("/{id}")
  ResponseEntity<ApiResponse<Void>> recoverSubject(@PathVariable Long id) {
    subjectService.recoverSubjectById(id);

    return ResponseEntity.ok(ApiResponse.<Void>builder().code(200).message("Recover successfully").build());
  }

  @PutMapping("/{id}")
  ResponseEntity<ApiResponse<SubjectResponse>> updateSubject(@PathVariable Long id, @RequestBody AddSubjectRequest request) {
    var subject = subjectMapper.toSubject(request);
    subject.setId(id);
    var response = ApiResponse.<SubjectResponse>builder().code(200).data(subjectService.upsertSubject(subject)).build();

    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/{id}")
  ResponseEntity<ApiResponse<Void>> deleteSubject(@PathVariable Long id) {
    subjectService.deleteSubject(id);

    return ResponseEntity.ok(ApiResponse.<Void>builder().code(200).message("Delete successfully").build());
  }
}
