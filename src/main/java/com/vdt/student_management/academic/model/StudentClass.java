package com.vdt.student_management.academic.model;

import com.vdt.student_management.common.model.BaseModel;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class StudentClass extends BaseModel {
  String name;
  Integer startYear;
  Integer endYear;
}
