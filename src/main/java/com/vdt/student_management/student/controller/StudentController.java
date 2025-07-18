package com.vdt.student_management.student.controller;


import com.vdt.student_management.common.dto.ApiResponse;
import com.vdt.student_management.student.dto.request.AddStudentRequest;
import com.vdt.student_management.student.dto.response.StudentResponse;
import com.vdt.student_management.student.mapper.StudentMapper;
import com.vdt.student_management.student.model.Student;
import com.vdt.student_management.student.service.StudentService;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
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
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
@RequiredArgsConstructor()
public class StudentController {
  StudentService studentService;
  StudentMapper studentMapper;

  // Add new student
  @PostMapping
  ApiResponse<Void> addStudent(@RequestBody AddStudentRequest request) {
    studentService.upsertStudent(studentMapper.toStudent(request));
    return new ApiResponse<>();
  }

  // Get student by id
  @GetMapping("/{id}")
  ApiResponse<StudentResponse> getStudentById(@PathVariable("id") Long id) {
    ApiResponse<StudentResponse> response = new ApiResponse<>();
    response.setData(studentService.getStudent(id));
    return response;
  }

  // Get all students
  @GetMapping
  ApiResponse<List<StudentResponse>> getStudents() {
    ApiResponse<List<StudentResponse>> response = new ApiResponse<>();
    response.setData(studentService.getStudents());
    return response;
  }

  // Delete student by id
  @DeleteMapping("/{id}")
  ApiResponse<Void> deleteStudent(@PathVariable("id") Long id) {
    studentService.deleteStudent(id);
    return new ApiResponse<>();
  }

  // Update student by id
  @PutMapping("/{id}")
  ApiResponse<Void> updateStudent(@PathVariable("id") Long id, @RequestBody AddStudentRequest request) {
    Student student = studentMapper.toStudent(request);
    student.setId(id);
    studentService.upsertStudent(student);
    return new ApiResponse<>();
  }

}
