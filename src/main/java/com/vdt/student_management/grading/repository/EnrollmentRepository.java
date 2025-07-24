package com.vdt.student_management.grading.repository;

import com.vdt.student_management.grading.model.Enrollment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
  List<Enrollment> findAllByStudent_Id(long studentId);
}
