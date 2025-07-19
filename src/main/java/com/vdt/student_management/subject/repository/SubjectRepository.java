package com.vdt.student_management.subject.repository;

import com.vdt.student_management.subject.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

}
