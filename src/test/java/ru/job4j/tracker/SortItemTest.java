package ru.job4j.tracker;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SortItemTest {

    @Test
    public void sortItemAsc() {
        Item one = new Item(3, "One");
        Item two = new Item(6, "Two");
        Item third = new Item(5, "Third");
        Item four = new Item(2, "Four");
        Item five = new Item(1, "Five");
        Item six = new Item(4, "Six");
        List<Item> items = Arrays.asList(five, four, two, third, one, six);
        Collections.sort(items, new ItemAscByName());
        List<Item> expected = Arrays.asList(five, four, one, six, third, two);
        assertThat(items, is(expected));
    }

    @Test
    public void sortItemDesc() {
        Item one = new Item(3, "One");
        Item two = new Item(6, "Two");
        Item third = new Item(5, "Third");
        Item four = new Item(2, "Four");
        Item five = new Item(1, "Five");
        Item six = new Item(4, "Six");
        List<Item> items = Arrays.asList(five, four, two, third, one, six);
        Collections.sort(items, new ItemDescByName());
        List<Item> expected = Arrays.asList(two, third, six, one, four, five);
        assertThat(items, is(expected));
    }
}
