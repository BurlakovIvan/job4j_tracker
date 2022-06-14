package ru.job4j.collection;

import org.junit.Test;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class StringCompareTest {
    @Test
    public void whenFirstStringEqualZeroThenNegative() {
        StringCompare compare = new StringCompare();
        int rst = compare.compare(
                "",
                "Ivanov"
        );
        assertThat(rst, lessThan(0));
    }

    @Test
    public void whenStringsAreEqualThenZero() {
        StringCompare com = new StringCompare();
        int rst = com.compare(
                "Ivanov",
                "Ivanov"
        );
        assertThat(rst, is(0));
    }

    @Test
    public void whenLeftLessThanRightResultShouldBeNegative() {
        StringCompare com = new StringCompare();
        int rst = com.compare(
                "Ivanov",
                "Ivanova"
        );
        assertThat(rst, lessThan(0));
    }

    @Test
    public void whenLeftGreaterThanRightResultShouldBePositive() {
        StringCompare com = new StringCompare();
        int rst = com.compare(
                "Petrov",
                "Ivanova"
        );
        assertThat(rst, greaterThan(0));
    }

    @Test
    public void secondCharOfLeftGreaterThanRightShouldBePositive() {
        StringCompare com = new StringCompare();
        int rst = com.compare(
                "Petrov",
                "Patrov"
        );
        assertThat(rst, greaterThan(0));
    }

    @Test
    public void secondCharOfLeftLessThanRightShouldBeNegative() {
        StringCompare com = new StringCompare();
        int rst = com.compare(
                "Patrova",
                "Petrov"
        );
        assertThat(rst, lessThan(0));
    }
}