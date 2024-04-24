package org.example.ClassToParsed;


import java.util.ArrayList;

public class Book {
    private String title;
    private String author;
    private int[] publicationYears;
    private ArrayList<String> genres;
    private boolean isFiction;

    public Book() {
        this.title = "";
        this.author = "";
        this.publicationYears = new int[0];
        this.genres = new ArrayList<>();
        this.isFiction = true;
    }

    public Book(String title, String author, int[] publicationYears, ArrayList<String> genres, boolean isFiction, double rating) {
        this.title = title;
        this.author = author;
        this.publicationYears = publicationYears;
        this.genres = genres;
        this.isFiction = isFiction;
    }

    @Override
    public String toString() {
        StringBuilder yearsStr = new StringBuilder("[");
        for (int i = 0; i < publicationYears.length; i++) {
            yearsStr.append(publicationYears[i]);
            if (i < publicationYears.length - 1) {
                yearsStr.append(", ");
            }
        }
        yearsStr.append("]");

        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publicationYears=" + yearsStr.toString() +
                ", genres=" + genres +
                ", isFiction=" + isFiction +
                '}';
    }
}