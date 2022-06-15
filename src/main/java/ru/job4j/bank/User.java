package ru.job4j.bank;

import java.util.Objects;

/**
 * Класс описывает модель данных пользователя системы
 *@author ***
 * @version 1.0
 */
public class User {
    /**
     * Содержит паспортные данные пользователя в виде строки
     */
    private String passport;
    /**
     * Содержит ФИО пользователя в виде строки
     */
    private String username;

    /**
     * Конструктор класса, инициализирует поля
     * @param passport - паспортные данные нового пользователя, {@link ru.job4j.bank.User#passport}}
     * @param username - ФИО нового пользователя, {@link ru.job4j.bank.User#username}}
     */
    public User(String passport, String username) {
        this.passport = passport;
        this.username = username;
    }

    /**
     * геттер поля паспортные данные, {@link ru.job4j.bank.User#passport}}
     * @return возвращает паспортные данные текущего пользователя
     */
    public String getPassport() {
        return passport;
    }

    /**
     * сеттер поля паспортные данные, {@link ru.job4j.bank.User#passport}}
     * @param passport - паспортные данные, которые заменят текущие
     */
    public void setPassport(String passport) {
        this.passport = passport;
    }

    /**
     * геттер поля ФИО, {@link ru.job4j.bank.User#username}}
     * @return возвращает ФИО текущего пользователя
     */
    public String getUsername() {
        return username;
    }

    /**
     * сеттер поля ФИО, {@link ru.job4j.bank.User#username}}
     * @param username - ФИО, которое заменит текущее
     */
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(passport, user.passport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(passport);
    }
}
