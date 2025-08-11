package com.vdt.student_management.academic.repository;

import com.vdt.student_management.academic.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {}
