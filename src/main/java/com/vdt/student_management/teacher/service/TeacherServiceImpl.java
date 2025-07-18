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
  public TeacherDetailResponse upsertTeacher(Teacher teacher) {
    teacher.setUpdatedAt(LocalDateTime.now());

    // if update
    if(teacher.getId() != null) {
      teacherRepository.findById(teacher.getId()).ifPresentOrElse(teacher1 -> {
        if(teacher1.getDeletedAt() != null) {
          throw new AppException(ErrorCode.CANT_UPDATE_DELETED_RESOURCE);
        }
      }, () -> {
        throw new AppException(ErrorCode.RESOURCE_NOT_FOUND);
      });
    }
    return teacherMapper.toTeacherDetailResponse(teacherRepository.save(teacher));
  }

  @Override
  public void deleteTeacher(Long id) {
    teacherRepository.findById(id)
        .ifPresentOrElse(this::handleTeacherDeletion,
            () -> { throw new AppException(ErrorCode.RESOURCE_NOT_FOUND); });
  }

  private void handleTeacherDeletion(Teacher teacher) {
    if (teacher.getDeletedAt() == null) {
      teacher.setDeletedAt(LocalDateTime.now());
      teacherRepository.save(teacher);
    } else {
      teacherRepository.deleteById(teacher.getId());
    }
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
