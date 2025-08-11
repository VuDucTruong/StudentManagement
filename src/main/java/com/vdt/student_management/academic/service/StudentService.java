package com.vdt.student_management.academic.service;

import com.vdt.student_management.academic.dto.request.AddStudentRequest;
import com.vdt.student_management.academic.dto.response.StudentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudentService {
    StudentResponse upsertStudent(Long id, AddStudentRequest addStudentRequest);

    void deleteStudent(Long id);

    StudentResponse getStudent(Long id);

    Page<StudentResponse> getStudents(Pageable pageable);

    void recoverStudent(Long id);
}
