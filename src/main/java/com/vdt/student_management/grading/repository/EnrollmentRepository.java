package com.vdt.student_management.grading.repository;

import com.vdt.student_management.grading.model.Enrollment;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

  Page<Enrollment> findByStudentId(long studentId, Pageable pageable);

  List<Enrollment> findAllByStudentId(long studentId);
}
