package com.vdt.student_management.academic.controller;


import com.vdt.student_management.common.dto.ApiResponse;
import com.vdt.student_management.academic.dto.request.AddStudentRequest;
import com.vdt.student_management.academic.dto.response.StudentResponse;
import com.vdt.student_management.academic.service.StudentService;
import com.vdt.student_management.common.dto.PageResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/students")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Tag(name = "Students", description = "Operations related to students")
public class StudentController {

  StudentService studentService;

  // Add new student
  @PostMapping
  @PreAuthorize("hasRole(T(com.vdt.student_management.common.enums.RoleType).ADMIN)")
  ResponseEntity<ApiResponse<StudentResponse>> addStudent(@RequestBody AddStudentRequest request) {

    return ResponseEntity.status(HttpStatus.CREATED)
        .body(ApiResponse.<StudentResponse>builder().code(201).data(studentService.upsertStudent(null,request)).build());
  }

  // Get student by id
  @GetMapping("/{id}")
  ResponseEntity<ApiResponse<StudentResponse>> getStudentById(@PathVariable("id") Long id) {

    var response = ApiResponse.<StudentResponse>builder().code(200)
        .data(studentService.getStudent(id)).build();

    return ResponseEntity.ok(response);
  }

  // Get all students
  @GetMapping
  @PreAuthorize("@authServiceImpl.hasMinRole(T(com.vdt.student_management.common.enums.RoleType).TEACHER)")
  ResponseEntity<ApiResponse<PageResponse<StudentResponse>>> getStudents(Pageable pageable) {
    var response = ApiResponse.<PageResponse<StudentResponse>>builder().code(200)
        .data(PageResponse.fromPage(studentService.getStudents(pageable))).build();

    return ResponseEntity.ok(response);
  }

  // Delete student by id
  @DeleteMapping("/{id}")
  @PreAuthorize("hasRole(T(com.vdt.student_management.common.enums.RoleType).ADMIN)")
  ResponseEntity<ApiResponse<Void>> deleteStudent(@PathVariable("id") Long id) {
    studentService.deleteStudent(id);
    return ResponseEntity.ok(
        ApiResponse.<Void>builder().code(200).message("Delete successfully").build());
  }

  // Update student by id
  @PutMapping("/{id}")
  ResponseEntity<ApiResponse<StudentResponse>> updateStudent(@PathVariable("id") Long id,
      @RequestBody AddStudentRequest request) {

    var response = ApiResponse.<StudentResponse>builder().code(200)
        .data(studentService.upsertStudent(id , request)).build();
    return ResponseEntity.ok(response);
  }


  @PostMapping("/recover/{id}")
  @PreAuthorize("hasRole(T(com.vdt.student_management.common.enums.RoleType).ADMIN)")
  ResponseEntity<ApiResponse<Void>> recoverStudent(@PathVariable("id") Long id) {
    studentService.recoverStudent(id);

    return ResponseEntity.ok(
        ApiResponse.<Void>builder().code(200).message("Recover successfully").build());
  }

}
