package ru.job4j.tracker;

import java.util.List;

public final class SingleTracker {

    private static SingleTracker instance = null;
    private Store store = new MemTracker();

    private SingleTracker() {
    }

    public static SingleTracker getInstance() {
        if (instance == null) {
            instance = new SingleTracker();
        }
        return instance;
    }

    public Item add(Item item) {
        return store.add(item);
    }

    public List<Item> findAll() {
        return store.findAll();
    }

    public List<Item> findByName(String key) {
        return store.findByName(key);
    }

    public Item findById(int id) {
        return store.findById(id);
    }

    public boolean replace(int id, Item item) {
        return store.replace(id, item);
    }

    public boolean delete(int id) {
        return store.delete(id);
    }
}
