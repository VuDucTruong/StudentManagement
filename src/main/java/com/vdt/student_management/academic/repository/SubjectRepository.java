package com.vdt.student_management.academic.repository;

import com.vdt.student_management.academic.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

}
