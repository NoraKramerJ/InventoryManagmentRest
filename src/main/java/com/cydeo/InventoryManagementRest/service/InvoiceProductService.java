package com.cydeo.InventoryManagementRest.service;

import com.cydeo.InventoryManagementRest.dto.InvoiceProductDto;
import com.cydeo.InventoryManagementRest.entity.InvoiceProduct;
import com.cydeo.InventoryManagementRest.entity.Product;
import com.cydeo.InventoryManagementRest.enums.InvoiceType;

import java.util.List;

public interface InvoiceProductService {

    InvoiceProductDto findInvoiceProductById(long id);
    List<InvoiceProductDto> getInvoiceProductsOfInvoice(Long invoiceId);
    InvoiceProductDto save(Long invoiceId, InvoiceProductDto invoiceProductDto);
    void delete(Long invoiceProductId);
    void completeApprovalProcedures(Long invoiceId, InvoiceType type);
    boolean checkProductQuantity(InvoiceProductDto salesInvoiceProduct);
    List<InvoiceProduct> findNotSoldProducts(Product product);
    List<InvoiceProduct> findAllInvoiceProductsByProductId(Long id);
}
