package com.vdt.student_management.tuition.repository;

import com.vdt.student_management.tuition.model.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Page<Payment> findByTuitionId(Long tuitionId, Pageable pageable);
}
