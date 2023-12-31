package com.cydeo.InventoryManagementRest.service.implementation;

import com.cydeo.InventoryManagementRest.dto.RoleDto;
import com.cydeo.InventoryManagementRest.exception.AccountingException;
import com.cydeo.InventoryManagementRest.mapper.MapperUtil;
import com.cydeo.InventoryManagementRest.repository.RoleRepository;
import com.cydeo.InventoryManagementRest.service.RoleService;
import com.cydeo.InventoryManagementRest.service.SecurityService;
import com.cydeo.InventoryManagementRest.service.common.CommonService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl extends CommonService implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(SecurityService securityService, MapperUtil mapperUtil, RoleRepository roleRepository) {
        super(securityService, mapperUtil);
        this.roleRepository = roleRepository;
    }

    @Override
    public RoleDto findRoleById(Long id) {
        return mapperUtil.convert(roleRepository.findRoleById(id), new RoleDto());
    }

    @Override
    public List<RoleDto> getFilteredRolesForCurrentUser() {
        if (getCurrentUser().getRole().getDescription().equals("Root User")) {
            List<RoleDto> list = new ArrayList<>();
            list.add(mapperUtil.convert(roleRepository.findByDescription("Admin"), new RoleDto()));
            return list;
        } else {
            return roleRepository.findAll()
                    .stream()
                    .filter(role -> !role.getDescription().equals("Root User"))
                    .map(role -> mapperUtil.convert(role, new RoleDto()))
                    .collect(Collectors.toList());
        }
    }

}
