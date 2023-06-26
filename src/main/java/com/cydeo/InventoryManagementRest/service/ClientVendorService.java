package com.cydeo.InventoryManagementRest.service;

import com.cydeo.InventoryManagementRest.dto.ClientVendorDto;
import com.cydeo.InventoryManagementRest.enums.ClientVendorType;
import com.cydeo.InventoryManagementRest.exception.AccountingException;

import java.util.List;

public interface ClientVendorService {

    ClientVendorDto findClientVendorById(Long id) throws AccountingException;
    List<ClientVendorDto> getAllClientVendors() throws Exception;
    List<ClientVendorDto> getAllClientVendors(ClientVendorType clientVendorType);
    ClientVendorDto create(ClientVendorDto clientVendorDto) throws Exception;
    ClientVendorDto update(Long id, ClientVendorDto clientVendorDto) throws ClassNotFoundException, CloneNotSupportedException, AccountingException;
    void delete(Long id) throws AccountingException;
    boolean companyNameExists(ClientVendorDto clientVendorDto);
}
