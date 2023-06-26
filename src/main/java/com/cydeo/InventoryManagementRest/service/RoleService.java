package com.cydeo.InventoryManagementRest.service;

import com.cydeo.InventoryManagementRest.dto.RoleDto;
import com.cydeo.InventoryManagementRest.exception.AccountingException;

import java.util.List;

public interface RoleService {

    RoleDto findRoleById(Long id);
    List<RoleDto> getFilteredRolesForCurrentUser();

}
