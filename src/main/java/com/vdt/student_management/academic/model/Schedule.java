package com.vdt.student_management.academic.model;

import com.vdt.student_management.common.enums.WeekDay;
import com.vdt.student_management.common.model.BaseModel;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class Schedule extends BaseModel {
    WeekDay weekDay;
    String room;
    int startPeriod; // Tiết bắt đầu
    int endPeriod; // Tiết kết thúc

    @ManyToOne
    @JoinColumn(name = "section_id", referencedColumnName = "id")
    ClassSection classSection;
}
