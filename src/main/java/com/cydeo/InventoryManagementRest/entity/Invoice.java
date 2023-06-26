package com.cydeo.InventoryManagementRest.entity;

import com.cydeo.InventoryManagementRest.dto.InvoiceProductDto;
import com.cydeo.InventoryManagementRest.enums.InvoiceStatus;
import com.cydeo.InventoryManagementRest.enums.InvoiceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "invoices")
@Where(clause = "is_deleted=false")
public class Invoice extends BaseEntity{

    private String invoiceNo;

    @Enumerated(EnumType.STRING)
    private InvoiceStatus invoiceStatus;

    @Enumerated(EnumType.STRING)
    private InvoiceType invoiceType;

    private LocalDate date;

    @ManyToOne
    private Company company;

    @ManyToOne
    private ClientVendor clientVendor;



}
