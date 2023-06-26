package com.cydeo.InventoryManagementRest.repository;

import com.cydeo.InventoryManagementRest.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDate;
import java.util.List;

@Repository
@CrossOrigin(origins = "http://localhost:4200/")
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findAllByYearBetweenAndCompanyId(LocalDate start, LocalDate end, Long id);
}