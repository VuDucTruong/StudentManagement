package com.vdt.student_management.academic.service;

import com.vdt.student_management.academic.dto.request.AddPrerequisiteRequest;
import com.vdt.student_management.academic.dto.response.PrerequisiteResponse;

public interface PrerequisiteService {

    PrerequisiteResponse addPrerequisite(AddPrerequisiteRequest request);

    PrerequisiteResponse updatePrerequisite(Long id, AddPrerequisiteRequest request);

    void deletePrerequisite(Long id);
}
