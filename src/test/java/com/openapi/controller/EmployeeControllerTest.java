package com.openapi.controller;

import com.openapi.model.Employee;
import com.openapi.model.EmployeeRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.Objects;
import java.util.UUID;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EmployeeControllerTest {

    @Value("${local.server.port}")
    private int randomServerPort; //random server port assigned by spingboot test

    @Value("${server.servlet.context-path}")
    private String contextRoot;

    private String baseUrl;

    private HttpHeaders httpHeaders;

    private final String validToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJqYW5lZG9lQGV4YW1wbGUuY29tIiwibmFtZSI6IkphbmUgRG9lIiwiaWF0IjoxNTQ2MzAwODAwLCJleHAiOjE4OTM0NTYwMDB9.Csjq0GWRu5ChiEjNhzyDDuk9YR08gIwvnsfTkqoOISPb9ZEJcFGs6yrEZZ5lhyIneW-QIJZjqg8EPHaitFd6PncOb1zuFBcECK1aPNiVXQ1stS4GZZ0K7kMTfbn_bDtg3LwkScRLvd1UaXPX3gdWiyTWU_EQeBtpaKvN_NtqzRlAVslmHfsi50dgDmg0Pbk80oLqbN8TB4JctgM6VuvI5yCrwyID9GNwItSMVpd1ZjOmGTiof2hqi6E3KH3j9RfWz_Gq4Vq61XvYKEooDzDjuFSoie1vkptVp2fngRMBTxTTM2ZBCEpd_HbcrqBiHuIMw19nyXr8mP7n8rPLG4S1LA";

    @BeforeEach
    void setUp() {

        System.out.println("API PORT :" + randomServerPort);
        baseUrl = "http://127.0.0.1:" + randomServerPort + contextRoot + "/employees";
        System.out.println("BASE URL :" + baseUrl);

        httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setBearerAuth(validToken);
        System.out.println("Headers are set");
    }

    @Test
    void addNewEmployee() {

        EmployeeRequest request = new EmployeeRequest();
        request.setLastName("Scmitz");
        request.setFirstName("Robert");
        HttpEntity<EmployeeRequest> httpEntity = new HttpEntity<>(request, httpHeaders);
        ResponseEntity<Employee> responseEntity = new TestRestTemplate().postForEntity(baseUrl, httpEntity, Employee.class);
        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        Assertions.assertEquals(Boolean.FALSE, Objects.requireNonNull(responseEntity.getBody()).getId().isEmpty());

    }

    @Test
    void getAllEmployees() {
        EmployeeRequest request = new EmployeeRequest();
        request.setLastName("Ralph");
        request.setFirstName("Robert");
        HttpEntity<EmployeeRequest> httpEntity = new HttpEntity<>(request, httpHeaders);
        ResponseEntity<Employee> responseEntity = new TestRestTemplate().postForEntity(baseUrl, httpEntity, Employee.class);
        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        ResponseEntity<Object[]> entity = new TestRestTemplate().exchange(baseUrl, HttpMethod.GET, new HttpEntity<>(httpHeaders), Object[].class);
        Assertions.assertEquals(HttpStatus.OK, entity.getStatusCode());
        Assertions.assertEquals(Boolean.TRUE, entity.getBody().length >= 1);

    }

    @Test
    void getEmployee() {
        EmployeeRequest request = new EmployeeRequest();
        request.setLastName("Ralph");
        request.setFirstName("Junes");
        HttpEntity<EmployeeRequest> httpEntity = new HttpEntity<>(request, httpHeaders);
        ResponseEntity<Employee> responseEntity = new TestRestTemplate().postForEntity(baseUrl, httpEntity, Employee.class);
        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        String url = baseUrl + "/" + responseEntity.getBody().getId();
        System.out.println(url);

        ResponseEntity<Employee> entity = new TestRestTemplate().exchange(url, HttpMethod.GET, new HttpEntity<>(httpHeaders), Employee.class);
        Assertions.assertEquals(HttpStatus.OK, entity.getStatusCode());
        Assertions.assertEquals(request.getFirstName(), entity.getBody().getFirstName());
        Assertions.assertEquals(request.getLastName(), entity.getBody().getLastName());

    }

    @Test
    void updateExistingEmployee() {
        EmployeeRequest request = new EmployeeRequest();
        request.setLastName("James");
        request.setFirstName("Hillock");
        HttpEntity<EmployeeRequest> httpEntity = new HttpEntity<>(request, httpHeaders);
        ResponseEntity<Employee> responseEntity = new TestRestTemplate().postForEntity(baseUrl, httpEntity, Employee.class);
        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        Employee originalEmployee = responseEntity.getBody();

        Employee updatedEmployee = new Employee();
        updatedEmployee.setId(originalEmployee.getId());
        updatedEmployee.setFirstName("Jamie");
        updatedEmployee.setLastName("Hillrock");

        httpEntity = new HttpEntity<>(updatedEmployee, httpHeaders);
        ResponseEntity<Employee> entity = new TestRestTemplate().exchange(baseUrl, HttpMethod.PUT, httpEntity, Employee.class);
        Assertions.assertEquals(HttpStatus.OK, entity.getStatusCode());
        Assertions.assertEquals(originalEmployee.getId(), entity.getBody().getId());
        Assertions.assertNotEquals(originalEmployee.getFirstName(), entity.getBody().getFirstName());
        Assertions.assertNotEquals(originalEmployee.getLastName(), entity.getBody().getLastName());

    }

    @Test
    void updateNonExistingEmployee() {
        EmployeeRequest request = new EmployeeRequest();
        request.setLastName("James");
        request.setFirstName("Hillock");
        HttpEntity<EmployeeRequest> httpEntity = new HttpEntity<>(request, httpHeaders);
        ResponseEntity<Employee> responseEntity = new TestRestTemplate().postForEntity(baseUrl, httpEntity, Employee.class);
        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        Employee originalEmployee = responseEntity.getBody();

        Employee updatedEmployee = new Employee();
        updatedEmployee.setId(UUID.randomUUID().toString());
        updatedEmployee.setFirstName("Jamie");
        updatedEmployee.setLastName("Hillrock");

        httpEntity = new HttpEntity<>(updatedEmployee, httpHeaders);
        ResponseEntity<Employee> entity = new TestRestTemplate().exchange(baseUrl, HttpMethod.PUT, httpEntity, Employee.class);
        Assertions.assertEquals(HttpStatus.CREATED, entity.getStatusCode());
        Assertions.assertNotEquals(originalEmployee.getId(), entity.getBody().getId());
        Assertions.assertNotEquals(originalEmployee.getFirstName(), entity.getBody().getFirstName());
        Assertions.assertNotEquals(originalEmployee.getLastName(), entity.getBody().getLastName());

    }


    @Test
    void deleteEmployee() {
        EmployeeRequest request = new EmployeeRequest();
        request.setLastName("Peter");
        request.setFirstName("Homes");
        HttpEntity<EmployeeRequest> httpEntity = new HttpEntity<>(request, httpHeaders);
        ResponseEntity<Employee> responseEntity = new TestRestTemplate().postForEntity(baseUrl, httpEntity, Employee.class);
        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        String url = baseUrl + "/" + responseEntity.getBody().getId();
        System.out.println(url);

        ResponseEntity<Employee> entity = new TestRestTemplate().exchange(url, HttpMethod.DELETE, new HttpEntity<>(httpHeaders), Employee.class);
        Assertions.assertEquals(HttpStatus.OK, entity.getStatusCode());
        Assertions.assertEquals(request.getFirstName(), entity.getBody().getFirstName());
        Assertions.assertEquals(request.getLastName(), entity.getBody().getLastName());

    }
}