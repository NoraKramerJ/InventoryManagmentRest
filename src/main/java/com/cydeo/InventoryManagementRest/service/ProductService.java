package com.cydeo.InventoryManagementRest.service;

import com.cydeo.InventoryManagementRest.dto.ProductDto;

import java.util.List;

public interface ProductService {

    ProductDto findProductById(Long productId);

    List<ProductDto> getAllProducts();

    ProductDto save(ProductDto productDto);

    ProductDto update(Long productId, ProductDto productDto);

    void delete(Long productId);

    List<ProductDto> findAllProductsWithCategoryId(Long categoryId);

    boolean isProductNameExist(ProductDto productDto);
    boolean checkProductQuantity(Long productId);

}
