package com.cydeo.InventoryManagementRest.enums;

public enum InvoiceType {

    PURCHASE("Purchase"),
    SALE("Sale");

    private String value;

    InvoiceType(String value) {
        this.value = value;
    }

}
