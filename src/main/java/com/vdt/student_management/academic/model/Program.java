package com.vdt.student_management.academic.model;

import com.vdt.student_management.academic.enums.ProgramLevel;
import com.vdt.student_management.common.model.BaseModel;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Getter
@Setter
public class Program extends BaseModel {
    String name;
    ProgramLevel level;
    Integer duration;

    @ManyToOne
    @JoinColumn(name = "major_id", referencedColumnName = "id")
    Major major;
}
