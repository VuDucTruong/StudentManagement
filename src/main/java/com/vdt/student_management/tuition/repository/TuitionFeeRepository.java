package com.vdt.student_management.tuition.repository;

import com.vdt.student_management.tuition.model.TuitionFee;
import io.lettuce.core.dynamic.annotation.Param;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TuitionFeeRepository extends JpaRepository<TuitionFee, Long> {

    @Query(
            "select t from TuitionFee t where t.academicYear = :academicYear and t.program.id = :programId and :currentDate between t.effectiveDate and t.expiryDate")
    Optional<TuitionFee> findValidTuitionFee(
            @Param("academicYear") int academicYear,
            @Param("program_id") Long programId,
            @Param("currentDate") LocalDate currentDate);
}
