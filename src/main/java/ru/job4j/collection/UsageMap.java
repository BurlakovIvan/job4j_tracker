package ru.job4j.collection;

import java.util.HashMap;

public class UsageMap {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("myemail@email.ru", "Ivanov Ivan Ivanovich");
        map.put("youremail@email.ru", "Ivanov Sergey Ivanovich");
        map.put("email@email.ru", "Petrov Petr Petrovicdh");
        for (String key : map.keySet()) {
            String value = map.get(key);
            System.out.println(key + " = " + value);
        }
    }
}
