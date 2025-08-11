package com.vdt.student_management.academic.service;

import com.vdt.student_management.academic.dto.request.AddSemesterRequest;
import com.vdt.student_management.academic.dto.response.SemesterResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SemesterService {

    SemesterResponse upsertSemester(Long id, AddSemesterRequest addSemesterRequest);

    void deleteSemester(Long id);

    Page<SemesterResponse> getAllSemesters(Pageable pageable);

    SemesterResponse getSemester(Long id);
}
