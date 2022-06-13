package ru.job4j.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankService {
    private final Map<User, List<Account>> users = new HashMap<>();

    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<>());
    }

    public void deleteUser(User user) {
        users.remove(user);
    }

    public void addAccount(String passport, Account account) {
        User user = findByPassport(passport);
        if (user != null) {
            List<Account> userAccount = users.get(user);
            if (!userAccount.contains(account)) {
                userAccount.add(account);
            }
        }
    }

    public User findByPassport(String passport) {
        User userFounded = null;
        for (User user : users.keySet()) {
            if (passport.equals(user.getPassport())) {
                userFounded = user;
                break;
            }
        }
        return userFounded;
    }

    public Account findByRequisite(String passport, String requisite) {
        Account accountFounded = null;
        User user = findByPassport(passport);
        if (user != null) {
            List<Account> userAccount = users.get(user);
            for (Account account : userAccount) {
                if (requisite.equals(account.getRequisite())) {
                    accountFounded = account;
                    break;
                }
            }
        }
        return accountFounded;
    }

    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        boolean rsl = false;
        Account srcAccount = findByRequisite(srcPassport, srcRequisite);
        if (srcAccount != null && srcAccount.getBalance() >= amount) {
            Account destAccount = findByRequisite(destPassport, destRequisite);
            if (destAccount != null) {
                destAccount.setBalance(destAccount.getBalance() + amount);
                srcAccount.setBalance(srcAccount.getBalance() - amount);
                rsl = true;
            }
        }
        return rsl;
    }
}
