package ru.job4j;

public class Error {
    private boolean active;
    private int status;
    private String message;

    public Error() {
    }

    public Error(boolean active, int status, String message) {
        this.active = active;
        this.status = status;
        this.message = message;
    }

    public void printInfo() {
        System.out.println("Действующая: " + active);
        System.out.println("Статус: " + status);
        System.out.println("Сообщение об ошибки: " + message);
    }

    public static void main(String[] args) {
        Error arrayIndex = new Error();
        Error stackOverflow = new Error(false, 2, "stack overflow");
        Error divide = new Error(true, 1, "Division by zero");
        arrayIndex.printInfo();
        stackOverflow.printInfo();
        divide.printInfo();
    }
}
