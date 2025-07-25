package com.vdt.student_management.academic.service;

import com.vdt.student_management.academic.dto.request.AddScheduleRequest;
import com.vdt.student_management.academic.dto.response.ScheduleResponse;

public interface ScheduleService {
  ScheduleResponse upsertSchedule(Long id, AddScheduleRequest addScheduleRequest);
  void deleteSchedule(Long id);
}
