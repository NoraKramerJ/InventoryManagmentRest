package com.cydeo.InventoryManagementRest.repository;

import com.cydeo.InventoryManagementRest.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin(origins = "http://localhost:4200/")
public interface AddressRepository extends JpaRepository<Address, Long> {
}
