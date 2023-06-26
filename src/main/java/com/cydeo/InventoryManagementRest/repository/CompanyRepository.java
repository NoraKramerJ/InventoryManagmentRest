package com.cydeo.InventoryManagementRest.repository;

import com.cydeo.InventoryManagementRest.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin(origins = "http://localhost:4200/")
public interface CompanyRepository extends JpaRepository<Company, Long> {

    Company findCompanyByTitle(String title);

    boolean existsByTitle(String title);

}
