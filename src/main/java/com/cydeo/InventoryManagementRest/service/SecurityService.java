package com.cydeo.InventoryManagementRest.service;

import com.cydeo.InventoryManagementRest.dto.UserDto;
import com.cydeo.InventoryManagementRest.exception.AccountingException;

public interface SecurityService  {

    UserDto getLoggedInUser();

}
