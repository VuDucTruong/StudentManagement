package com.vdt.student_management.academic.model;

import com.vdt.student_management.common.model.BaseModel;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class Major extends BaseModel {
    String name;

    @OneToMany(mappedBy = "major", cascade = CascadeType.ALL)
    List<Subject> subjects;

    @OneToMany(mappedBy = "major", cascade = CascadeType.ALL)
    List<Program> programs;

    @ManyToOne
    @JoinColumn(name = "faculty_id", referencedColumnName = "id")
    Faculty faculty;
}
