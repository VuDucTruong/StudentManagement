package com.vdt.student_management.academic.model;

import com.vdt.student_management.common.model.BaseModel;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class Teacher extends BaseModel {

    String name;
    String degree;
    String specialization;
    String email;
    String phone;
    LocalDate hireDate;
    LocalDate dob;

    @ManyToOne
    @JoinColumn(name = "faculty_id", referencedColumnName = "id")
    Faculty faculty;

    @OneToMany(mappedBy = "teacher")
    List<ClassSection> classSections;
}
