package com.cydeo.InventoryManagementRest.service.unit;

import com.cydeo.InventoryManagementRest.dto.CategoryDto;
import com.cydeo.InventoryManagementRest.dto.CompanyDto;
import com.cydeo.InventoryManagementRest.dto.UserDto;
import com.cydeo.InventoryManagementRest.entity.Category;
import com.cydeo.InventoryManagementRest.entity.Company;
import com.cydeo.InventoryManagementRest.exception.AccountingException;
import com.cydeo.InventoryManagementRest.mapper.MapperUtil;
import com.cydeo.InventoryManagementRest.repository.CategoryRepository;
import com.cydeo.InventoryManagementRest.service.ProductService;
import com.cydeo.InventoryManagementRest.service.SecurityService;
import com.cydeo.InventoryManagementRest.service.implementation.CategoryServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

    @Mock
    CategoryRepository repository;

    @Mock
    SecurityService securityService;

    @Mock
    MapperUtil mapperUtil;

    @InjectMocks
    CategoryServiceImpl service;

    @Test
    void findCategoryById() throws AccountingException {
        when(repository.findById(anyLong())).thenReturn(Optional.of(new Category()));
        when(mapperUtil.convert(any(Category.class), any(CategoryDto.class))).thenReturn(new CategoryDto());
        service.findCategoryById(anyLong());
        verify(repository).findById(anyLong());
    }

    @Test
    void getAllCategories() {
        CompanyDto company = new CompanyDto();
        UserDto user = new UserDto();
        user.setCompany(company);
        when(securityService.getLoggedInUser()).thenReturn(user);

        service.getAllCategories();
        verify(repository).findAllByCompany(mapperUtil.convert(user.getCompany(), new Company()));
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void isCategoryDescriptionExist() {
    }
}