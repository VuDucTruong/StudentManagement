package com.vdt.student_management.academic.dto.request;

import com.vdt.student_management.common.enums.WeekDay;

public record AddScheduleRequest(
    WeekDay weekDay,
    String room,
    int startPeriod,// Tiết bắt đầu
    int endPeriod, // Tiết kết thúc
    Long classSectionId
) {

}
