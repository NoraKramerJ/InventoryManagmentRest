package com.cydeo.InventoryManagementRest.repository;

import java.util.List;

import com.cydeo.InventoryManagementRest.entity.Company;
import com.cydeo.InventoryManagementRest.entity.Product;
import com.cydeo.InventoryManagementRest.enums.InvoiceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cydeo.InventoryManagementRest.entity.Invoice;
import com.cydeo.InventoryManagementRest.entity.InvoiceProduct;
import com.cydeo.InventoryManagementRest.enums.InvoiceType;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin(origins = "http://localhost:4200/")
public interface InvoiceProductRepository extends JpaRepository<InvoiceProduct, Long> {

    InvoiceProduct findInvoiceProductById(Long id);
    List<InvoiceProduct> findAllByInvoice(Invoice invoice);
    List<InvoiceProduct> findAllByInvoice_Id(Long id);
    List<InvoiceProduct> findAllByInvoice_InvoiceTypeAndInvoice_Company(InvoiceType invoiceType, Company company);
    List<InvoiceProduct> findAllByInvoice_InvoiceStatusAndInvoice_Company(InvoiceStatus invoiceStatus, Company company);
    List<InvoiceProduct> findInvoiceProductsByInvoiceInvoiceTypeAndProductAndRemainingQuantityNotOrderByIdAsc(InvoiceType invoiceType, Product product, Integer remainingQuantity);
    List<InvoiceProduct> findAllInvoiceProductByProductId(Long id);
}
