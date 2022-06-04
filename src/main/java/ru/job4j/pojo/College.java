package ru.job4j.pojo;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class College {
    public static void main(String[] args) {
        Student student = new Student();
        student.setFullName("Ivanov Ivan Ivanovich");
        student.setGroup("05280");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy");
        LocalDateTime currentDate = LocalDateTime.now();
        student.setEnter(currentDate);
        String enterStudent = student.getEnter().format(formatter);
        System.out.println("ФИО: " + student.getFullName() + ", группа: " + student.getGroup() + ", дата поступления: " + enterStudent);
    }
}
