package com.cydeo.InventoryManagementRest.controller;

import com.cydeo.InventoryManagementRest.dto.ResponseWrapper;
import com.cydeo.InventoryManagementRest.dto.RoleDto;
import com.cydeo.InventoryManagementRest.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<ResponseWrapper> getUsers() {
        List<RoleDto> users = roleService.getFilteredRolesForCurrentUser();
        return ResponseEntity.ok(new ResponseWrapper("Roles successfully retrieved",users, HttpStatus.OK));
    }
}
