package com.vdt.student_management.academic.service.impl;

import com.vdt.student_management.academic.dto.request.AddClassSectionRequest;
import com.vdt.student_management.academic.dto.response.ClassSectionResponse;
import com.vdt.student_management.academic.mapper.ClassSectionMapper;
import com.vdt.student_management.academic.repository.ClassSectionRepository;
import com.vdt.student_management.academic.repository.ScheduleRepository;
import com.vdt.student_management.academic.repository.SemesterRepository;
import com.vdt.student_management.academic.repository.SubjectRepository;
import com.vdt.student_management.academic.repository.TeacherRepository;
import com.vdt.student_management.academic.service.ClassSectionService;
import com.vdt.student_management.common.enums.ErrorCode;
import com.vdt.student_management.common.exception.AppException;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ClassSectionServiceImpl implements ClassSectionService {

  ClassSectionRepository classSectionRepository;
  SubjectRepository subjectRepository;
  ScheduleRepository scheduleRepository;
  SemesterRepository semesterRepository;
  TeacherRepository teacherRepository;
  ClassSectionMapper classSectionMapper;

  @Override
  public ClassSectionResponse upsertClassSection(Long id, AddClassSectionRequest request) {
    var classSection = classSectionMapper.toClassSection(request);
    if (id != null) {
      classSection.setId(id);
      classSectionRepository.findById(classSection.getId()).ifPresentOrElse(s -> {
        if (s.getDeletedAt() != null) {
          throw new AppException(ErrorCode.CANT_UPDATE_DELETED_RESOURCE);
        }
      }, () -> {
        throw new AppException(ErrorCode.CLASS_SECTION_NOT_FOUND);
      });
    }

    if (request.subjectId() != null && !subjectRepository.existsById(request.subjectId())) {
      throw new AppException(ErrorCode.SUBJECT_NOT_FOUND);
    }

    if (request.scheduleId() != null && !scheduleRepository.existsById(request.scheduleId())) {
      throw new AppException(ErrorCode.SCHEDULE_NOT_FOUND);
    }

    if (request.semesterId() != null && !semesterRepository.existsById(request.semesterId())) {
      throw new AppException(ErrorCode.SEMESTER_NOT_FOUND);
    }

    if (request.teacherId() != null && !teacherRepository.existsById(request.teacherId())) {
      throw new AppException(ErrorCode.TEACHER_NOT_FOUND);
    }

    return classSectionMapper.toClassSectionResponse(classSectionRepository.save(classSection));
  }

  @Override
  public void deleteClassSectionById(Long id) {
    classSectionRepository.findById(id).ifPresentOrElse(s -> {
      if (s.getDeletedAt() == null) {
        s.setDeletedAt(LocalDateTime.now());
        classSectionRepository.save(s);
      } else {
        classSectionRepository.deleteById(id);
      }
    }, () -> {
      throw new AppException(ErrorCode.CLASS_SECTION_NOT_FOUND);
    });
  }

  @Override
  public ClassSectionResponse getClassSectionById(Long id) {
    return classSectionMapper.toClassSectionResponse(classSectionRepository.findById(id)
        .orElseThrow(() -> new AppException(ErrorCode.CLASS_SECTION_NOT_FOUND)));
  }

  @Override
  public Page<ClassSectionResponse> getAllClassSections(Pageable pageable) {
    return classSectionRepository.findAll(pageable).map(classSectionMapper::toClassSectionResponse);
  }

  @Override
  public void recoverClassSectionById(Long id) {
    var c = classSectionRepository.findById(id)
        .orElseThrow(() -> new AppException(ErrorCode.CLASS_SECTION_RECOVER_FAILED));

    if (c.getDeletedAt() == null) {
      throw new AppException(ErrorCode.CLASS_SECTION_RECOVER_FAILED);
    }

    c.setDeletedAt(null);
    classSectionRepository.save(c);
  }

  @Override
  public Page<ClassSectionResponse> getAllClassSectionsByTeacherId(Long teacherId, Pageable pageable) {
    if (!teacherRepository.existsById(teacherId)) {
      throw new AppException(ErrorCode.TEACHER_NOT_FOUND);
    }

    return classSectionRepository.findByTeacherId(teacherId, pageable).map(classSectionMapper::toClassSectionResponse);
  }

}
