package com.de.zahid.completableFuture;

import com.de.zahid.datasource.EmployeeDatSource;
import com.de.zahid.dto.Employee;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class ReminderService {

    public CompletableFuture<Void> sendRemainder() {

        Executor executor = Executors.newFixedThreadPool(5);

        // Todo: Used supplyAsync if you need to return some value
        // Todo: Used runAsync if you don't need to return any value

        CompletableFuture<Void> listCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("Employee data fetch : " + Thread.currentThread().getName());
            return EmployeeDatSource.fetchEmployees();
        }, executor).thenApplyAsync((employees) -> {
            System.out.println("Filter newly joined employee list : " + Thread.currentThread().getName());
            return employees
                    .stream()
                    .filter(employee -> employee.getNewJoiner().equals("TRUE"))
                    .collect(Collectors.toList());
        }, executor).thenApplyAsync((employees) -> {
            System.out.println("Filter training not completed employees : " + Thread.currentThread().getName());
            return employees
                    .stream()
                    .filter(employee -> employee.getLearningPending().equals("TRUE"))
                    .collect(Collectors.toList());
        }, executor).thenApplyAsync((employees) -> {
            System.out.println("Collect employee email list : " + Thread.currentThread().getName());
            return employees
                    .stream()
                    .map(Employee::getEmail)
                    .collect(Collectors.toList());
        }, executor).thenAcceptAsync((emails) -> {
            System.out.println("Send email to the employee : " + Thread.currentThread().getName());
            emails.forEach(ReminderService::sendEmail);
        });

        return listCompletableFuture;
    }


    public static void sendEmail(String email) {
        System.out.println("Email has sent to : " + email);
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ReminderService reminderService = new ReminderService();
        reminderService.sendRemainder().get();

    }
}
