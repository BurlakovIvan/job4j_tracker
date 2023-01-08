package ru.job4j.checkstyle;

public class Broken {
    public static final String NEW_VALUE = "";
    private final int sizeOfEmpty = 10;
    public String surname;
    public String name;

    public Broken() { }

    public void echo() { }

    public void media(Object obj) {
        if (obj != null) {
            System.out.println(obj);
        }
    }

    public void method(int a, int b, int c, int d) { }
}