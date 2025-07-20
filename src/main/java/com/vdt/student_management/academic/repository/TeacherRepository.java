package com.vdt.student_management.academic.repository;

import com.vdt.student_management.academic.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

  @Modifying
  @Query("update Teacher t set t.deletedAt = NULL where t.id = :id and t.deletedAt is not NULL")
  int recoverDeletedTeacherById(@Param("id") Long id);
}
