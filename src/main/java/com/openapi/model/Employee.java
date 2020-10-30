package com.openapi.model;

import com.squareup.moshi.Json;

/**
 * @author sourabh
 * @implNote Employee class to be used for Request, Response
 */
public class Employee extends EmployeeRequest{

    @Json(name = "id")
    private String id;

    public Employee() {
        super();
        id = null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
