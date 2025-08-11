package com.vdt.student_management.academic.service.impl;

import com.vdt.student_management.academic.dto.request.AddTeacherRequest;
import com.vdt.student_management.academic.dto.response.TeacherDetailResponse;
import com.vdt.student_management.academic.dto.response.TeacherResponse;
import com.vdt.student_management.academic.mapper.TeacherMapper;
import com.vdt.student_management.academic.model.Teacher;
import com.vdt.student_management.academic.repository.TeacherRepository;
import com.vdt.student_management.academic.service.TeacherService;
import com.vdt.student_management.common.enums.ErrorCode;
import com.vdt.student_management.common.exception.AppException;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    TeacherRepository teacherRepository;
    TeacherMapper teacherMapper;

    @Override
    public TeacherDetailResponse upsertTeacher(Long id, AddTeacherRequest request) {
        var teacher = teacherMapper.toTeacher(request);

        // if update
        if (id != null) {
            teacher.setId(id);
            teacherRepository
                    .findById(teacher.getId())
                    .ifPresentOrElse(
                            teacher1 -> {
                                if (teacher1.getDeletedAt() != null) {
                                    throw new AppException(ErrorCode.CANT_UPDATE_DELETED_RESOURCE);
                                }
                            },
                            () -> {
                                throw new AppException(ErrorCode.TEACHER_NOT_FOUND);
                            });
        }
        return teacherMapper.toTeacherDetailResponse(teacherRepository.save(teacher));
    }

    @Override
    public void deleteTeacher(Long id) {
        teacherRepository.findById(id).ifPresentOrElse(this::handleTeacherDeletion, () -> {
            throw new AppException(ErrorCode.TEACHER_NOT_FOUND);
        });
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
    public Page<TeacherResponse> getAllTeachers(Pageable pageable) {
        return teacherRepository.findAll(pageable).map(teacherMapper::toTeacherResponse);
    }

    @Override
    public TeacherDetailResponse getTeacherById(Long id) {
        return teacherMapper.toTeacherDetailResponse(
                teacherRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.TEACHER_NOT_FOUND)));
    }

    @Override
    @Transactional
    public void recoverTeacher(Long id) {
        int result = teacherRepository.recoverDeletedTeacherById(id);

        if (result == 0) {
            throw new AppException(ErrorCode.TEACHER_RECOVER_FAILED);
        }
    }
}
