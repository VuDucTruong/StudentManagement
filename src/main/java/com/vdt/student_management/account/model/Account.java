package com.vdt.student_management.account.model;

import com.vdt.student_management.account.enums.RoleEnum;
import com.vdt.student_management.common.model.BaseModel;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class Account extends BaseModel {
  String username;
  String password;
  Long linkedId; // student or teacher id
  RoleEnum role;
}
