package com.cydeo.InventoryManagementRest.service.implementation;

import com.cydeo.InventoryManagementRest.dto.UserDto;
import com.cydeo.InventoryManagementRest.exception.AccountingException;
import com.cydeo.InventoryManagementRest.service.SecurityService;
import com.cydeo.InventoryManagementRest.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {

    private final UserService userService;

    public SecurityServiceImpl(UserService userService) {
        this.userService = userService;
    }


    @Override
    public UserDto getLoggedInUser() {
        var currentUsername = "manager@greentech.com";
        return userService.findByUsername(currentUsername);
    }

}
