package com.vdt.student_management.grading.model;

import com.vdt.student_management.common.model.BaseModel;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class Score extends BaseModel {
  Float processScore;
  Float midTermScore;
  Float finalScore;


  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
      @JoinColumn(name = "enrollment_id", referencedColumnName = "id")
  Enrollment enrollment;

}
