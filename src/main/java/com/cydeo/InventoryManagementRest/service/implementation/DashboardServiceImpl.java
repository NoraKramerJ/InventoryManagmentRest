package com.cydeo.InventoryManagementRest.service.implementation;


import com.cydeo.InventoryManagementRest.dto.CurrencyApiResponse;
import com.cydeo.InventoryManagementRest.dto.CurrencyDto;
import com.cydeo.InventoryManagementRest.dto.InvoiceDto;
import com.cydeo.InventoryManagementRest.enums.InvoiceStatus;
import com.cydeo.InventoryManagementRest.enums.InvoiceType;
import com.cydeo.InventoryManagementRest.service.*;
import com.cydeo.InventoryManagementRest.service.feignClients.CurrencyExchangeClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class DashboardServiceImpl implements DashboardService {

    private final InvoiceService invoiceService;
    private final CurrencyExchangeClient client;

    public DashboardServiceImpl(InvoiceService invoiceService, CurrencyExchangeClient client) {
        this.invoiceService = invoiceService;
        this.client = client;
    }

    @Override
    public Map<String, BigDecimal> getSummaryNumbers() {
        Map<String, BigDecimal> summaryNumbersMap = new HashMap<>();
        BigDecimal totalCost = BigDecimal.ZERO;
        BigDecimal totalSales = BigDecimal.ZERO;
        BigDecimal profitLoss = BigDecimal.ZERO;
        List<InvoiceDto> allApprovedInvoicesOfCompany = invoiceService.getAllInvoicesByInvoiceStatus(InvoiceStatus.APPROVED);
        for (InvoiceDto invoice : allApprovedInvoicesOfCompany) {
            if (invoice.getInvoiceType() == InvoiceType.PURCHASE) {
                totalCost = totalCost.add(invoiceService.getTotalPriceOfInvoice(invoice));
            } else {
                totalSales = totalSales.add(invoiceService.getTotalPriceOfInvoice(invoice));
                profitLoss = profitLoss.add(invoiceService.getProfitLossOfInvoice(invoice));
            }
        }
        summaryNumbersMap.put("totalCost", totalCost);
        summaryNumbersMap.put("totalSales", totalSales);
        summaryNumbersMap.put("profitLoss", profitLoss);
        return summaryNumbersMap;
    }


    @Override
    public CurrencyDto getExchangeRates() {
        CurrencyApiResponse currency = client.getUsdBasedCurrencies();
        CurrencyDto currencyDto= CurrencyDto.builder()
                .euro(currency.getUsd().getEur())
                .britishPound(currency.getUsd().getGbp())
                .indianRupee(currency.getUsd().getInr())
                .japaneseYen(currency.getUsd().getJpy())
                .canadianDollar(currency.getUsd().getCad())
                .build();

        log.info("Currencies are fetched for the date : "+ currency.getDate());

        return currencyDto;

    }


}
