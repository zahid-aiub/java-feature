package com.de.zahid.stream;

import java.util.*;
import java.util.stream.Collectors;

public class StreamTest {

    public static void groupWiseHighestRatingBooks(List<Book> books) {
        //      Group wise height rating book list
        List<Book> sortedGroupedList = books.stream()
                .collect(Collectors.groupingBy(Book::getType)) // Group by type
                .values().stream()                             // Stream of List<Book>
                .flatMap(group -> group.stream()
                        .sorted(Comparator.comparingDouble(Book::getRating).reversed())
                )
                .toList();

        sortedGroupedList.forEach(book -> {
            System.out.printf("%d, \"%s\", \"%s\", \"%s\", %d, %.1f\n",
                    book.getId(),
                    book.getName(),
                    book.getAuthor(),
                    book.getType(),
                    book.getPublishedYear(),
                    book.getRating()
            );
        });
    }

    public static void highestRatedBookInGroup(List<Book> books) {

        //        Height rated book per group
        Map<String, Book> bookMap = new HashMap<>();
        books.forEach(book -> {
            if (bookMap.containsKey(book.getType())) {
                Book existBook = bookMap.get(book.getType());

                if (book.getRating() > existBook.getRating()) {
                    bookMap.put(book.getType(), book);
                }
            } else {
                bookMap.put(book.getType(), book);
            }
        });

        System.out.println(bookMap);
    }

    public static void groupWiseSortByRating(List<Book> books) {

        Map<String, List<Book>> groupedSortedBooks = books.stream()
                .collect(Collectors.groupingBy(
                        Book::getType,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> list.stream()
                                        .sorted(Comparator.comparingDouble(Book::getRating).reversed())
                                        .collect(Collectors.toList())
                        )
                ));


        groupedSortedBooks.forEach((type, bookList) -> {
            bookList.forEach(book -> {
                System.out.printf("%d, \"%s\", \"%s\", \"%s\", %d, %.1f\n",
                        book.getId(),
                        book.getName(),
                        book.getAuthor(),
                        book.getType(),
                        book.getPublishedYear(),
                        book.getRating()
                );
            });
            System.out.println("--");
        });
    }

    public static void getAllDistinctAuthorName(List<Book> books) {
        List<String> authorList =
                books.stream()
                        .map(Book::getAuthor)
                        .sorted()
                        .toList();

        System.out.println(authorList);
    }

    public static void getBookPublishedAfter2000(List<Book> books) {

        List<Book> bookList = books.stream()
                .filter(book -> book.getPublishedYear() > 2000)
                .sorted(Comparator.comparing(Book::getRating).reversed())
                .toList();

        System.out.println(bookList);
    }

    //Todo::::: interesting findings
    public static void countBooksPerAuthor(List<Book> books) {

        Map<String, Long> booksPerAuthor = books.stream()
                .collect(Collectors.groupingBy(
                        Book::getAuthor,
                        Collectors.counting()
                ));

        System.out.println(booksPerAuthor);
    }

    // Get Books with Rating Above the Average
    public static void getBooksWithAboveAvgRating(List<Book> books) {

        double avgRating = books.stream()
                .mapToDouble(Book::getRating)
                .average()
                .orElse(0);

        System.out.println(avgRating);

        List<Book> aboveAvg = books.stream()
                .filter(book -> book.getRating() > avgRating)
                .toList();

        aboveAvg.forEach(System.out::println);
    }

    // Group by Author and List Book Names Only
    public static void groupByAuthorAndBookListName(List<Book> books) {
        Map<String, List<String>> bookByAuthor = books.stream()
                .collect(Collectors.groupingBy(
                        Book::getAuthor,
                        Collectors.mapping(Book::getName, Collectors.toList())
                ));

        System.out.println(bookByAuthor);
    }

    public static void main(String[] args) {

        List<Book> books = Arrays.asList(
                new Book(1, "Atomic Habits", "James Clear", "Non-Fiction", 2018, 4.8),
                new Book(2, "To Kill a Mockingbird", "Harper Lee", "Fiction", 1960, 4.7),
                new Book(3, "A Brief History of Time", "Stephen Hawking", "Science", 1988, 4.5),
                new Book(4, "The Great Gatsby", "F. Scott Fitzgerald", "Fiction", 1925, 4.3),
                new Book(5, "Sapiens", "Yuval Noah Harari", "History", 2011, 4.6),
                new Book(6, "The Alchemist", "Paulo Coelho", "Fiction", 1988, 4.2),
                new Book(7, "Clean Code", "Robert C. Martin", "Programming", 2008, 4.7),
                new Book(8, "The Pragmatic Programmer", "Andrew Hunt", "Programming", 1999, 4.6),
                new Book(9, "1984", "George Orwell", "Fiction", 1949, 4.4),
                new Book(10, "Thinking, Fast and Slow", "Daniel Kahneman", "Non-Fiction", 2011, 4.1)
        );

        groupWiseHighestRatingBooks(books);
        highestRatedBookInGroup(books);
        groupWiseSortByRating(books);
        getAllDistinctAuthorName(books);
        getBookPublishedAfter2000(books);
        countBooksPerAuthor(books);
        getBooksWithAboveAvgRating(books);
        groupByAuthorAndBookListName(books);
    }
}
