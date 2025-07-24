package com.vdt.student_management.academic.controller;


import com.vdt.student_management.common.dto.ApiResponse;
import com.vdt.student_management.academic.dto.request.AddStudentRequest;
import com.vdt.student_management.academic.dto.response.StudentResponse;
import com.vdt.student_management.academic.service.StudentService;
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
@RequestMapping("/students")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor()
public class StudentController {

  StudentService studentService;

  // Add new student
  @PostMapping
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
  ResponseEntity<ApiResponse<List<StudentResponse>>> getStudents() {
    var response = ApiResponse.<List<StudentResponse>>builder().code(200)
        .data(studentService.getStudents()).build();

    return ResponseEntity.ok(response);
  }

  // Delete student by id
  @DeleteMapping("/{id}")
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
  ResponseEntity<ApiResponse<Void>> recoverStudent(@PathVariable("id") Long id) {
    studentService.recoverStudent(id);

    return ResponseEntity.ok(
        ApiResponse.<Void>builder().code(200).message("Recover successfully").build());
  }

}
