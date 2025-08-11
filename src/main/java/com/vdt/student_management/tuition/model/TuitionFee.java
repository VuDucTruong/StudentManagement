package com.vdt.student_management.tuition.model;

import com.vdt.student_management.academic.model.Program;
import com.vdt.student_management.common.model.BaseModel;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Getter
@Setter
public class TuitionFee extends BaseModel {
    Integer academicYear;
    Long creditFee;
    Long fixedFee;
    LocalDate effectiveDate;
    LocalDate expiryDate;

    @ManyToOne
    @JoinColumn(name = "program_id", referencedColumnName = "id")
    Program program;
}
