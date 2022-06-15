package ru.job4j.collection;

import java.util.Comparator;

public class StringCompare implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        int size = Math.min(o1.length(), o2.length());
        int index = 0;
        int result = 0;
        for (; index < size; index++) {
            result = Character.compare(o1.charAt(index), o2.charAt(index));
            if (result != 0) {
                break;
            }
        }
        if (index == size && o1.length() != o2.length()) {
            result = Integer.compare(o1.length(), o2.length());
        }
        return result;
    }
}
