package com.vdt.student_management.academic.model;

import com.vdt.student_management.common.model.BaseModel;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class Faculty extends BaseModel {
    String name;

    @OneToMany(mappedBy = "faculty", cascade = CascadeType.ALL)
    List<Teacher> teachers = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "dean_id", referencedColumnName = "id")
    Teacher dean;

    @OneToMany(mappedBy = "faculty", cascade = CascadeType.ALL)
    List<Major> majors;
}
