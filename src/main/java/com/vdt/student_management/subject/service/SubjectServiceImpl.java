package com.vdt.student_management.subject.service;

import com.vdt.student_management.common.enums.ErrorCode;
import com.vdt.student_management.common.exception.AppException;
import com.vdt.student_management.subject.dto.SubjectResponse;
import com.vdt.student_management.subject.mapper.SubjectMapper;
import com.vdt.student_management.subject.model.Subject;
import com.vdt.student_management.subject.repository.SubjectRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

  SubjectRepository subjectRepository;
  SubjectMapper subjectMapper;


  @Override
  public SubjectResponse upsertSubject(Subject subject) {
    subject.setUpdatedAt(LocalDateTime.now());

    if (subject.getId() != null) {
      subjectRepository.findById(subject.getId()).ifPresentOrElse(s -> {
        if (s.getDeletedAt() != null) {
          throw new AppException(ErrorCode.CANT_UPDATE_DELETED_RESOURCE);
        }
      }, () -> {
        throw new AppException(ErrorCode.RESOURCE_NOT_FOUND);
      });
    }

    return subjectMapper.toSubjectResponse(subjectRepository.save(subject));
  }

  @Override
  public void deleteSubject(Long id) {
    subjectRepository.findById(id).ifPresentOrElse(s -> {
      if (s.getDeletedAt() == null) {
        s.setDeletedAt(LocalDateTime.now());
        subjectRepository.save(s);
      } else {
        subjectRepository.deleteById(id);
      }
    }, () -> {
      throw new AppException(ErrorCode.RESOURCE_NOT_FOUND);
    });
  }

  @Override
  public List<SubjectResponse> getAllSubjects() {
    return subjectRepository.findAll().stream().map(subjectMapper::toSubjectResponse).toList();
  }

  @Override
  public SubjectResponse getSubjectById(Long id) {
    return subjectRepository.findById(id).map(subjectMapper::toSubjectResponse)
        .orElseThrow(() -> new AppException(ErrorCode.RESOURCE_NOT_FOUND));
  }

  @Override
  public void recoverSubjectById(Long id) {
    Subject subject = subjectRepository.findById(id)
        .orElseThrow(() -> new AppException(ErrorCode.SUBJECT_RECOVER_FAILED));

    if (subject.getDeletedAt() != null) {
      subject.setDeletedAt(null);
      subjectRepository.save(subject);
      return;
    }

    throw new AppException(ErrorCode.SUBJECT_RECOVER_FAILED);
  }
}
