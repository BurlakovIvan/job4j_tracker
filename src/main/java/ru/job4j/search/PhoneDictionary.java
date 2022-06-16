package ru.job4j.search;

import java.util.ArrayList;
import java.util.function.Predicate;

public class PhoneDictionary {
    private final ArrayList<Person> persons = new ArrayList<>();

    public void add(Person person) {
        this.persons.add(person);
    }

    public ArrayList<Person> find(String key) {
        Predicate<Person> personName = person -> key.contains(person.getName());
        Predicate<Person> personPhone = person -> key.contains(person.getPhone());
        Predicate<Person> personSurname = person -> key.contains(person.getSurname());
        Predicate<Person> personAddress = person -> key.contains(person.getAddress());
        Predicate<Person> combine = personName.or(personPhone.or(personSurname.or(personAddress)));
        ArrayList<Person> result = new ArrayList<>();
        for (Person person : persons) {
            if (combine.test(person)) {
                result.add(person);
            }
        }
        return result;
    }
}
