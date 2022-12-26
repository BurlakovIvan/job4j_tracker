package ru.job4j.queue;

import java.util.Queue;

public class AppleStore {
    private final Queue<Customer> queue;

    private final int count;

    public AppleStore(Queue<Customer> queue, int count) {
        this.queue = queue;
        this.count = count;
    }

    public String getLastHappyCustomer() {
        String result = "";
        int min = Math.min(count, queue.size());
        for (int i = 0; i < min; i++) {
            result = queue.poll().name();
        }
        return result;
    }

    public String getLastUpsetCustomer() {
        String result = "";
        int min = Math.min(count, queue.size() - 1);
        for (int i = 0; i <= min; i++) {
            result = queue.poll().name();
        }
        return count > min ? "" : result;
    }
}
