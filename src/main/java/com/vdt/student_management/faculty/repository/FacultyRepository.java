package com.vdt.student_management.faculty.repository;

import com.vdt.student_management.faculty.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {

}
