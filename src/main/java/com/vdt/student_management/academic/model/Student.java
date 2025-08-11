package com.vdt.student_management.academic.model;

import com.vdt.student_management.academic.enums.StudentStatus;
import com.vdt.student_management.common.enums.Gender;
import com.vdt.student_management.common.model.BaseModel;
import com.vdt.student_management.grading.model.Enrollment;
import com.vdt.student_management.tuition.model.Tuition;
import com.vdt.student_management.tuition.model.TuitionFee;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Student extends BaseModel {
    String name;
    LocalDate dob;
    Gender gender;
    String email;
    String phone;
    String address;
    LocalDate entryDate;
    StudentStatus status;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    List<Enrollment> enrollments = new ArrayList<>();

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    List<Tuition> tuitions = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "major_id", referencedColumnName = "id")
    Major major;

    @ManyToOne
    @JoinColumn(name = "class_id", referencedColumnName = "id")
    StudentClass studentClass;

    @ManyToOne
    @JoinColumn(name = "fee_id", referencedColumnName = "id")
    TuitionFee tuitionFee;
}
