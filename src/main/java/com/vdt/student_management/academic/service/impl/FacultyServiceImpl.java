package com.vdt.student_management.academic.service.impl;

import com.vdt.student_management.academic.dto.request.AddFacultyRequest;
import com.vdt.student_management.academic.service.FacultyService;
import com.vdt.student_management.common.enums.ErrorCode;
import com.vdt.student_management.common.exception.AppException;
import com.vdt.student_management.academic.dto.response.FacultyDetailResponse;
import com.vdt.student_management.academic.dto.response.FacultyResponse;
import com.vdt.student_management.academic.mapper.FacultyMapper;
import com.vdt.student_management.academic.model.Faculty;
import com.vdt.student_management.academic.repository.FacultyRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor()
public class FacultyServiceImpl implements FacultyService {
  FacultyRepository facultyRepository;
  FacultyMapper facultyMapper;

  @Override
  public FacultyDetailResponse upsertFaculty(Long id, AddFacultyRequest request) {
    var faculty = facultyMapper.toFaculty(request);

    // if update
    if(id != null) {
      faculty.setId(id);
      facultyRepository.findById(faculty.getId()).ifPresentOrElse(faculty1 -> {
        if(faculty1.getDeletedAt() != null) {
          throw new AppException(ErrorCode.CANT_UPDATE_DELETED_RESOURCE);
        }
      }, ()->{
        throw new AppException(ErrorCode.FACULTY_NOT_FOUND);
      });
    }

    return facultyMapper.toFacultyDetailResponse(facultyRepository.save(faculty));
  }

  @Override
  public FacultyDetailResponse getFacultyById(Long id) {
    return facultyMapper.toFacultyDetailResponse(facultyRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.RESOURCE_NOT_FOUND)));
  }

  @Override
  public List<FacultyResponse> getAllFaculty() {
    return facultyRepository.findAll().stream().map(facultyMapper::toFacultyResponse).toList();
  }

  @Override
  public void deleteFacultyById(Long id) {
    var faculty = facultyRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.RESOURCE_NOT_FOUND));
    if(faculty.getDeletedAt() == null) {
      faculty.setDeletedAt(LocalDateTime.now());
    } else {
      facultyRepository.deleteById(id);
    }
  }

  @Override
  public void recoverFacultyById(Long id) {
    var faculty = facultyRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.RESOURCE_NOT_FOUND));
    faculty.setDeletedAt(null);
    facultyRepository.save(faculty);
  }
}
