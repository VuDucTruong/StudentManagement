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
public class ClassSection extends BaseModel {
  String name;
  LocalDate startDate;
  LocalDate endDate;


  @ManyToOne
  @JoinColumn(name = "subject_id", referencedColumnName = "id")
  Subject subject;

  @ManyToOne
  @JoinColumn(name = "teacher_id", referencedColumnName = "id")
  Teacher teacher;

  @OneToMany(mappedBy = "classSection")
  List<Enrollment> enrollments;

  @OneToMany(mappedBy = "classSection")
  List<Schedule> schedules;

  @ManyToOne
  @JoinColumn(name = "semester_id" , referencedColumnName = "id")
  Semester semester;
}
