package ru.job4j.bank;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class BankServiceTest {
    @Test
    public void addUser() {
        User user = new User("3434", "Petr Arsentev");
        BankService bank = new BankService();
        bank.addUser(user);
        assertThat(bank.findByPassport("3434").get(), is(user));
    }

    @Test
    public void addUserDuplicate() {
        User userFirst = new User("3434", "Petr Arsentev");
        User userSecond = new User("3434", "Ivan Ivanov");
        BankService bank = new BankService();
        bank.addUser(userFirst);
        bank.addUser(userSecond);
        assertEquals(bank.findByPassport("3434").get().getUsername(), userFirst.getUsername());
    }

    @Test
    public void deleteUser() {
        User userFirst = new User("3434", "Petr Arsentev");
        User userSecond = new User("1234", "Ivan Ivanov");
        BankService bank = new BankService();
        bank.addUser(userFirst);
        bank.addUser(userSecond);
        bank.deleteUser(userFirst);
        assertTrue(bank.findByPassport("3434").isEmpty());
    }

    @Test
    public void whenEnterInvalidPassport() {
        User user = new User("3434", "Petr Arsentev");
        BankService bank = new BankService();
        bank.addUser(user);
        bank.addAccount(user.getPassport(), new Account("5546", 150D));
        assertTrue(bank.findByRequisite("34", "5546").isEmpty());
    }

    @Test
    public void whenEnterInvalidRequisite() {
        User user = new User("3434", "Petr Arsentev");
        BankService bank = new BankService();
        bank.addUser(user);
        bank.addAccount(user.getPassport(), new Account("5546", 150D));
        assertFalse(bank.findByRequisite("3434", "55").isPresent());
    }

    @Test
    public void addAccount() {
        User user = new User("3434", "Petr Arsentev");
        BankService bank = new BankService();
        bank.addUser(user);
        bank.addAccount(user.getPassport(), new Account("5546", 150D));
        assertThat(bank.findByRequisite("3434", "5546").get().getBalance(), is(150D));
    }

    @Test
    public void addTwoAccount() {
        User user = new User("3434", "Petr Arsentev");
        BankService bank = new BankService();
        bank.addUser(user);
        bank.addAccount(user.getPassport(), new Account("5546", 50D));
        bank.addAccount(user.getPassport(), new Account("113", 100D));
        assertThat(bank.findByRequisite("3434", "5546").get().getBalance(), is(50D));
    }

    @Test
    public void transferMoney() {
        User user = new User("3434", "Petr Arsentev");
        BankService bank = new BankService();
        bank.addUser(user);
        bank.addAccount(user.getPassport(), new Account("5546", 150D));
        bank.addAccount(user.getPassport(), new Account("113", 50D));
        bank.transferMoney(user.getPassport(), "5546", user.getPassport(), "113", 150D);
        assertThat(bank.findByRequisite(user.getPassport(), "113").get().getBalance(), is(200D));
    }

    @Test
    public void transferMoneyNullAccountDest() {
        User user = new User("3434", "Petr Arsentev");
        BankService bank = new BankService();
        bank.addUser(user);
        bank.addAccount(user.getPassport(), new Account("5546", 150D));
        bank.addAccount(user.getPassport(), new Account("113", 50D));
        assertFalse(bank.transferMoney(user.getPassport(), "5546",
                user.getPassport(), "11", 150D));
    }

    @Test
    public void transferMoneyNullAccountSrc() {
        User user = new User("3434", "Petr Arsentev");
        BankService bank = new BankService();
        bank.addUser(user);
        bank.addAccount(user.getPassport(), new Account("5546", 150D));
        bank.addAccount(user.getPassport(), new Account("113", 50D));
        assertFalse(bank.transferMoney(user.getPassport(), "55",
                user.getPassport(), "113", 150D));
    }

    @Test
    public void transferMoneyWrongAmount() {
        User user = new User("3434", "Petr Arsentev");
        BankService bank = new BankService();
        bank.addUser(user);
        bank.addAccount(user.getPassport(), new Account("5546", 150D));
        bank.addAccount(user.getPassport(), new Account("113", 50D));
        assertFalse(bank.transferMoney(user.getPassport(), "5546",
                user.getPassport(), "113", 200D));
    }

    @Test
    public void transferMoneyCorrectAmountFirstAccount() {
        User user = new User("3434", "Petr Arsentev");
        BankService bank = new BankService();
        bank.addUser(user);
        bank.addAccount(user.getPassport(), new Account("5546", 150D));
        bank.addAccount(user.getPassport(), new Account("113", 50D));
        bank.transferMoney(user.getPassport(), "5546", user.getPassport(), "113", 150D);
        assertThat(bank.findByRequisite(user.getPassport(), "5546").get().getBalance(), is(0D));
    }
}