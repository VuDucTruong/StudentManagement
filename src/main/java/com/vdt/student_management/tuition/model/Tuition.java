package com.vdt.student_management.tuition.model;

import com.vdt.student_management.academic.model.Semester;
import com.vdt.student_management.academic.model.Student;
import com.vdt.student_management.common.model.BaseModel;
import com.vdt.student_management.tuition.enums.TuitionStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class Tuition extends BaseModel {
    Long totalAmount;
    Long remainingAmount;
    TuitionStatus status;

    @ManyToOne
    @JoinColumn(name = "semester_id", referencedColumnName = "id")
    Semester semester;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    Student student;
}
