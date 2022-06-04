package ru.job4j.pojo;

public class Book {

    private String title;
    private int count;

    public Book() {
    }

    public Book(String title, int count) {
        this.count = count;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
