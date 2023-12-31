package com.cydeo.InventoryManagementRest.service.implementation;

import com.cydeo.InventoryManagementRest.dto.CategoryDto;
import com.cydeo.InventoryManagementRest.entity.Category;
import com.cydeo.InventoryManagementRest.exception.AccountingException;
import com.cydeo.InventoryManagementRest.mapper.MapperUtil;
import com.cydeo.InventoryManagementRest.repository.CategoryRepository;
import com.cydeo.InventoryManagementRest.service.CategoryService;
import com.cydeo.InventoryManagementRest.service.ProductService;
import com.cydeo.InventoryManagementRest.service.SecurityService;
import com.cydeo.InventoryManagementRest.service.common.CommonService;
import com.stripe.exception.ApiException;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CategoryServiceImpl extends CommonService implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ProductService productService;

    public CategoryServiceImpl(SecurityService securityService, MapperUtil mapperUtil, CategoryRepository categoryRepository, ProductService productService) {
        super(securityService, mapperUtil);
        this.categoryRepository = categoryRepository;
        this.productService = productService;
    }

    @Override
    public CategoryDto findCategoryById(Long categoryId) throws AccountingException {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new AccountingException("Category not found"));
        CategoryDto dto = mapperUtil.convert(category, new CategoryDto());
        dto.setHasProduct(hasProduct(dto.getId()));
        return dto;
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepository
                .findAllByCompany(getCompany())
                .stream()
                .sorted(Comparator.comparing(Category::getDescription))
                .map(each -> mapperUtil.convert(each, new CategoryDto()))
                .peek(dto -> dto.setHasProduct(hasProduct(dto.getId())))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto create(CategoryDto categoryDto) throws Exception {
        Category category = mapperUtil.convert(categoryDto, new Category());
        category.setCompany(getCompany());
        return mapperUtil.convert(categoryRepository.save(category), new CategoryDto());
    }

    @Override
    public CategoryDto update(Long categoryId, CategoryDto categoryDto) throws AccountingException {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new AccountingException("Category not found"));
        category.setDescription(categoryDto.getDescription());
        return mapperUtil.convert(categoryRepository.save(category), new CategoryDto());
    }

    @Override
    public void delete(Long categoryId) throws AccountingException {
        if(hasProduct(categoryId)){
            throw new AccountingException("This category can not be deleted...");
        }
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new AccountingException("Category not found"));
        category.setIsDeleted(true);
        category.setDescription(category.getDescription() + "-" + category.getId());
        categoryRepository.save(category);
    }

    private boolean hasProduct(Long categoryId) {
        return productService.findAllProductsWithCategoryId(categoryId).size() > 0;
    }

    @Override
    public boolean isCategoryDescriptionExist(CategoryDto categoryDTO) {
        Category existingCategory = categoryRepository.findByDescriptionAndCompany(categoryDTO.getDescription(), getCompany());
        if (existingCategory == null) return false;
        return !existingCategory.getId().equals(categoryDTO.getId());
    }
}
