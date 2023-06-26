package com.cydeo.InventoryManagementRest.service;

import com.cydeo.InventoryManagementRest.dto.UserDto;
import com.cydeo.InventoryManagementRest.exception.AccountingException;

import java.util.List;

public interface UserService {

    UserDto findUserById(Long id) throws AccountingException;
    UserDto findByUsername(String username);
    List<UserDto> getFilteredUsers() throws Exception;
    UserDto save(UserDto userDto);
    UserDto update(UserDto userDto);
    void delete(Long id);
    Boolean emailExist(UserDto userDto) throws AccountingException;

}
