package com.vdt.student_management.academic.service;

import com.vdt.student_management.academic.dto.request.AddFacultyRequest;
import com.vdt.student_management.academic.dto.response.FacultyDetailResponse;
import com.vdt.student_management.academic.dto.response.FacultyResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FacultyService {

    FacultyDetailResponse upsertFaculty(Long id, AddFacultyRequest addFacultyRequest);

    FacultyDetailResponse getFacultyById(Long id);

    Page<FacultyResponse> getAllFaculty(Pageable pageable);

    void deleteFacultyById(Long id);

    void recoverFacultyById(Long id);
}
