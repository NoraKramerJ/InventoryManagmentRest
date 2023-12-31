package com.cydeo.InventoryManagementRest.repository;

import com.cydeo.InventoryManagementRest.entity.Company;
import com.cydeo.InventoryManagementRest.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Repository
@CrossOrigin(origins = "http://localhost:4200/")
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByCategoryCompany(Company company);

    List<Product> findByCategoryId(Long categoryId);

    boolean existsByName(String name);

    Product findByNameAndCategoryCompany(String name, Company actualCompany);
}
