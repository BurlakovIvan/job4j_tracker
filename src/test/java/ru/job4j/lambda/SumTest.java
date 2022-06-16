package ru.job4j.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SumTest {
    @Test
    public void whenLinearFunctionThenLinearResults() {
        List<Double> result = Sum.diapason(5, 8, x -> 2 * x + 1);
        List<Double> expected = Arrays.asList(11D, 13D, 15D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenQuadraticFunction() {
        List<Double> result = Sum.diapason(4, 6, x -> x * x - 1);
        List<Double> expected = Arrays.asList(15D, 24D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenExponentialFunction() {
        List<Double> result = Sum.diapason(1, 4, x -> Math.pow(3, x) + 3);
        List<Double> expected = Arrays.asList(6D, 12D, 30D);
        assertThat(result, is(expected));
    }
}