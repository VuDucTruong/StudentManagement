package com.vdt.student_management.academic.service.impl;

import com.vdt.student_management.academic.dto.request.AddPrerequisiteRequest;
import com.vdt.student_management.academic.dto.response.PrerequisiteResponse;
import com.vdt.student_management.academic.mapper.PrerequisiteMapper;
import com.vdt.student_management.academic.model.Prerequisite;
import com.vdt.student_management.academic.repository.PrerequisiteRepository;
import com.vdt.student_management.academic.repository.SubjectRepository;
import com.vdt.student_management.academic.service.PrerequisiteService;
import com.vdt.student_management.common.enums.ErrorCode;
import com.vdt.student_management.common.exception.AppException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class PrerequisiteServiceImpl implements PrerequisiteService {

  PrerequisiteRepository prerequisiteRepository;
  SubjectRepository subjectRepository;
  PrerequisiteMapper prerequisiteMapper;

  @Override
  public PrerequisiteResponse addPrerequisite(AddPrerequisiteRequest request) {
    var subject = subjectRepository.findById(request.subjectId())
        .orElseThrow(() -> new AppException(ErrorCode.SUBJECT_NOT_FOUND));

    var pre = subjectRepository.findById(request.prerequisiteId())
        .orElseThrow(() -> new AppException(ErrorCode.PREREQUISITE_NOT_FOUND));

    Prerequisite prerequisite = Prerequisite.builder().prerequisiteSubject(pre).subject(subject)
        .passRequirement(
            request.passRequirement()).build();

    return prerequisiteMapper.toPrerequisiteResponse(prerequisiteRepository.save(prerequisite));
  }

  @Override
  public PrerequisiteResponse updatePrerequisite(Long id, AddPrerequisiteRequest request) {
    var prerequisite = prerequisiteRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.PREREQUISITE_NOT_FOUND));

    var subject = subjectRepository.findById(request.subjectId()).orElseThrow(() -> new AppException(ErrorCode.SUBJECT_NOT_FOUND));

    var pre = subjectRepository.findById(request.prerequisiteId()).orElseThrow(() -> new AppException(ErrorCode.PREREQUISITE_NOT_FOUND));

    prerequisite.setPrerequisiteSubject(pre);
    prerequisite.setPassRequirement(request.passRequirement());
    prerequisite.setSubject(subject);

    return prerequisiteMapper.toPrerequisiteResponse(prerequisiteRepository.save(prerequisite));
  }

  @Override
  public void deletePrerequisite(Long id) {
    prerequisiteRepository.deleteById(id);
  }
}
