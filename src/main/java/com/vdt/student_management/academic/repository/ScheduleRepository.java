package com.vdt.student_management.academic.repository;

import com.vdt.student_management.academic.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {}
