package com.vdt.student_management.academic.service.impl;

import com.vdt.student_management.academic.dto.request.AddScheduleRequest;
import com.vdt.student_management.academic.dto.response.ScheduleResponse;
import com.vdt.student_management.academic.mapper.ScheduleMapper;
import com.vdt.student_management.academic.repository.ClassSectionRepository;
import com.vdt.student_management.academic.repository.ScheduleRepository;
import com.vdt.student_management.academic.service.ScheduleService;
import com.vdt.student_management.common.enums.ErrorCode;
import com.vdt.student_management.common.exception.AppException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

  ScheduleRepository scheduleRepository;
  ClassSectionRepository classSectionRepository;
  ScheduleMapper scheduleMapper;

  @Override
  public ScheduleResponse upsertSchedule(Long id, AddScheduleRequest addScheduleRequest) {
    var classSection = classSectionRepository.findById(addScheduleRequest.classSectionId()).orElseThrow(() -> new AppException(
        ErrorCode.CLASS_SECTION_NOT_FOUND));

    var schedule = scheduleMapper.toSchedule(addScheduleRequest);
    schedule.setClassSection(classSection);

    if (id != null) {
      schedule.setId(id);
      scheduleRepository.findById(id).ifPresentOrElse(s -> {
            if (s.getDeletedAt() != null) {
              throw new AppException(ErrorCode.SCHEDULE_NOT_FOUND);
            }
          }
          , () -> {
            throw new AppException(ErrorCode.SCHEDULE_NOT_FOUND);
          });
    }

    return scheduleMapper.toScheduleResponse(scheduleRepository.save(schedule));

  }

  @Override
  public void deleteSchedule(Long id) {
    scheduleRepository.deleteById(id);
  }
}
