package com.vdt.student_management.academic.service.impl;

import com.vdt.student_management.academic.dto.request.AddSubjectRequest;
import com.vdt.student_management.academic.dto.response.SubjectResponse;
import com.vdt.student_management.academic.mapper.SubjectMapper;
import com.vdt.student_management.academic.model.Subject;
import com.vdt.student_management.academic.repository.SubjectRepository;
import com.vdt.student_management.academic.service.SubjectService;
import com.vdt.student_management.common.enums.ErrorCode;
import com.vdt.student_management.common.exception.AppException;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    SubjectRepository subjectRepository;
    SubjectMapper subjectMapper;

    @Override
    public SubjectResponse upsertSubject(Long id, AddSubjectRequest request) {
        var subject = subjectMapper.toSubject(request);

        if (id != null) {
            subject.setId(id);
            subjectRepository
                    .findById(subject.getId())
                    .ifPresentOrElse(
                            s -> {
                                if (s.getDeletedAt() != null) {
                                    throw new AppException(ErrorCode.CANT_UPDATE_DELETED_RESOURCE);
                                }
                            },
                            () -> {
                                throw new AppException(ErrorCode.SUBJECT_NOT_FOUND);
                            });
        }

        return subjectMapper.toSubjectResponse(subjectRepository.save(subject));
    }

    @Override
    public void deleteSubject(Long id) {
        subjectRepository
                .findById(id)
                .ifPresentOrElse(
                        s -> {
                            if (s.getDeletedAt() == null) {
                                s.setDeletedAt(LocalDateTime.now());
                                subjectRepository.save(s);
                            } else {
                                subjectRepository.deleteById(id);
                            }
                        },
                        () -> {
                            throw new AppException(ErrorCode.SUBJECT_NOT_FOUND);
                        });
    }

    @Override
    public Page<SubjectResponse> getAllSubjects(Pageable pageable) {
        return subjectRepository.findAll(pageable).map(subjectMapper::toSubjectResponse);
    }

    @Override
    public SubjectResponse getSubjectById(Long id) {
        return subjectRepository
                .findById(id)
                .map(subjectMapper::toSubjectResponse)
                .orElseThrow(() -> new AppException(ErrorCode.SUBJECT_NOT_FOUND));
    }

    @Override
    public void recoverSubjectById(Long id) {
        Subject subject =
                subjectRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.SUBJECT_RECOVER_FAILED));

        if (subject.getDeletedAt() != null) {
            subject.setDeletedAt(null);
            subjectRepository.save(subject);
            return;
        }

        throw new AppException(ErrorCode.SUBJECT_RECOVER_FAILED);
    }
}
