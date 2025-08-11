package com.vdt.student_management.academic.repository;

import com.vdt.student_management.academic.model.Program;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramRepository extends JpaRepository<Program, Long> {}
