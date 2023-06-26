package com.cydeo.InventoryManagementRest.service.implementation;

import com.cydeo.InventoryManagementRest.dto.CompanyDto;
import com.cydeo.InventoryManagementRest.entity.Company;
import com.cydeo.InventoryManagementRest.enums.CompanyStatus;
import com.cydeo.InventoryManagementRest.exception.AccountingException;
import com.cydeo.InventoryManagementRest.mapper.MapperUtil;
import com.cydeo.InventoryManagementRest.repository.CompanyRepository;
import com.cydeo.InventoryManagementRest.service.CompanyService;
import com.cydeo.InventoryManagementRest.service.SecurityService;
import com.cydeo.InventoryManagementRest.service.common.CommonService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl extends CommonService implements CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(@Lazy SecurityService securityService, MapperUtil mapperUtil, CompanyRepository companyRepository) {
        super(securityService, mapperUtil);
        this.companyRepository = companyRepository;
    }

    @Override
    public CompanyDto findCompanyById(Long id) {
        Company company = companyRepository.findById(id).get();
        return mapperUtil.convert(company, new CompanyDto());
    }

    @Override
    public List<CompanyDto> getAllCompanies() {
        boolean isRootUser = getCurrentUser().getRole().getDescription().equalsIgnoreCase("root user");

        return companyRepository.findAll()
                .stream()
                .filter(company -> company.getId() != 1)
                .filter(each -> isRootUser ? true : each.getTitle().equals(getCompany().getTitle()))
                .sorted(Comparator.comparing(Company::getCompanyStatus).thenComparing(Company::getTitle))
                .map(each -> mapperUtil.convert(each, new CompanyDto()))
                .collect(Collectors.toList());
    }

    @Override
    public CompanyDto create(CompanyDto companyDto) {
        companyDto.setCompanyStatus(CompanyStatus.PASSIVE);
        Company company = companyRepository.save(mapperUtil.convert(companyDto, new Company()));
        return mapperUtil.convert(company, new CompanyDto());
    }

    @Override
    public CompanyDto update(Long companyId, CompanyDto companyDto) {
        Company savedCompany = companyRepository.findById(companyId).get();
        companyDto.setId(companyId);
        companyDto.setCompanyStatus(savedCompany.getCompanyStatus());
        companyDto.getAddress().setId(savedCompany.getAddress().getId());
        Company updatedCompany = mapperUtil.convert(companyDto, new Company());
        return mapperUtil.convert(companyRepository.save(updatedCompany), new CompanyDto());
    }

    @Override
    public void activate(Long companyId) {
        Company company = companyRepository.findById(companyId).get();
        company.setCompanyStatus(CompanyStatus.ACTIVE);
        companyRepository.save(company);
        mapperUtil.convert(company, new CompanyDto());
    }

    @Override
    public void deactivate(Long companyId) {
        Company company = companyRepository.findById(companyId).get();
        company.setCompanyStatus(CompanyStatus.PASSIVE);
        companyRepository.save(company);
        mapperUtil.convert(company, new CompanyDto());
    }

    @Override
    public boolean isTitleExist(String title) {
        return companyRepository.existsByTitle(title);
    }

    @Override
    public CompanyDto getCompanyByLoggedInUser() {
        return mapperUtil.convert(getCompany(),new CompanyDto());
    }

}
