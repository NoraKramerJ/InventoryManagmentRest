package com.cydeo.InventoryManagementRest.controller;

import com.cydeo.InventoryManagementRest.dto.InvoiceDto;
import com.cydeo.InventoryManagementRest.dto.ResponseWrapper;
import com.cydeo.InventoryManagementRest.entity.Invoice;
import com.cydeo.InventoryManagementRest.enums.InvoiceStatus;
import com.cydeo.InventoryManagementRest.enums.InvoiceType;
import com.cydeo.InventoryManagementRest.service.CompanyService;
import com.cydeo.InventoryManagementRest.service.DashboardService;
import com.cydeo.InventoryManagementRest.service.InvoiceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class DashboardController {
    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/summary")
    public ResponseEntity<ResponseWrapper> getSummaryNumbers() throws Exception {
        var numbers = dashboardService.getSummaryNumbers();
        return ResponseEntity.ok(new ResponseWrapper("Summary numbers retrieved",numbers, HttpStatus.OK));
    }

    @GetMapping("/exchange")
    public ResponseEntity<ResponseWrapper> getExchangeRates() throws Exception {
        var currency = dashboardService.getExchangeRates();
        return ResponseEntity.ok(new ResponseWrapper("Summary numbers retrieved",currency, HttpStatus.OK));
    }

}
