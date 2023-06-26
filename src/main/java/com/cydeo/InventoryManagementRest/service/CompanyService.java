package com.cydeo.InventoryManagementRest.service;

import com.cydeo.InventoryManagementRest.dto.CompanyDto;
import com.cydeo.InventoryManagementRest.exception.AccountingException;

import java.util.List;

public interface CompanyService {

    CompanyDto findCompanyById(Long id);
    CompanyDto getCompanyByLoggedInUser();
    List<CompanyDto> getAllCompanies();
    CompanyDto create(CompanyDto companyDto);
    CompanyDto update(Long companyId, CompanyDto companyDto) throws CloneNotSupportedException;
    void activate(Long companyId);
    void deactivate(Long companyId);
    boolean isTitleExist(String title);

}
