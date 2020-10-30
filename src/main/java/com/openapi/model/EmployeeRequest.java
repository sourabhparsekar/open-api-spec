package com.openapi.model;

import com.squareup.moshi.Json;

/**
 * @author sourabh
 * @implNote base class for employee
 */
public class EmployeeRequest {

    @Json(name = "first_name")
    private String firstName;

    @Json(name = "last_name")
    private String lastName;

    public EmployeeRequest() {
        firstName = null;
        lastName = null;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
