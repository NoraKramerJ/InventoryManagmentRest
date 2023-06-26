package com.cydeo.InventoryManagementRest.service.common;

import com.cydeo.InventoryManagementRest.entity.Company;
import com.cydeo.InventoryManagementRest.entity.User;
import com.cydeo.InventoryManagementRest.exception.AccountingException;
import com.cydeo.InventoryManagementRest.mapper.MapperUtil;
import com.cydeo.InventoryManagementRest.service.SecurityService;
import org.springframework.stereotype.Service;

@Service
public class CommonService {

    protected final SecurityService securityService;
    protected final MapperUtil mapperUtil;

    public CommonService(SecurityService securityService, MapperUtil mapperUtil) {
        this.securityService = securityService;
        this.mapperUtil = mapperUtil;
    }

    protected Company getCompany() {
        return mapperUtil.convert(securityService.getLoggedInUser().getCompany(), new Company());
    }

    protected User getCurrentUser(){
        return mapperUtil.convert(securityService.getLoggedInUser(), new User());
    }




}
