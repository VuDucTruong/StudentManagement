package com.vdt.student_management.academic.mapper;

import com.vdt.student_management.academic.dto.request.AddScheduleRequest;
import com.vdt.student_management.academic.dto.response.ScheduleResponse;
import com.vdt.student_management.academic.model.Schedule;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "classSection", ignore = true)
    Schedule toSchedule(AddScheduleRequest addScheduleRequest);

    ScheduleResponse toScheduleResponse(Schedule schedule);
}
