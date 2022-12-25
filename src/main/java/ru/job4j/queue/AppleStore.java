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
        String name = "";
        Customer customer = queue.poll();
        int remainder = count;
        while (customer != null && remainder > 0) {
            remainder--;
            name = customer.name();
            customer = queue.poll();
        }
        return name;
    }

    public String getLastUpsetCustomer() {
        Customer customer = queue.poll();
        int remainder = count;
        while (customer != null && remainder > 0) {
            remainder--;
            customer = queue.poll();
        }
        return remainder == 0 ? customer.name() : "";
    }
}
