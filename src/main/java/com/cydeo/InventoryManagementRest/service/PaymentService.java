package com.cydeo.InventoryManagementRest.service;

import com.cydeo.InventoryManagementRest.dto.PaymentDto;

import java.util.List;

public interface PaymentService {

    List<PaymentDto> getAllPaymentsByYear(int year);

    void createPaymentsIfNotExist(int year);

    PaymentDto getPaymentById(Long id);

    PaymentDto updatePayment(Long id);
}
