package com.vdt.student_management.account.model;

import com.vdt.student_management.common.enums.RoleType;
import com.vdt.student_management.common.model.BaseModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import java.util.Set;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class Account extends BaseModel {
  @Column(unique = true, nullable = false)
  String username;
  String password;
  Long linkedId;
  Set<RoleType> roles;
  String avatarUrl;
}
