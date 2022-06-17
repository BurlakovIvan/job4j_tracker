package ru.job4j.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *Класс описывает модель простейшей банковской системы.
 *
 *В системе можно производить следующие действия:
 *
 *1. Регистрировать пользователя;
 *
 *2. Удалять пользователя из системы;
 *
 *3. Добавлять пользователю банковский счет. У пользователя системы могут быть несколько счетов;
 *
 *4. Переводить деньги с одного банковского счета на другой счет.
 * @author ***
 * @version 1.0
 */
public class BankService {
    /**
     * Содержит всех пользователей с привязанными к ним банковскими счетами,
     * хранится в коллекции типа Map
      */
    private final Map<User, List<Account>> users = new HashMap<>();

    /**
     * Метод добавляет ТОЛЬКО НОВЫХ пользователей в систему,
     * с пустым списком банковских счетов
     * @param user - добавляемый пользователь, тип User
     */
    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<>());
    }

    /**
     * Метод удаляет пользователя из системы
     * @param user - удаляемый пользователь, тип User
     */
    public void deleteUser(User user) {
        users.remove(user);
    }

    /**
     * Метод добавляет новый банковский счет пользователю, который есть в системе,
     * поиск пользователя осуществляется по его паспортным данным,
     * если добавляемы банковский счет уже есть у пользователя, то снова он не добавляется
     * @param passport - паспортные данные пользователя, тип String
     * @param account - добаляемый банковский счет, тип Account
     */
    public void addAccount(String passport, Account account) {
        User user = findByPassport(passport);
        if (user != null) {
            List<Account> userAccount = users.get(user);
            if (!userAccount.contains(account)) {
                userAccount.add(account);
            }
        }
    }

    /**
     * Метод осуществляет поиск пользователя по точному совпадению его паспортных данных
     * @param passport - паспортные данные пользователя, тип String
     * @return - возвращает найденного пользователя (тип User), либо null
     */
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

    /**
     * Метод осуществляет поиск банковского счета пользователя по реквизитам,
     * сначала ищется пользователь по паспорту с помощью метода {@link ru.job4j.bank.BankService#findByPassport(String)},
     * потом, если пользователь найден, получаем список счетов этого пользователя и в нем находим нужный счет.
     * @param passport - паспортные данные пользователя, счет которого необходимо найти, тип String
     * @param requisite - реквизиты искомого банковского счета, тип String
     * @return - возвращает найденный банвковский счет (тип Account), либо null
     */
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

    /**
     * метод предназначен для перечисления денег с одного счёта на другой счёт,
     * сначала ищем банковский счет в системе с которого осуществляем перевод,
     * с помощью метода {@link ru.job4j.bank.BankService#findByRequisite(String, String)},
     * если такой счет найден и на нем достаточно денежных средств,
     * то ищем банковский счет в системе на который переводим деньги,
     * с помощью метода {@link ru.job4j.bank.BankService#findByRequisite(String, String)},
     * если счет найден, то зачисляем на него денежные средства, а с первого списываем
     * @param srcPassport - паспортные данные пользователя который переводит деньги, тип String
     * @param srcRequisite - реквизиты банквского счета с которого будем списывать денежные средства, тип String
     * @param destPassport - паспортные данные пользователя который принимает перевод, тип String
     * @param destRequisite - реквизиты банквского счета на который будем зачислять денежные средства, тип String
     * @param amount - переводимая сумма, тип double
     * @return возвращает true если перевод прошел удачно, иначе false
     */
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
