package com.vdt.student_management.academic.repository;

import com.vdt.student_management.academic.model.ClassSection;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassSectionRepository extends JpaRepository<ClassSection, Long> {
  Page<ClassSection> findByTeacherId(long teacherId, Pageable pageable);
}
