package com.crud.prelim_crud.Exception;

public class EmployeeNotFoundException extends RuntimeException{
    public EmployeeNotFoundException (String resource, int id) {
        super(resource + " with ID " + id + " not found.");
    }
}
