package com.cydeo.InventoryManagementRest.service;


import com.cydeo.InventoryManagementRest.dto.CurrencyDto;

import java.math.BigDecimal;
import java.util.Map;

public interface DashboardService {

    Map<String, BigDecimal> getSummaryNumbers() throws Exception;
    CurrencyDto getExchangeRates();
}
