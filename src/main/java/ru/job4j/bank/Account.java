package ru.job4j.bank;

import java.util.Objects;

/**
 * Класс описывает модель данных простейшего банковского счета
 *@author ***
 * @version 1.0
 */
public class Account {
    /**
     * Содержит реквизиты банковсого счета в виде строки
     */
    private String requisite;
    /**
     * Содержит баланс на банковском счете, ти double
     */
    private double balance;

    /**
     * Конструктор класса, инициализирует поля
     * @param requisite - реквзиты счета, {@link ru.job4j.bank.Account#requisite}}
     * @param balance - баланс счета, {@link ru.job4j.bank.Account#balance}}
     */
    public Account(String requisite, double balance) {
        this.requisite = requisite;
        this.balance = balance;
    }

    /**
     * геттер поля реквизита счета, {@link ru.job4j.bank.Account#requisite}}
     * @return возвращает реквизиты текущего счета
     */
    public String getRequisite() {
        return requisite;
    }

    /**
     * сеттер поля ревизиты счета, позволяет его заменить новыми данными, {@link ru.job4j.bank.Account#requisite}}
     * @param requisite - реквизиты на которые следует заменить текузие данные
     */
    public void setRequisite(String requisite) {
        this.requisite = requisite;
    }

    /**
     * геттер поля баланс счета, {@link ru.job4j.bank.Account#balance}}
     * @return возвращает баланс текущего счета
     */
    public double getBalance() {
        return balance;
    }

    /**
     * сеттер поля баланс счета, позволяет его заменить новыми данными, {@link ru.job4j.bank.Account#balance}}
     * @param balance - баланс на который заменяется текущий
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return Objects.equals(requisite, account.requisite);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requisite);
    }
}
