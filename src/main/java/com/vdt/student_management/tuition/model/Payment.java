package com.vdt.student_management.tuition.model;

import com.vdt.student_management.common.model.BaseModel;
import com.vdt.student_management.tuition.enums.PaymentMethod;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Payment extends BaseModel {

    Long amountPaid;
    LocalDate paymentDate;
    PaymentMethod paymentMethod;
    String referenceNumber;
    String note;

    @ManyToOne
    @JoinColumn(name = "tuition_id", referencedColumnName = "id")
    Tuition tuition;
}
