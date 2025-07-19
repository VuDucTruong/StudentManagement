package com.vdt.student_management.class_section.dto;

import com.vdt.student_management.schedule.dto.ScheduleResponse;
import com.vdt.student_management.subject.dto.SubjectResponse;
import com.vdt.student_management.teacher.dto.TeacherResponse;
import java.time.LocalDate;

public record ClassSectionResponse(
    Long id,
    String semester,
    String name,
    LocalDate startDate,
    LocalDate endDate,
    SubjectResponse subject,
    int numOfStudents,
    ScheduleResponse schedule,
    TeacherResponse teacher
) {

}
