package com.cydeo.InventoryManagementRest.service;

import com.cydeo.InventoryManagementRest.dto.CategoryDto;
import com.cydeo.InventoryManagementRest.exception.AccountingException;


import java.util.List;

public interface CategoryService {

    CategoryDto findCategoryById(Long categoryId) throws AccountingException;

    List<CategoryDto> getAllCategories() throws Exception;

    CategoryDto create(CategoryDto categoryDto) throws Exception;

    CategoryDto update(Long categoryId, CategoryDto categoryDto) throws AccountingException;

    void delete(Long categoryId) throws AccountingException;
    boolean isCategoryDescriptionExist(CategoryDto categoryDto);

}
