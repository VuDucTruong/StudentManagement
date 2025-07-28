package com.vdt.student_management.grading.model;


import com.vdt.student_management.academic.model.ClassSection;
import com.vdt.student_management.academic.model.Student;
import com.vdt.student_management.common.model.BaseModel;
import com.vdt.student_management.grading.enums.EnrollmentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Enrollment extends BaseModel {

  EnrollmentStatus status;

  @ManyToOne
  @JoinColumn(name = "student_id", referencedColumnName = "id")
  Student student;

  @ManyToOne
  @JoinColumn(name = "section_id", referencedColumnName = "id")
  ClassSection classSection;
}
