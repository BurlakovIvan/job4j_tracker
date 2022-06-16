package ru.job4j.lambda;

import java.util.ArrayList;
import java.util.function.Function;
import java.util.List;

public class Sum {
    public static List<Double> diapason(int start, int end, Function<Double, Double> func) {
        List<Double> rsl = new ArrayList<>();
        for (int i = start; i < end; i++) {
            rsl.add(func.apply((double) i));
        }
        return rsl;
    }
}
