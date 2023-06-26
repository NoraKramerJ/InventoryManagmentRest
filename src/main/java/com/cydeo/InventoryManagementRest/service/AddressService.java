package com.cydeo.InventoryManagementRest.service;

import com.cydeo.InventoryManagementRest.dto.AddressDto;
import com.cydeo.InventoryManagementRest.dto.addressApi.Country;
import com.cydeo.InventoryManagementRest.dto.addressApi.State;

import java.util.List;

public interface AddressService {

    List<String> getCountryList();

    List<State> getStateList(String country);

    List<State> getCity(String state);


}
