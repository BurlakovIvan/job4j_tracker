package ru.job4j.search;

import java.util.ArrayList;
import java.util.function.Predicate;

public class PhoneDictionary {
    private final ArrayList<Person> persons = new ArrayList<>();

    public void add(Person person) {
        this.persons.add(person);
    }

    public ArrayList<Person> find(String key) {
        Predicate<Person> personName = person -> person.getName().contains(key);
        Predicate<Person> personPhone = person -> person.getPhone().contains(key);
        Predicate<Person> personSurname = person -> person.getSurname().contains(key);
        Predicate<Person> personAddress = person -> person.getAddress().contains(key);
        var combine = personPhone.or(personName.or(personSurname.or(personAddress)));
        var result = new ArrayList<Person>();
        for (var person : persons) {
            if (combine.test(person)) {
                result.add(person);
            }
        }
        return result;
    }
}
