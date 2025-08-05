package com.vdt.student_management.tuition.repository;

import com.vdt.student_management.tuition.model.Tuition;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TuitionRepository extends JpaRepository<Tuition, Long> {

  List<Tuition> getByStudentId(Long studentId);

}
