package com.vdt.student_management.grading.repository;

import com.vdt.student_management.grading.model.Score;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreRepository extends JpaRepository<Score, Long> {

    Optional<Score> findScoreByEnrollmentId(Long enrollmentId);
}
