package ru.job4j.collection;

import java.util.Comparator;

public class StringCompare implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        int result = 0;
        for (int index = 0; index < Math.min(o1.length(), o2.length()); index++) {
            result = Character.compare(o1.charAt(index), o2.charAt(index));
            if (result != 0) {
                break;
            }
        }
        return result == 0 ? Integer.compare(o1.length(), o2.length()) : result;
    }
}
