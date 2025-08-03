package com.vdt.student_management.academic.service.impl;

import com.vdt.student_management.academic.dto.request.AddMajorRequest;
import com.vdt.student_management.academic.dto.response.MajorResponse;
import com.vdt.student_management.academic.mapper.MajorMapper;
import com.vdt.student_management.academic.repository.FacultyRepository;
import com.vdt.student_management.academic.repository.MajorRepository;
import com.vdt.student_management.academic.service.MajorService;
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
public class MajorServiceImpl implements MajorService {

  MajorRepository majorRepository;
  FacultyRepository facultyRepository;
  MajorMapper majorMapper;

  @Override
  public MajorResponse upsertMajor(Long id, AddMajorRequest request) {
    var major = majorMapper.toMajor(request);


    var faculty = facultyRepository.findById(request.facultyId()).orElse(null);

    if(faculty == null || faculty.getDeletedAt() != null) throw new AppException(ErrorCode.FACULTY_NOT_FOUND);
    major.setFaculty(faculty);
    if (id != null) {
      major.setId(id);
      majorRepository.findById(id).ifPresentOrElse(m -> {
        if (m.getDeletedAt() != null) {
          throw new AppException(ErrorCode.MAJOR_NOT_FOUND);
        }
      }, () -> {
        throw new AppException(ErrorCode.MAJOR_NOT_FOUND);
      });
    }

    return majorMapper.toMajorResponse(majorRepository.save(major));
  }

  @Override
  public MajorResponse getMajor(Long id) {
    return majorMapper.toMajorResponse(majorRepository.findById(id)
        .orElseThrow(() -> new AppException(ErrorCode.MAJOR_NOT_FOUND)));
  }

  @Override
  public Page<MajorResponse> getAllMajor(Pageable pageable) {
    return majorRepository.findAll(pageable).map(majorMapper::toMajorResponse);
  }

  @Override
  public void deleteMajor(Long id) {
    majorRepository.findById(id).ifPresentOrElse(m -> {
      if (m.getDeletedAt() != null) {
        majorRepository.deleteById(m.getId());
      } else {
        m.setDeletedAt(LocalDateTime.now());
        majorRepository.save(m);
      }
    }, () -> {
      throw new AppException(ErrorCode.MAJOR_NOT_FOUND);
    });
  }

  @Override
  public void recoverMajor(Long id) {
    majorRepository.findById(id).ifPresentOrElse(m -> {
      if (m.getDeletedAt() != null) {
        m.setDeletedAt(LocalDateTime.now());
        majorRepository.save(m);
      } else {
        throw new AppException(ErrorCode.MAJOR_RECOVER_FAILED);
      }
    }, () -> {
      throw new AppException(ErrorCode.MAJOR_NOT_FOUND);
    });
  }
}
