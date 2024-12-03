package com.de.zahid.datasource;

import com.de.zahid.dto.Employee;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class EmployeeDatSource {

    public static List<Employee> fetchEmployees() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            return mapper.readValue(new File("employees.json"), new TypeReference<List<Employee>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return List.of();
    }
}
