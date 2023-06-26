package com.cydeo.InventoryManagementRest.service.implementation;

import com.cydeo.InventoryManagementRest.dto.InvoiceProductDto;
import com.cydeo.InventoryManagementRest.entity.InvoiceProduct;
import com.cydeo.InventoryManagementRest.enums.InvoiceStatus;
import com.cydeo.InventoryManagementRest.enums.InvoiceType;
import com.cydeo.InventoryManagementRest.mapper.MapperUtil;
import com.cydeo.InventoryManagementRest.repository.InvoiceProductRepository;
import com.cydeo.InventoryManagementRest.service.ReportingService;
import com.cydeo.InventoryManagementRest.service.SecurityService;
import com.cydeo.InventoryManagementRest.service.common.CommonService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service
public class ReportingServiceImpl extends CommonService implements ReportingService {

    private final InvoiceProductRepository invoiceProductRepository;

    public ReportingServiceImpl(SecurityService securityService, MapperUtil mapperUtil, InvoiceProductRepository invoiceProductRepository) {
        super(securityService, mapperUtil);
        this.invoiceProductRepository = invoiceProductRepository;
    }

    @Override
    public List<InvoiceProductDto> getStockData() {
        return invoiceProductRepository.findAllByInvoice_InvoiceStatusAndInvoice_Company(InvoiceStatus.APPROVED, getCompany())
                .stream()
                .sorted(Comparator.comparing(InvoiceProduct::getId).reversed())
                .map(each -> mapperUtil.convert(each, new InvoiceProductDto()))
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, BigDecimal> getMonthlyProfitLossDataMap() {
        Map<String, BigDecimal> profitLossDataMap = new TreeMap<>();
        List<InvoiceProduct> salesInvoiceProducts = invoiceProductRepository.findAllByInvoice_InvoiceTypeAndInvoice_Company(InvoiceType.SALE, getCompany());
        for (InvoiceProduct invoiceProduct : salesInvoiceProducts) {
            int year = invoiceProduct.getInvoice().getDate().getYear();
            String month = invoiceProduct.getInvoice().getDate().getMonth().toString();
            BigDecimal profitLoss = invoiceProduct.getProfitLoss();
            String timeWindow = year + " " + month;
            profitLossDataMap.put(timeWindow, profitLossDataMap.getOrDefault(timeWindow, BigDecimal.ZERO).add(profitLoss));
        }
        return profitLossDataMap;
    }

}
