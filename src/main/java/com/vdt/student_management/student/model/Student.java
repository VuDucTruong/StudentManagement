package com.vdt.student_management.student.model;

import com.vdt.student_management.common.enums.Gender;
import com.vdt.student_management.common.model.BaseModel;
import com.vdt.student_management.enrollment.model.Enrollment;
import com.vdt.student_management.major.model.Major;
import com.vdt.student_management.student.enums.StudentStatus;
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


  @OneToMany(mappedBy = "student")
  List<Enrollment> enrollments;

  @ManyToOne
      @JoinColumn(name = "major_id", referencedColumnName = "id")
  Major major;


}
