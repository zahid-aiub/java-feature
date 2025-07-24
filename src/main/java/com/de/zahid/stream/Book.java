package com.de.zahid.stream;

public class Book {
    private int id;
    private String name;
    private String author;
    private String type;
    private int publishedYear;
    private double rating;

    public Book(int id, String name, String author, String type, int publishedYear, double rating) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.type = type;
        this.publishedYear = publishedYear;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", type='" + type + '\'' +
                ", publishedYear=" + publishedYear +
                ", rating=" + rating +
                '}';
    }
}
