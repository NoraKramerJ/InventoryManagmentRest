package com.cydeo.InventoryManagementRest.service;

import com.cydeo.accountingsimplified.dto.UserDto;
import com.cydeo.accountingsimplified.exception.AccountingException;

public interface SecurityService  {

    UserDto getLoggedInUser();

}
