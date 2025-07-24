package com.de.zahid.completableFuture;

/*
 * Click `Run` to execute the snippet below!
 */

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


public class Solution {

    public static void main(String[] args) {

        var books = new ArrayList<Book>();

        books.add(new Book(1, "Clean Code", "Robert C. Martin", "Programming", 2008, 4.7));
        books.add(new Book(2, "Effective Java", "Joshua Bloch", "Programming", 2018, 4.9));
        books.add(new Book(3, "Design Patterns", "Erich Gamma", "Programming", 1994, 4.5));
        books.add(new Book(4, "Atomic Habits", "James Clear", "Self-help", 2018, 4.8));
        books.add(new Book(5, "Deep Work", "Cal Newport", "Self-help", 2016, 4.6));
        books.add(new Book(6, "Thinking, Fast and Slow", "Daniel Kahneman","Psychology", 2011, 4.2));

        // Print to the console book with the highest rating by genre:
        // Programming: Effective Java
        // Self-help: Atomic Habits
        // Psychology: Thinking, Fast and Slow

        Map<String, Book> map = new HashMap<>();

        books.forEach( book -> {
            if(map.containsKey(book.genre)) {
                Book existingBook = map.get(book.genre);
                if(existingBook.rating < book.rating) {
                    map.put(existingBook.genre, book);
                }
            }
            else {
                map.put(book.genre, book);
            }
        });
        System.out.println(map);


    }

    public record Book(
            Integer id,
            String title,
            String author,
            String genre,
            Integer year,
            Double rating) {}

}