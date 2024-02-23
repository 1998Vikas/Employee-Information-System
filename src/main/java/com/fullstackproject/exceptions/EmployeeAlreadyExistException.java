package com.fullstackproject.exceptions;

public class EmployeeAlreadyExistException extends RuntimeException{
    public EmployeeAlreadyExistException(String message) {
        super(message);
    }
}
