package com.vdt.student_management.academic.repository;

import com.vdt.student_management.academic.model.StudentClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentClassRepository extends JpaRepository<StudentClass, Long> {

}
