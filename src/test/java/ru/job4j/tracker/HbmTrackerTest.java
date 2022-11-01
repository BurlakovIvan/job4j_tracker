package ru.job4j.tracker;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class HbmTrackerTest {

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() throws Exception {
        try (var tracker = new HbmTracker()) {
            Item item = new Item("test1");
            tracker.add(item);
            Item result = tracker.findById(item.getId());
            assertThat(result.getName()).isEqualTo(item.getName());
        }
    }

    @Test
    public void whenReplaceItemThenTrackerHasNewItemName() throws Exception {
        try (var tracker = new HbmTracker()) {
            Item item1 = new Item("test1");
            tracker.add(item1);
            String testName = "test2";
            Item item2 = new Item(testName);
            tracker.replace(item1.getId(), item2);
            Item result = tracker.findById(item1.getId());
            assertThat(result.getName()).isEqualTo(testName);
        }
    }

    @Test
    public void whenDeleteItemThenTrueAdnNotFindByID() throws Exception {
        try (var tracker = new HbmTracker()) {
            Item item1 = new Item("test1");
            tracker.add(item1);
            Item item2 = new Item("test2");
            tracker.add(item2);
            boolean result = tracker.delete(item1.getId());
            assertThat(result).isTrue();
            Item rsl = tracker.findById(item1.getId());
            assertThat(rsl).isNull();
        }
    }

    @Test
    public void whenAddItemThenReturnTheSameCount() throws Exception {
        try (var tracker = new HbmTracker()) {
            List<Item> result = tracker.findAll();
            assertThat(result.size()).isEqualTo(0);
            Item item1 = new Item("test1");
            tracker.add(item1);
            Item item2 = new Item("test2");
            tracker.add(item2);
            result = tracker.findAll();
            assertThat(result.size()).isEqualTo(2);
        }
    }

    @Test
    public void whenFindItemByName() throws Exception {
        try (var tracker = new HbmTracker()) {
            Item item1 = new Item("test1");
            tracker.add(item1);
            Item item2 = new Item("test2");
            tracker.add(item2);
            List<Item> result = tracker.findByName("test1");
            assertThat(result.size()).isEqualTo(1);
            assertThat(result.get(0).getName()).isEqualTo(item1.getName());
        }
    }

    @Test
    public void whenNotFindItemByName() throws Exception {
        try (var tracker = new HbmTracker()) {
            Item item1 = new Item("test1");
            tracker.add(item1);
            Item item2 = new Item("test2");
            tracker.add(item2);
            List<Item> result = tracker.findByName("test3");
            assertThat(result.size()).isEqualTo(0);
        }
    }

    @Test
    public void whenFindItemById() throws Exception {
        try (var tracker = new HbmTracker()) {
            Item item1 = new Item("test1");
            tracker.add(item1);
            Item item2 = new Item("test2");
            tracker.add(item2);
            Item result = tracker.findById(item2.getId());
            assertThat(result.getName()).isEqualTo(item2.getName());
        }
    }
}