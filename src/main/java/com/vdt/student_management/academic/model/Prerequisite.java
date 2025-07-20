package com.vdt.student_management.academic.model;

import com.vdt.student_management.common.model.BaseModel;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Getter
@Setter
public class Prerequisite extends BaseModel {
  @ManyToOne
  @JoinColumn(name = "subject_id" , referencedColumnName = "id")
  Subject subject;

  @ManyToOne
  @JoinColumn(name = "prerequisite_id" , referencedColumnName = "id")
  Subject prerequisite;

  boolean passRequirement;
}
