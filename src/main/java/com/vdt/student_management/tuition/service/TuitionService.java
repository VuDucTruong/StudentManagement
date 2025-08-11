package com.vdt.student_management.tuition.service;

import com.vdt.student_management.tuition.dto.response.TuitionResponse;
import java.util.List;

public interface TuitionService {

    List<TuitionResponse> getTuitionsByStudent(Long studentId);

    TuitionResponse getTuitionById(Long id);
}
