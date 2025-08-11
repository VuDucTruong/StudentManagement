package com.vdt.student_management.academic.service;

import com.vdt.student_management.academic.dto.request.AddClassSectionRequest;
import com.vdt.student_management.academic.dto.response.ClassSectionResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClassSectionService {

    ClassSectionResponse upsertClassSection(Long id, AddClassSectionRequest addClassSectionRequest);

    void deleteClassSectionById(Long id);

    ClassSectionResponse getClassSectionById(Long id);

    Page<ClassSectionResponse> getAllClassSections(Pageable pageable);

    void recoverClassSectionById(Long id);

    Page<ClassSectionResponse> getAllClassSectionsByTeacherId(Long teacherId, Pageable pageable);
}
