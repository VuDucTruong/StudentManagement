package com.vdt.student_management.teacher.service;

import com.vdt.student_management.common.enums.ErrorCode;
import com.vdt.student_management.common.exception.AppException;
import com.vdt.student_management.teacher.dto.TeacherDetailResponse;
import com.vdt.student_management.teacher.dto.TeacherResponse;
import com.vdt.student_management.teacher.mapper.TeacherMapper;
import com.vdt.student_management.teacher.model.Teacher;
import com.vdt.student_management.teacher.repository.TeacherRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

  TeacherRepository teacherRepository;
  TeacherMapper teacherMapper;


  @Override
  public void upsertTeacher(Teacher teacher) {
    teacher.setUpdatedAt(LocalDateTime.now());
    teacherRepository.save(teacher);
  }

  @Override
  public void deleteTeacher(Long id) {
    teacherRepository.findById(id).ifPresentOrElse(teacher -> {
      if (teacher.getDeletedAt() == null) {
        teacher.setDeletedAt(LocalDateTime.now());
      } else {
        teacherRepository.deleteById(id);
      }
    }, () -> {
      throw new AppException(ErrorCode.RESOURCE_NOT_FOUND);
    });
  }

  @Override
  public List<TeacherResponse> getAllTeachers() {
    return teacherRepository.findAll().stream().map(teacherMapper::toTeacherResponse).toList();
  }

  @Override
  public TeacherDetailResponse getTeacherById(Long id) {
    return teacherMapper.toTeacherDetailResponse(teacherRepository.findById(id)
        .orElseThrow(() -> new AppException(ErrorCode.RESOURCE_NOT_FOUND)));
  }


  @Override
  @Transactional
  public void recoverTeacher(Long id) {
    int result = teacherRepository.recoverDeletedTeacherById(id);

    if (result == 0) throw new AppException(ErrorCode.TEACHER_RECOVER_FAILED);
  }
}
