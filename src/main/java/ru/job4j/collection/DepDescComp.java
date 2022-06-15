package ru.job4j.collection;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class DepDescComp implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        List<String> o1Array = Arrays.asList(o1.split("/"));
        List<String> o2Array = Arrays.asList(o2.split("/"));
        int result = o2Array.get(0).compareTo(o1Array.get(0));
        if (result == 0 && (o1Array.size() > 1 || o2Array.size() > 1)) {
            String o1WihtoutFirst = o1.contains("/") ?  o1.substring(o1.indexOf("/") + 1) : "";
            String o2WihtoutFirst = o2.contains("/") ?  o2.substring(o2.indexOf("/") + 1) : "";
            result = o1WihtoutFirst.compareTo(o2WihtoutFirst);
        }
        return result;
    }
}
