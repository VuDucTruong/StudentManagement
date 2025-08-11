package com.vdt.student_management.academic.dto.response;

import com.vdt.student_management.common.enums.WeekDay;

public record ScheduleResponse(WeekDay weekDay, String room, int startPeriod, int endPeriod) {}
