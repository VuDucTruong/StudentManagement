package com.vdt.student_management.major.model;


import com.vdt.student_management.common.model.BaseModel;
import com.vdt.student_management.subject.model.Subject;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class Major extends BaseModel {
  String name;
  @OneToMany(mappedBy = "major", cascade = CascadeType.ALL)
  List<Subject> subjects;
}
