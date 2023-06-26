package com.cydeo.InventoryManagementRest.repository;

import com.cydeo.InventoryManagementRest.entity.ClientVendor;
import com.cydeo.InventoryManagementRest.entity.Company;
import com.cydeo.InventoryManagementRest.entity.Invoice;
import com.cydeo.InventoryManagementRest.enums.InvoiceStatus;
import com.cydeo.InventoryManagementRest.enums.InvoiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Repository
@CrossOrigin(origins = "http://localhost:4200/")
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    Invoice findInvoiceById(Long id);
    List<Invoice> findInvoicesByCompanyAndInvoiceType(Company company, InvoiceType invoiceType);
    List<Invoice> findInvoicesByCompanyAndInvoiceStatus(Company company, InvoiceStatus invoiceStatus);
    List<Invoice> findInvoicesByCompanyAndInvoiceStatusOrderByDateDesc(Company company, InvoiceStatus invoiceStatus);
    Integer countAllByCompanyAndClientVendor_Id(Company company, Long clientVendorId);

}
