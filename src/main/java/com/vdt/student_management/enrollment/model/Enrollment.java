package com.vdt.student_management.enrollment.model;


import com.vdt.student_management.class_section.model.ClassSection;
import com.vdt.student_management.common.model.BaseModel;
import com.vdt.student_management.score.model.Score;
import com.vdt.student_management.student.model.Student;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import java.time.LocalDate;
import java.util.Date;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class Enrollment extends BaseModel {

  LocalDate enrollmentDate;

  @ManyToOne
  @JoinColumn(name = "student_id", referencedColumnName = "id")
  Student student;

  @ManyToOne
  @JoinColumn(name = "section_id", referencedColumnName = "id")
  ClassSection classSection;

  @OneToOne(mappedBy = "enrollment", cascade = CascadeType.ALL)
  Score score;
}
