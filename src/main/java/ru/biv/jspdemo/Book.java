package ru.biv.jspdemo;

public class Book {

    private int id;
    private String nameBook;
    private String date;
    private String author;
    private String ideasBook;

    public Book(String nameBook, String date, String author, String ideasBook) {
        this.nameBook = nameBook;
        this.date = date;
        this.author = author;
        this.ideasBook = ideasBook;
    }

    public Book(int id, String nameBook, String date, String author, String ideasBook) {
        this.id = id;
        this.nameBook = nameBook;
        this.date = date;
        this.author = author;
        this.ideasBook = ideasBook;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIdeasBook() {
        return ideasBook;
    }

    public void setIdeasBook(String ideasBook) {
        this.ideasBook = ideasBook;
    }
}
