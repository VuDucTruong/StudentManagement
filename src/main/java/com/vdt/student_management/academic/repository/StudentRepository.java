package com.vdt.student_management.academic.repository;

import com.vdt.student_management.academic.model.Student;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student , Long> {

  int deleteStudentByIdIn(List<Long> ids);
}
