package com.vdt.student_management.faculty.model;

import com.vdt.student_management.common.model.BaseModel;
import com.vdt.student_management.teacher.model.Teacher;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.util.Set;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class Faculty extends BaseModel {
  String name;


  @OneToMany(mappedBy = "faculty", cascade = CascadeType.ALL)
  Set<Teacher> teachers;

  @OneToOne
      @JoinColumn(name = "dean_id")
  Teacher teacher;

}
