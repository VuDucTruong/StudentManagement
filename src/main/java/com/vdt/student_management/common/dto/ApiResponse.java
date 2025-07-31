package com.vdt.student_management.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Map;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL) // the output will not include null data
public class ApiResponse<T> {

  int code;
  String message;
  T data;
}
