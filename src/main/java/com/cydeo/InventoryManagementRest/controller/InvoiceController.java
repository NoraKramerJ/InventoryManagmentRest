package com.cydeo.InventoryManagementRest.controller;

import com.cydeo.InventoryManagementRest.dto.InvoiceDto;
import com.cydeo.InventoryManagementRest.dto.InvoiceProductDto;
import com.cydeo.InventoryManagementRest.dto.ResponseWrapper;
import com.cydeo.InventoryManagementRest.enums.InvoiceType;
import com.cydeo.InventoryManagementRest.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;
    private final InvoiceProductService invoiceProductService;

    public InvoiceController(InvoiceService invoiceService, InvoiceProductService invoiceProductService) {
        this.invoiceService = invoiceService;
        this.invoiceProductService = invoiceProductService;
    }

    @GetMapping("/get/{type}")
    public ResponseEntity<ResponseWrapper> getInvoices(@PathVariable("type") String type) throws Exception {
        InvoiceType invoiceType = type.equalsIgnoreCase("sale") ? InvoiceType.SALE : InvoiceType.PURCHASE;
        var invoices = invoiceService.getAllInvoicesOfCompany(invoiceType);
        return ResponseEntity.ok(new ResponseWrapper("Invoices successfully retrieved",invoices, HttpStatus.OK));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseWrapper> getInvoiceById(@PathVariable("id") Long id) throws Exception {
       var invoice = invoiceService.findInvoiceById(id);
       return ResponseEntity.ok(new ResponseWrapper("Invoice successfully retrieved",invoice, HttpStatus.OK));
    }

    @PostMapping("/create/{type}")
    public ResponseEntity<ResponseWrapper> create(@RequestBody InvoiceDto invoiceDto, @PathVariable("type") String type) {
        InvoiceType invoiceType = type.equalsIgnoreCase("sale") ? InvoiceType.SALE : InvoiceType.PURCHASE;
        var invoice = invoiceService.save(invoiceDto, invoiceType);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseWrapper("Invoice successfully created",invoice, HttpStatus.CREATED));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseWrapper> update(@RequestBody InvoiceDto invoiceDto, @PathVariable("id") Long id ) {
        var invoice = invoiceService.update(id, invoiceDto);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper("Invoice successfully updated",invoice, HttpStatus.OK));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseWrapper> delete(@PathVariable("id") Long id){
        invoiceService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper("Invoice successfully deleted",HttpStatus.OK));
    }

    @GetMapping("/approve/{id}")
    public ResponseEntity<ResponseWrapper> approve(@PathVariable("id") Long id){
        var invoice = invoiceService.approve(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper("Invoice approved",invoice, HttpStatus.OK));
    }

    @GetMapping("/invoice-products/{invoiceId}")
    public ResponseEntity<ResponseWrapper> getInvoiceProducts(@PathVariable("invoiceId") Long invoiceId) {
        var iProducts = invoiceProductService.getInvoiceProductsOfInvoice(invoiceId);
        return ResponseEntity.ok(new ResponseWrapper("Invoices successfully retrieved",iProducts, HttpStatus.OK));
    }
    @PostMapping("/invoice-products/{id}")
    public ResponseEntity<ResponseWrapper> addInvoiceProduct(@PathVariable("id") Long id, @RequestBody InvoiceProductDto invoiceProductDto) {
        InvoiceProductDto iProduct = invoiceProductService.save(id, invoiceProductDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseWrapper("InvoiceProduct successfully created",iProduct, HttpStatus.CREATED));
    }

    @GetMapping("/lastthree")
    public ResponseEntity<ResponseWrapper> getLastTree(){
        List<InvoiceDto> lastTreeInvoice = invoiceService.getLastThreeInvoices();
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseWrapper("LastThree Invoices successfully created",lastTreeInvoice, HttpStatus.CREATED));
    }

//    @GetMapping("/removeInvoiceProduct/{invoiceId}/{invoiceProductId}")
//    public String removeInvoiceProduct(@PathVariable("invoiceId") Long invoiceId,
//                                       @PathVariable("invoiceProductId") Long invoiceProductId) {
//        invoiceProductService.delete(invoiceProductId);
//        return "redirect:/purchaseInvoices/update/" + invoiceId;
//    }


}
