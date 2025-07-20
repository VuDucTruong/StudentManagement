package com.vdt.student_management.academic.model;

import com.vdt.student_management.common.model.BaseModel;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class Subject extends BaseModel {
  String name;
  int credits;
  String semester;
  String description;
  String subjectCode;
  @ManyToOne
  @JoinColumn(name = "major_id", referencedColumnName = "id")
  Major major;

  @OneToMany(mappedBy = "subject")
  List<ClassSection> classSections;

}
