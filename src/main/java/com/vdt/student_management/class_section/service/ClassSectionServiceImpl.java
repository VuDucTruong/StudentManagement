package com.vdt.student_management.class_section.service;

import com.vdt.student_management.class_section.dto.ClassSectionResponse;
import com.vdt.student_management.class_section.mapper.ClassSectionMapper;
import com.vdt.student_management.class_section.model.ClassSection;
import com.vdt.student_management.class_section.repository.ClassSectionRepository;
import com.vdt.student_management.common.enums.ErrorCode;
import com.vdt.student_management.common.exception.AppException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ClassSectionServiceImpl implements ClassSectionService {

  ClassSectionRepository classSectionRepository;
  ClassSectionMapper classSectionMapper;

  @Override
  public ClassSectionResponse upsertClassSection(ClassSection classSection) {
    classSection.setUpdatedAt(LocalDateTime.now());
    if (classSection.getId() != null) {
      classSectionRepository.findById(classSection.getId()).ifPresentOrElse(s -> {
        if (s.getDeletedAt() != null) {
          throw new AppException(ErrorCode.CANT_UPDATE_DELETED_RESOURCE);
        }
      }, () -> {
        throw new AppException(ErrorCode.RESOURCE_NOT_FOUND);
      });
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
      throw new AppException(ErrorCode.RESOURCE_NOT_FOUND);
    });
  }

  @Override
  public ClassSectionResponse getClassSectionById(Long id) {
    return classSectionMapper.toClassSectionResponse(classSectionRepository.findById(id)
        .orElseThrow(() -> new AppException(ErrorCode.RESOURCE_NOT_FOUND)));
  }

  @Override
  public List<ClassSectionResponse> getAllClassSections() {
    return classSectionRepository.findAll().stream().map(classSectionMapper::toClassSectionResponse).toList();
  }

  @Override
  public void recoverClassSectionById(Long id) {
    var c = classSectionRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.CLASS_SECTION_RECOVER_FAILED));

    if(c.getDeletedAt() == null){
      throw new AppException(ErrorCode.CLASS_SECTION_RECOVER_FAILED);
    }

    c.setDeletedAt(null);
    classSectionRepository.save(c);
  }
}
