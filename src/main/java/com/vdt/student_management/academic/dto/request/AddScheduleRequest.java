package com.vdt.student_management.academic.dto.request;

import com.vdt.student_management.common.enums.WeekDay;
import jakarta.validation.constraints.NotNull;

public record AddScheduleRequest(
        @NotNull(message = "WEEK_DAY_REQUIRED") WeekDay weekDay,
        String room,
        int startPeriod, // Tiết bắt đầu
        int endPeriod, // Tiết kết thúc
        @NotNull(message = "CLASS_SECTION_REQUIRED") Long classSectionId) {}
