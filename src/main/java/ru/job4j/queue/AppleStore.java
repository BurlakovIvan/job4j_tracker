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
        if (count > 0) {
            Customer customer = queue.poll();
            for (int i = 0; i < count - 1; i++) {
                Customer next = queue.poll();
                if (next == null) {
                    break;
                }
                customer = next;
            }
            name = customer.name();
        }
        return name;
    }

    public String getLastUpsetCustomer() {
        Customer customer = queue.poll();
        String name = customer.name();
        if (queue.size() < count || queue.size() == 0) {
            name = "";
        } else if (count > 0) {
            int i = 0;
            while (i <= count && customer != null) {
                i++;
                name = customer.name();
                customer = queue.poll();
            }
        }
        return name;
    }
}
