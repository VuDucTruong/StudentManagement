package com.vdt.student_management.academic.controller;

import com.vdt.student_management.academic.dto.request.AddScheduleRequest;
import com.vdt.student_management.academic.dto.response.ScheduleResponse;
import com.vdt.student_management.academic.service.ScheduleService;
import com.vdt.student_management.common.dto.ApiResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schedules")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ScheduleController {

  ScheduleService scheduleService;

  @PostMapping
  ResponseEntity<ApiResponse<ScheduleResponse>> addScheduleForClassSection(@RequestBody
  AddScheduleRequest request) {
    return ResponseEntity.ok(ApiResponse.<ScheduleResponse>builder().code(201)
        .data(scheduleService.upsertSchedule(null, request)).build());
  }

  @PutMapping("/{id}")
  ResponseEntity<ApiResponse<ScheduleResponse>> updateSchedule(@PathVariable("id") Long id, @RequestBody AddScheduleRequest request) {
    return ResponseEntity.ok(ApiResponse.<ScheduleResponse>builder().code(200)
        .data(scheduleService.upsertSchedule(id, request)).build());
  }

  @DeleteMapping("/{id}")
  ResponseEntity<ApiResponse<Void>> deleteSchedule(@PathVariable("id") Long id) {
    scheduleService.deleteSchedule(id);
    return ResponseEntity.ok(ApiResponse.<Void>builder().code(200).message("Delete successfully").build());
  }
}
