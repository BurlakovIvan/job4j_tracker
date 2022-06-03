package ru.job4j.oop;

public class Calculator {
    private static int x = 5;

    public double add(double first, double second) {
        return first + second;
    }

    public double add(double first, double second, double third) {
        return add(
                first,
                add(second, third)
        );
    }

    public static int sum(int y) {
        return x + y;
    }

    public int multiply(int a) {
        return x * a;
    }

    public static int minus(int a) {
        return a - x;
    }

    public int divide(int a) {
        return a / x;
    }

    public int sumAllOperation(int a) {
        return sum(a) + multiply(a) + minus(a) + divide(a);
    }

    public static void main(String[] args) {
        int result = sum(10);
        System.out.println("sum: " + result);
        Calculator calculator = new Calculator();
        int rsl = calculator.multiply(5);
        System.out.println("multiply: " + rsl);
        result = minus(4);
        System.out.println("minus: " + result);
        rsl = calculator.divide(10);
        System.out.println("divide: "  + rsl);
        rsl = calculator.sumAllOperation(10);
        System.out.println("sumAllOperation: " + rsl);
    }
}
