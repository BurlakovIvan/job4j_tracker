package ru.job4j.tracker;

import java.util.*;

public class MemTracker implements Store {
    private List<Item> items = new ArrayList<>();
    private int ids = 1;

    @Override
    public Item add(Item item) {
        item.setId(ids++);
        items.add(item);
        return item;
    }

    @Override
    public List<Item> findAll() {
        return new ArrayList<Item>(items);
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> itemOnName = new ArrayList<>();
        for (Item item : items) {
            if (key.equals(item.getName())) {
                itemOnName.add(item);
            }
        }
        return itemOnName;
    }

    @Override
    public Item findById(int id) {
        int index = indexOf(id);
        return index != -1 ? items.get(index) : null;
    }

    @Override
    public boolean replace(int id, Item item) {
        int index = indexOf(id);
        boolean result = index != -1;
        if (result) {
            item.setId(id);
            items.set(index, item);
        }
        return result;
    }

    @Override
    public boolean delete(int id) {
        int index = indexOf(id);
        boolean result = index != -1;
        if (result) {
            items.remove(index);
        }
        return result;
    }

    private int indexOf(int id) {
        for (int index = 0; index < items.size(); index++) {
               if (items.get(index) != null && items.get(index).getId() == id) {
                   return index;
            }
        }
        return -1;
    }

}