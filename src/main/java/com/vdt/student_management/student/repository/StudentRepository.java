package com.vdt.student_management.student.repository;

import com.vdt.student_management.student.model.Student;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student , Long> {

  int deleteStudentByIdIn(List<Long> ids);
}
