package com.cydeo.InventoryManagementRest.exception;

public class UserDoesNotExistException extends RuntimeException {
    public UserDoesNotExistException(){
        super("User does not exist.");
    }
    public UserDoesNotExistException(Long userId){
        super("User with " + userId + " not exist");
    }
}
