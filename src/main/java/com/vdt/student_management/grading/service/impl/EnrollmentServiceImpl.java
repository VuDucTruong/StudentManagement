package com.vdt.student_management.grading.service.impl;

import com.vdt.student_management.academic.model.Prerequisite;
import com.vdt.student_management.academic.repository.ClassSectionRepository;
import com.vdt.student_management.academic.repository.PrerequisiteRepository;
import com.vdt.student_management.academic.repository.StudentRepository;
import com.vdt.student_management.common.enums.ErrorCode;
import com.vdt.student_management.common.exception.AppException;
import com.vdt.student_management.grading.dto.request.AddEnrollmentRequest;
import com.vdt.student_management.grading.dto.response.EnrollmentResponse;
import com.vdt.student_management.grading.enums.EnrollmentStatus;
import com.vdt.student_management.grading.mapper.EnrollmentMapper;
import com.vdt.student_management.grading.model.Enrollment;
import com.vdt.student_management.grading.model.Score;
import com.vdt.student_management.grading.repository.EnrollmentRepository;
import com.vdt.student_management.grading.repository.ScoreRepository;
import com.vdt.student_management.grading.service.EnrollmentService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {

    EnrollmentRepository enrollmentRepository;
    StudentRepository studentRepository;
    ClassSectionRepository classSectionRepository;
    PrerequisiteRepository prerequisiteRepository;
    ScoreRepository scoreRepository;
    EnrollmentMapper enrollmentMapper;

    @Override
    public EnrollmentResponse addEnrollment(AddEnrollmentRequest request) {

        var student = studentRepository
                .findById(request.studentId())
                .orElseThrow(() -> new AppException(ErrorCode.STUDENT_NOT_FOUND));
        var classSection = classSectionRepository
                .findById(request.classSectionId())
                .orElseThrow(() -> new AppException(ErrorCode.CLASS_SECTION_NOT_FOUND));
        var prerequisites = prerequisiteRepository.findBySubjectId(
                classSection.getSubject().getId()); // get all precondition subject

        if (!prerequisites.isEmpty()) {
            List<Enrollment> requiredEnrollments = enrollmentRepository.findAllByStudentId(student.getId());
            Set<Long> requiredSubjects = requiredEnrollments.stream()
                    .map(e -> e.getClassSection().getSubject().getId())
                    .collect(Collectors.toSet());
            for (Prerequisite prerequisite : prerequisites) {
                Long prerequisiteId = prerequisite.getPrerequisiteSubject().getId();
                if (!requiredSubjects.contains(prerequisiteId)) {
                    throw new AppException(ErrorCode.PRECONDITIONS_NOT_MEET);
                }
            }
        }

        var enrollment = Enrollment.builder()
                .student(student)
                .classSection(classSection)
                .status(EnrollmentStatus.STUDYING)
                .build();
        var score = new Score();
        score.setEnrollment(enrollment);
        scoreRepository.save(score);

        return enrollmentMapper.toEnrollmentResponse(enrollmentRepository.save(enrollment));
    }

    @Override
    public void deleteEnrollment(Long enrollmentId) {
        var enrollment = enrollmentRepository
                .findById(enrollmentId)
                .orElseThrow(() -> new AppException(ErrorCode.ENROLLMENT_NOT_FOUND));
        if (enrollment.getDeletedAt() == null) {
            enrollment.setDeletedAt(LocalDateTime.now());
            enrollment.setStatus(EnrollmentStatus.CANCELLED);
            enrollmentRepository.save(enrollment);
        } else {
            enrollmentRepository.deleteById(enrollmentId);
        }
    }

    @Override
    public Page<EnrollmentResponse> getStudentEnrollments(Long studentId, Pageable pageable) {
        return enrollmentRepository.findByStudentId(studentId, pageable).map(enrollmentMapper::toEnrollmentResponse);
    }
}
