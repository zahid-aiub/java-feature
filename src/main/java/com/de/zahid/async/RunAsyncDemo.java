package com.de.zahid.async;

import com.de.zahid.dto.Employee;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class RunAsyncDemo {

    public void saveEmployees(File jsonFile) throws ExecutionException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper();
        // Ignore unknown properties
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        CompletableFuture<Void> runAsyncFuture = CompletableFuture.runAsync(() -> {
            try {
                List<Employee> employees = mapper.readValue(jsonFile, new TypeReference<>() {});
                // Save logic
                System.out.println("Thread : " + Thread.currentThread().getName());
                System.out.println(employees.size());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

//        return runAsyncFuture.get();
    }

    public Void saveEmployeesWithCustomExecutor(File jsonFile) throws ExecutionException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Executor executor = Executors.newFixedThreadPool(5);
        CompletableFuture<Void> runAsyncFuture = CompletableFuture.runAsync(() -> {
            try {
                List<Employee> employees = mapper.readValue(jsonFile, new TypeReference<List<Employee>>() {});


                // Save data to the Database
                System.out.println("Thread ----> : " + Thread.currentThread().getName());
                System.out.println(employees.size());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }, executor);

        return runAsyncFuture.get();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        RunAsyncDemo runAsyncDemo = new RunAsyncDemo();
        runAsyncDemo.saveEmployees(new File("employees.json"));
        runAsyncDemo.saveEmployeesWithCustomExecutor(new File("employees.json"));
    }
}
