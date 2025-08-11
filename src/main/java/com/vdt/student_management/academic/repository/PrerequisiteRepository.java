package com.vdt.student_management.academic.repository;

import com.vdt.student_management.academic.model.Prerequisite;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrerequisiteRepository extends JpaRepository<Prerequisite, Long> {
    List<Prerequisite> findBySubjectId(Long subjectId);
}
