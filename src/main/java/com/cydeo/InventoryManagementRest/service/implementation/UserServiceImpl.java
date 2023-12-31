package com.cydeo.InventoryManagementRest.service.implementation;

import com.cydeo.InventoryManagementRest.dto.UserDto;
import com.cydeo.InventoryManagementRest.entity.Company;
import com.cydeo.InventoryManagementRest.entity.User;
import com.cydeo.InventoryManagementRest.exception.AccountingException;
import com.cydeo.InventoryManagementRest.exception.UserDoesNotExistException;
import com.cydeo.InventoryManagementRest.mapper.MapperUtil;
import com.cydeo.InventoryManagementRest.repository.UserRepository;
import com.cydeo.InventoryManagementRest.service.SecurityService;
import com.cydeo.InventoryManagementRest.service.UserService;
import com.cydeo.InventoryManagementRest.service.common.CommonService;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final SecurityService securityService;
    private final MapperUtil mapperUtil;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, @Lazy SecurityService securityService1, MapperUtil mapperUtil1) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.securityService = securityService1;
        this.mapperUtil = mapperUtil1;
    }

    @Override
    public UserDto findUserById(Long id) throws AccountingException {
        User user = userRepository.findUserById(id);
        if (user == null){
            throw new AccountingException("There is no user with given id");
        }
        UserDto dto = mapperUtil.convert(user, new UserDto());
        dto.setIsOnlyAdmin(checkIfOnlyAdminForCompany(dto));
        return dto;
    }

    @Override
    public UserDto findByUsername(String username){
        User user = userRepository.findByUsername(username);
        return mapperUtil.convert(user, new UserDto());
    }

    @Override
    public List<UserDto> getFilteredUsers() {
        List<User> userList;
        if (securityService.getLoggedInUser().getRole().getDescription().equals("Root User")) {
            userList = userRepository.findAllByRole_Description("Admin");
        } else {
            userList = userRepository.findAllByCompany(mapperUtil.convert(securityService.getLoggedInUser().getCompany(), new Company()));
        }
        return userList.stream()
                .sorted(Comparator.comparing((User u) -> u.getCompany().getTitle()).thenComparing(u -> u.getRole().getDescription()))
                .map(entity -> mapperUtil.convert(entity, new UserDto()))
                .peek(dto -> dto.setIsOnlyAdmin(this.checkIfOnlyAdminForCompany(dto)))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto save(UserDto userDto) {
        User user = mapperUtil.convert(userDto, new User());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        userRepository.save(user);
        return mapperUtil.convert(user, userDto);
    }

    @Override
    public UserDto update(UserDto userDto) {
        User updatedUser = mapperUtil.convert(userDto, new User());
        updatedUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        updatedUser.setEnabled(userRepository.findUserById(userDto.getId()).isEnabled());
        User savedUser = userRepository.save(updatedUser);
        return mapperUtil.convert(savedUser, userDto);
    }

    @Override
    public void delete(Long userId) throws UserDoesNotExistException {
        User user = userRepository.findUserById(userId);
        user.setUsername(user.getUsername() + "-" + user.getId());
        user.setIsDeleted(true);
        userRepository.save(user);
    }

    @Override
    public Boolean emailExist(UserDto userDto) throws AccountingException {
        User userWithUpdatedEmail = userRepository.findByUsername(userDto.getUsername());
        return !userWithUpdatedEmail.getId().equals(userDto.getId());
    }


    private Boolean checkIfOnlyAdminForCompany(UserDto dto) {
        if (!dto.getRole().getDescription().equals("Admin")){
            return false;
        }
        return userRepository.countAllByCompanyAndRole_Description(mapperUtil.convert(dto.getCompany(), new Company()), "Admin") == 1;
    }


}
