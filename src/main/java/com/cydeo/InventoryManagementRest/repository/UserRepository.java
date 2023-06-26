package com.cydeo.InventoryManagementRest.repository;

import com.cydeo.InventoryManagementRest.entity.Company;
import com.cydeo.InventoryManagementRest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200/")
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserById(Long id);
    User findByUsername(String username);
    List<User> findAllByRole_Description(String roleDescription);
    List<User> findAllByCompany(Company company);
    Integer countAllByCompanyAndRole_Description(Company company, String role);
}
