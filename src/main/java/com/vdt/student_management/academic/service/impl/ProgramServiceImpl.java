package com.vdt.student_management.academic.service.impl;

import com.vdt.student_management.academic.dto.request.AddProgramRequest;
import com.vdt.student_management.academic.dto.response.ProgramResponse;
import com.vdt.student_management.academic.mapper.ProgramMapper;
import com.vdt.student_management.academic.repository.ProgramRepository;
import com.vdt.student_management.academic.service.ProgramService;
import com.vdt.student_management.common.enums.ErrorCode;
import com.vdt.student_management.common.exception.AppException;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ProgramServiceImpl implements ProgramService {
    ProgramRepository programRepository;
    ProgramMapper programMapper;

    @Override
    public ProgramResponse upsertProgram(Long id, AddProgramRequest request) {
        var program = programMapper.toProgram(request);

        if (id != null) {
            program.setId(id);
            programRepository
                    .findById(id)
                    .ifPresentOrElse(
                            p -> {
                                if (p.getDeletedAt() != null) {
                                    throw new AppException(ErrorCode.PROGRAM_NOT_FOUND);
                                }
                            },
                            () -> {
                                throw new AppException(ErrorCode.PROGRAM_NOT_FOUND);
                            });
        }

        return programMapper.toProgramResponse(programRepository.save(program));
    }

    @Override
    public void deleteProgram(Long id) {
        programRepository
                .findById(id)
                .ifPresentOrElse(
                        p -> {
                            if (p.getDeletedAt() != null) {
                                programRepository.deleteById(id);
                            } else {
                                p.setDeletedAt(LocalDateTime.now());
                                programRepository.save(p);
                            }
                        },
                        () -> {
                            throw new AppException(ErrorCode.PROGRAM_NOT_FOUND);
                        });
    }

    @Override
    public ProgramResponse getProgram(Long id) {
        return programMapper.toProgramResponse(
                programRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.PROGRAM_NOT_FOUND)));
    }

    @Override
    public void recoverProgram(Long id) {
        programRepository
                .findById(id)
                .ifPresentOrElse(
                        p -> {
                            if (p.getDeletedAt() != null) {
                                throw new AppException(ErrorCode.PROGRAM_RECOVER_FAILED);
                            } else {
                                p.setDeletedAt(null);
                                programRepository.save(p);
                            }
                        },
                        () -> {
                            throw new AppException(ErrorCode.PROGRAM_NOT_FOUND);
                        });
    }
}
