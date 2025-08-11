package com.vdt.student_management.academic.service;

import com.vdt.student_management.academic.dto.request.AddProgramRequest;
import com.vdt.student_management.academic.dto.response.ProgramResponse;

public interface ProgramService {

    ProgramResponse upsertProgram(Long id, AddProgramRequest request);

    void deleteProgram(Long id);

    ProgramResponse getProgram(Long id);

    void recoverProgram(Long id);
}
